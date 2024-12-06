package com.burundibuhire.burundi.buhire;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BurundiBuhireApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurundiBuhireApplication.class, args);
	}

}
