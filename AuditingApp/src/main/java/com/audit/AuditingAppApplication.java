package com.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuditingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditingAppApplication.class, args);
		System.out.print("Nathan project starter");
	}

}
