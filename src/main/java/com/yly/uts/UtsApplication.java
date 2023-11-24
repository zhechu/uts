package com.yly.uts;

import com.yly.uts.service.BitAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class UtsApplication {

	@Autowired
	private BitAnswerService bitAnswerService;

	public static void main(String[] args) {
		SpringApplication.run(UtsApplication.class, args);
	}

	@EventListener
	public void deploymentVer(ApplicationReadyEvent event) throws Exception {
		log.info("上下文已经准备完毕的时候触发>>>" + event.toString());

		bitAnswerService.menuTest();
	}

}
