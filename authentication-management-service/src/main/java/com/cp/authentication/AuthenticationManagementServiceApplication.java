package com.cp.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cp.*")
public class AuthenticationManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationManagementServiceApplication.class, args);
	}

}
