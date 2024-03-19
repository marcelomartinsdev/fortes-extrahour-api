package com.example.fortesextrahourapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class FortesExtrahourApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FortesExtrahourApiApplication.class, args);
	}

}
