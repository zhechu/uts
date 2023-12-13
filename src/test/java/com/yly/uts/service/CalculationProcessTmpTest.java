package com.yly.uts.service;

import cn.hutool.core.util.ZipUtil;
import com.yly.uts.core.model.UtsTag;
import com.yly.uts.dao.UtsTagDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@Slf4j
public class CalculationProcessTmpTest {

    @Resource
    private UtsTagDao utsTagDao;

    /**
     * 压缩字符串
     */
    @Test
    public void fillBlockData() {
        List<UtsTag> calculationProcessTmpList = utsTagDao.selectAll();
        calculationProcessTmpList.stream().forEach(c -> {
            log.info("calculationProcessTmp:{}", c);
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
    }

}
