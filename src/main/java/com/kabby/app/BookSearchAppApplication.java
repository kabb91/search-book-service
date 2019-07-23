package com.kabby.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class BookSearchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchAppApplication.class, args);
	}

}
