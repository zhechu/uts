package com.yly.uts.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.ZipUtil;
import com.yly.uts.core.model.BigData;
import com.yly.uts.core.model.UtsTag;
import com.yly.uts.dao.BigDataDao;
import com.yly.uts.util.PakoGzipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class BigDataTest {

    @Resource
    private BigDataDao bigDataDao;

    private static final String TABLE_NAME = "hpd_calculation_process";

    /**
     * 转换
     */
    @Test
    public void uint8ArrayToByteArray() {
        String tableName = TABLE_NAME;
        List<BigData> bigDataList = bigDataDao.selectAllId(tableName);
        if (CollectionUtil.isEmpty(bigDataList)) {
            return;
        }
        List<String> bigDataIdList = bigDataList.stream().filter(o -> o != null && StringUtils.isNotBlank(o.getId())).map(BigData::getId).collect(Collectors.toList());
        for (String id : bigDataIdList) {
            BigData bigData = bigDataDao.getById(tableName, id);
            byte[] blockDataJsonGzip = bigData.getBlockDataJsonGzip();
            if (blockDataJsonGzip == null || blockDataJsonGzip.length == 0) {
                continue;
            }
            byte[] gzip = PakoGzipUtils.decode(blockDataJsonGzip);
            int result = bigDataDao.updateById(tableName, id, gzip);
            log.info("id:{}, result:{}", id, result);
        }
    }

    /**
     * 循环压缩字符串，并落库
     */
    @Test
    public void JsonToByteArrayAndUpdate() {
        String tableName = TABLE_NAME;
        List<BigData> bigDataList = bigDataDao.selectAllId(tableName);
        if (CollectionUtil.isEmpty(bigDataList)) {
            return;
        }
        List<String> bigDataIdList = bigDataList.stream().filter(o -> o != null && StringUtils.isNotBlank(o.getId())).map(BigData::getId).collect(Collectors.toList());
        for (String id : bigDataIdList) {
            BigData bigData = bigDataDao.getById(tableName, id);
            String blockDataJson = bigData.getBlockDataJson();
            if (StringUtils.isBlank(blockDataJson)) {
                continue;
            }
            byte[] blockDataJsonGzip = PakoGzipUtils.jsonToByteArray(blockDataJson);
            int result = bigDataDao.updateById(tableName, id, blockDataJsonGzip);
            log.info("id:{}, result:{}", id, result);
        }
    }

    /**
     * 解压字符串
     */
    @Test
    public void byteArrayToJson() {
        String tableName = TABLE_NAME;
        List<BigData> bigDataList = bigDataDao.selectAllId(tableName);
        if (CollectionUtil.isEmpty(bigDataList)) {
            return;
        }
        List<String> bigDataIdList = bigDataList.stream().filter(o -> o != null && StringUtils.isNotBlank(o.getId())).map(BigData::getId).collect(Collectors.toList());
        for (String id : bigDataIdList) {
            BigData bigData = bigDataDao.getById(tableName, id);
            byte[] blockDataJsonGzip = bigData.getBlockDataJsonGzip();
            if (blockDataJsonGzip == null || blockDataJsonGzip.length == 0) {
                continue;
            }
            String blockDataJson = PakoGzipUtils.byteArrayToJson(blockDataJsonGzip);
            log.info("blockDataJson:{}", blockDataJson);
        }
    }

    @Test
    public void getBlockDataJson() {
        String tableName = TABLE_NAME;
        BigData bigData = bigDataDao.getById(tableName,"1729763934858149890");
        byte[] blockDataJsonGzip = bigData.getBlockDataJsonGzip();
        String blockDataJson = PakoGzipUtils.byteArrayToJson(blockDataJsonGzip);
        log.info("blockDataJson:{}", blockDataJson);
    }

}
