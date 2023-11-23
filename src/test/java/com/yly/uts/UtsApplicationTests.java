package com.yly.uts;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class UtsApplicationTests {

    @Test
    void test() {
        String fileName = "00002E36_00002AA2_x64.so";
        int extIndex = fileName.indexOf(".");
        log.info("file name:{}, ext:{}", fileName.substring(0, extIndex), fileName.substring(extIndex, fileName.length()));
    }

}
