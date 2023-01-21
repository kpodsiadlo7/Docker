package com.example.FamilyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;

@SpringBootApplication
public class FamilyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyAppApplication.class, args);
	}

}
