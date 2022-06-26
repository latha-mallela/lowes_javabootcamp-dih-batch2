package com.lowes.bankingapp.fundstransfer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class FundsTransferConfig {
	@Autowired
	@Bean
	public RestTemplate accountRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();

	}

}