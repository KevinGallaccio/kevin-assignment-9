package com.coderscampus.kevinassignment9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = DataSourceAutoConfiguration.class)
public class KevinAssignment9Application {

	public static void main(String[] args) {
		SpringApplication.run(KevinAssignment9Application.class, args);
	}

}
