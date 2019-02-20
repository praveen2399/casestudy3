package com.hackerrank.springhackeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class SpringHackEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHackEurekaserverApplication.class, args);
	}

}
