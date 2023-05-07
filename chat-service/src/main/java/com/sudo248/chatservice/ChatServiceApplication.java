package com.sudo248.chatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatServiceApplication.class, args);
	}

}
