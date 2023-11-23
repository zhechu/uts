package com.yly.uts.service;

import com.bitanswer.library.BitAnswer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingyuwang
 * @date 2023-11-17 9:25 上午
 * @since 0.0.1
 **/
//@Service
@Slf4j
public class BitAnswerService {

    public void menuTest() throws BitAnswer.BitAnswerException {
        log.info("Bitanswer client library sample for Java.");
        log.info("http://www.bitanswer.cn");

        BitAnswer bitAnswer = new BitAnswer();
        BitAnswer bitAnswer1 = new BitAnswer();
        BitAnswer bitAnswer2 = new BitAnswer();
        BitAnswer bitAnswer3 = new BitAnswer();
        BitAnswer bitAnswer4 = new BitAnswer();
        bitAnswer4.login(null, "", BitAnswer.LoginMode.AUTO);
        log.info("Login success.");

        log.info("\nProgram exit.");
    }

}
