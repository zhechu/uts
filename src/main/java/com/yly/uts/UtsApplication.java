package com.yly.uts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class UtsApplication {
//
//	@Autowired
//	private BitAnswerService bitAnswerService;

	public static void main(String[] args) {
		SpringApplication.run(UtsApplication.class, args);
	}
//
//	@EventListener
//	public void deploymentVer(ApplicationReadyEvent event) throws Exception {
//		log.info("上下文已经准备完毕的时候触发>>>" + event.toString());
//
//		String javaLibraryPath = System.getProperty("java.library.path");
//		String classpath = System.getProperty("java.class.path");
//		log.info("java.library.path={}", javaLibraryPath);
//		log.info("classpath={}", classpath);
//
//		bitAnswerService.menuTest();
//	}

}
