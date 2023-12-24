package com.yly.uts.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.util.ZipUtil;
import com.yly.uts.core.model.UtsTag;
import com.yly.uts.dao.UtsTagDao;
import com.yly.uts.util.PakoGzipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@SpringBootTest
@Slf4j
public class CalculationProcessTmpTest {

    @Resource
    private UtsTagDao utsTagDao;

    /**
     * 转换
     */
    @Test
    public void utf8ArrayToByteArray() {
        List<UtsTag> utsTags = utsTagDao.selectAllId();
        if (CollectionUtil.isEmpty(utsTags)) {
            return;
        }
        for (UtsTag utsTag : utsTags) {
            if (utsTag == null || StringUtils.isBlank(utsTag.getId())) {
                continue;
            }
            UtsTag c = utsTagDao.getById(utsTag.getId());
            byte[] blockData = c.getBlockData();
            if (blockData == null || blockData.length == 0) {
                continue;
            }
            byte[] gzip = PakoGzipUtils.decode(blockData);
            int result = utsTagDao.updateById(c.getId(), gzip);
            log.info("id:{}, result:{}", c.getId(), result);
        }
    }

    /**
     * 循环压缩字符串
     */
    @Test
    public void fillBlockDataByForEachToRaw() {
        List<UtsTag> utsTags = utsTagDao.selectAllId();
        if (CollectionUtil.isEmpty(utsTags)) {
            return;
        }
        for (UtsTag utsTag : utsTags) {
            if (utsTag == null || StringUtils.isBlank(utsTag.getId())) {
                continue;
            }
            UtsTag c = utsTagDao.getById(utsTag.getId());
            String blockDataJson = c.getBlockDataJson();
            if (blockDataJson == null) {
                continue;
            }
            byte[] gzip = PakoGzipUtils.jsonToByteArray(blockDataJson);
            int result = utsTagDao.updateById(c.getId(), gzip);
            log.info("id:{}, result:{}", c.getId(), result);
        }
    }

    /**
     * 循环压缩字符串
     */
    @Test
    public void fillBlockDataByForEach() {
        List<UtsTag> utsTags = utsTagDao.selectAllId();
        if (CollectionUtil.isEmpty(utsTags)) {
            return;
        }
        for (UtsTag utsTag : utsTags) {
            if (utsTag == null || StringUtils.isBlank(utsTag.getId())) {
                continue;
            }
            UtsTag c = utsTagDao.getById(utsTag.getId());
            String blockDataJson = c.getBlockDataJson();
            if (blockDataJson == null) {
                continue;
            }
            byte[] gzip = PakoGzipUtils.jsonToUint8Array(blockDataJson);
            int result = utsTagDao.updateById(c.getId(), gzip);
            log.info("id:{}, result:{}", c.getId(), result);
        }
    }

    /**
     * 循环压缩字符串
     */
    @Test
    public void fillBlockDataByForEachRaw() {
        List<UtsTag> utsTags = utsTagDao.selectAllId();
        if (CollectionUtil.isEmpty(utsTags)) {
            return;
        }
        for (UtsTag utsTag : utsTags) {
            if (utsTag == null || StringUtils.isBlank(utsTag.getId())) {
                continue;
            }
            UtsTag c = utsTagDao.getById(utsTag.getId());
            String blockDataJson = c.getBlockDataJson();
            if (blockDataJson == null) {
                continue;
            }
            byte[] gzip = ZipUtil.gzip(blockDataJson.getBytes(StandardCharsets.UTF_8));
            int result = utsTagDao.updateById(c.getId(), gzip);
            log.info("id:{}, result:{}", c.getId(), result);
        }
    }

    /**
     * 压缩字符串
     */
    @Test
    public void fillBlockData() {
        List<UtsTag> calculationProcessTmpList = utsTagDao.selectAll();
        calculationProcessTmpList.stream().forEach(c -> {
//            log.info("calculationProcessTmp:{}", c);
            String blockDataJson = c.getBlockDataJson();
            if (blockDataJson == null) {
                return;
            }
            byte[] gzip = ZipUtil.gzip(blockDataJson.getBytes(StandardCharsets.UTF_8));
            int result = utsTagDao.updateById(c.getId(), gzip);
            log.info("result:{}", result);
        });
    }

    /**
     * 解压字符串
     */
    @Test
    public void fillBlockDataJson() {
        List<UtsTag> calculationProcessTmpList = utsTagDao.selectAll();
        calculationProcessTmpList.stream().forEach(c -> {
            byte[] blockData = c.getBlockData();
            if (blockData == null || blockData.length == 0) {
                return;
            }
            blockData = ZipUtil.unGzip(blockData);
            String blockDataJson = new String(blockData, StandardCharsets.UTF_8);
            int result = utsTagDao.updateBlockDataJsonById(c.getId(), blockDataJson);
            log.info("result:{}", result);
        });
    }

    @Test
    public void selectBlockDataJson() {
//        for (int i = 0; i < 10; i++) {
            long beginTime = System.currentTimeMillis();
            List<UtsTag> calculationProcessTmpList = utsTagDao.selectBlockDataJson();
            log.info("查询耗时：{}毫秒，总条数：{}", System.currentTimeMillis() - beginTime, calculationProcessTmpList.size());
//        }
    }

    @Test
    public void selectBlockData() {
        long beginTime = System.currentTimeMillis();
        List<UtsTag> calculationProcessTmpList = utsTagDao.selectBlockData();
        log.info("查询耗时：{}毫秒，总条数：{}", System.currentTimeMillis() - beginTime, calculationProcessTmpList.size());

        for (UtsTag utsTag : calculationProcessTmpList) {
            byte[] blockData = utsTag.getBlockData();
            blockData = PakoGzipUtils.decode(blockData);
            String base64Str = Base64.getEncoder().encodeToString(blockData);
            log.info("blockData:{}, base64Str:{}", blockData.length, base64Str.length());
        }
    }

    @Test
    public void getBlockDataJson() {
        UtsTag utsTag = utsTagDao.getById("1729763934858149890");
        byte[] blockData = utsTag.getBlockData();
        String blockDataJson = PakoGzipUtils.uint8ArrayToJson(blockData);
        log.info("blockDataJson:{}", blockDataJson);
    }

    @Test
    public void getBlockDataJsonRaw() {
        UtsTag utsTag = utsTagDao.getById("1729763934858149890");
        byte[] blockData = utsTag.getBlockData();

        String blockDataJson = new String(ZipUtil.unGzip(blockData), StandardCharsets.UTF_8);
        blockDataJson = URLDecoder.decode(blockDataJson, StandardCharsets.UTF_8);
        log.info("blockDataJson:{}", blockDataJson);
    }

}
