package com.tcs.hack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class CustomerMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerMicroServiceApplication.class, args);
	}

}

