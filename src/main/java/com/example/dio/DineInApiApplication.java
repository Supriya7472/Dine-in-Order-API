package com.example.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan("com.example.dio")
@SpringBootApplication
@EnableJpaAuditing
public class DineInApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DineInApiApplication.class, args);
	}

}
