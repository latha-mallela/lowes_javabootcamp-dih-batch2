package com.lowes.bankingapp.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class BankingappAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingappAdminServiceApplication.class, args);
	}

}
