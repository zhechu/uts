package com.yly.uts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class UtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtsApplication.class, args);
	}

}
