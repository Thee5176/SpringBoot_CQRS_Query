package com.thee5176.record.springboot_cqrs_query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.thee5176.record.springboot_cqrs_query.Infrastructure.repository")
@EntityScan("com.thee5176.record.springboot_cqrs_query.Domain.model")
@ComponentScan
public class SpringbootCqrsQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCqrsQueryApplication.class, args);
	}

}
