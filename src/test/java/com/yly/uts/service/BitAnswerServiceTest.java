package com.yly.uts.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lingyuwang
 * @date 2023-11-23 6:15 下午
 * @since 0.0.1
 **/
public class BitAnswerServiceTest {

    @Autowired
    private BitAnswerService bitAnswerService;

    @Test
    void test() throws Exception {
        bitAnswerService.menuTest();
    }

}
