package com.cloudycat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cloudycat"})

public class CloudyCatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudyCatApplication.class, args);
	}
}
