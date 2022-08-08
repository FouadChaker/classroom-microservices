package com.tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TpAlFrontEndServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpAlFrontEndServiceApplication.class, args);
	}

}
