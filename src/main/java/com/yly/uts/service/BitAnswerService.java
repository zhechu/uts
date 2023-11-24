package com.yly.uts.service;

import com.bitanswer.library.BitAnswer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author lingyuwang
 * @date 2023-11-17 9:25 上午
 * @since 0.0.1
 **/
@Service
@Slf4j
public class BitAnswerService {

    @PostConstruct
    public void menuTest() {
        log.info("Bitanswer client library sample for Java.");
        log.info("http://www.bitanswer.cn");

        BitAnswer bitAnswer = new BitAnswer();
        try {
            bitAnswer.login(null, "Q3KZ5JLF5IXDNCLG", BitAnswer.LoginMode.AUTO);
        } catch (BitAnswer.BitAnswerException e) {
            log.error("Bit授权错误码:{}", e.getErrorCode());
            throw new RuntimeException(e);
        }
        log.info("Login success.");

        log.info("\nProgram exit.");
    }

}
