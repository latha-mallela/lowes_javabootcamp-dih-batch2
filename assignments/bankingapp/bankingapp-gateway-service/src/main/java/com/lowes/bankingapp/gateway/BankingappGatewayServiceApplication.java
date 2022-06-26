package com.lowes.bankingapp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class BankingappGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingappGatewayServiceApplication.class, args);
	}

}
