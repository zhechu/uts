package com.yly.uts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class UtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtsApplication.class, args);
	}

}
