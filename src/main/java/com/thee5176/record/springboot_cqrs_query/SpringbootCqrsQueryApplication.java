package com.thee5176.record.springboot_cqrs_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.thee5176.record.springboot_cqrs_query.domain")
@EntityScan("com.thee5176.record.springboot_cqrs_query.model")
public class SpringbootCqrsQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCqrsQueryApplication.class, args);
	}

}
