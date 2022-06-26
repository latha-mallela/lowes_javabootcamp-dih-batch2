package com.lowes.bankingapp.fundstransfer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.lowes.bankingapp.fundstransfer.model.FundsTransfer;
import com.lowes.bankingapp.fundstransfer.model.ResponseMessage;
import com.lowes.bankingapp.fundstransfer.service.FundsTransferService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FundsTransferControllerTests {
	
	@Autowired
	TestRestTemplate restTemp;

	@MockBean
	FundsTransferService fundTransService;
	
	List<FundsTransfer> fundTransfers = new ArrayList<>();
	
	
	@BeforeEach
	public void setup() {
		// Initializing Test data
		FundsTransfer fundTransfer1 = new FundsTransfer();
		fundTransfer1.setSrcAccId("1");
		fundTransfer1.setTrgtAccId("2");
		fundTransfer1.setDescription("paid rent");
		fundTransfer1.setAmount(20000);
		fundTransfers.add(fundTransfer1);

		FundsTransfer fundTransfer2 = new FundsTransfer();
		fundTransfer2.setSrcAccId("1");
		fundTransfer2.setTrgtAccId("2");
		fundTransfer2.setDescription("paid fee");
		fundTransfer2.setAmount(15000);
		fundTransfers.add(fundTransfer2);
	}

	@AfterEach
	public void cleanup() {
		fundTransfers.clear();
	}
	
	
	@Test
	public void shouldCreateFundTransfer() throws URISyntaxException {	
		
		String reqBody = "{\"srcAccId\":\"1\",\"trgtAccId\":\"2\",\"description\":\"Savings\",\"amount\":40000}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));

		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);
		
		ResponseMessage responseMsg = response.getBody();
		assertEquals("Success", responseMsg.getStatus());
		assertEquals("Funds Transferred successfully",responseMsg.getMessage());
	}
	
	
	
	@Test
	public void shouldThrowExceptionWhileNotPassingMandatoryDetails() throws URISyntaxException {
		
		String reqBody = "{\"srcAccId\":\"\",\"trgtAccId\":\"2\",\"description\":\"Savings\",\"amount\":40000}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));

		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);
		System.out.println("Response: " + response.getBody());

		ResponseMessage responseMsg = response.getBody();		
		assertEquals("Error: Invalid method arguments", responseMsg.getStatus());
		assertEquals("srcAccId cannot be blank",responseMsg.getErrors().get(0));
	}

}
