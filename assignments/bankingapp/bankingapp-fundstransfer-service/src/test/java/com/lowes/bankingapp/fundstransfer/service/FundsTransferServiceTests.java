package com.lowes.bankingapp.fundstransfer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import com.lowes.bankingapp.fundstransfer.exception.InsufficientBalanceException;
import com.lowes.bankingapp.fundstransfer.model.Account;
import com.lowes.bankingapp.fundstransfer.model.FundsTransfer;

@SpringBootTest
public class FundsTransferServiceTests {

	@Autowired
	FundsTransferService fundsTransService;

	@MockBean
	private RestTemplate restTemplate;
	
	@MockBean
	KafkaTemplate<String, String> kafkaTemplate;



	private static List<FundsTransfer> fundsTransfers = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initializing Test data
		FundsTransfer fundTransfer1 = new FundsTransfer();
		fundTransfer1.setSrcAccId("1");
		fundTransfer1.setTrgtAccId("2");
		fundTransfer1.setDescription("paid fees");
		fundTransfer1.setAmount(20000);
		fundsTransfers.add(fundTransfer1);

		FundsTransfer fundTransfer2 = new FundsTransfer();
		fundTransfer2.setSrcAccId("2");
		fundTransfer2.setTrgtAccId("3");
		fundTransfer2.setDescription("loan EMI");
		fundTransfer2.setAmount(50000);
		fundsTransfers.add(fundTransfer2);
	}

	@AfterEach
	public void cleanup() {
		fundsTransfers.clear();
	}

	@Test
	public void shouldCreateFundTransferWhilePassingFundTransferDetails() { 
		FundsTransfer fundTransfer = new FundsTransfer();
		fundTransfer.setSrcAccId("3");
		fundTransfer.setTrgtAccId("4");
		fundTransfer.setDescription("for shopping");
		fundTransfer.setAmount(5000);
		
		Account sourceAccount = new Account();
		sourceAccount.setId(3);
		sourceAccount.setStatus("active");
		sourceAccount.setBalance(10000); 

		ResponseEntity<Account> response = new ResponseEntity<Account>(sourceAccount, HttpStatus.OK);
		Mockito.when(restTemplate.<Account>getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(response);
		Mockito.when(kafkaTemplate.send(Mockito.anyString(),Mockito.anyString())).thenReturn(null);
		FundsTransfer fundTransferCreated = fundsTransService.create(fundTransfer);
		assertNotNull(fundTransferCreated);
		assertEquals(fundTransfer.getSrcAccId(), fundTransferCreated.getSrcAccId());

	}

	@Test
	public void shouldThrowExceptionWhenSourceAccountHasInsufficientBalance() {

		FundsTransfer fundsTransfer = new FundsTransfer();
		fundsTransfer.setSrcAccId("3");
		fundsTransfer.setTrgtAccId("4");
		fundsTransfer.setDescription("for shopping");
		fundsTransfer.setAmount(5000);

		Account sourceAccount = new Account();
		sourceAccount.setId(3);
		sourceAccount.setStatus("active");
		sourceAccount.setBalance(0); 

		ResponseEntity<Account> response = new ResponseEntity<Account>(sourceAccount, HttpStatus.OK);
		Mockito.when(restTemplate.<Account>getForEntity(Mockito.anyString(), Mockito.any())).thenReturn(response);
		assertThrows(InsufficientBalanceException.class, () -> fundsTransService.create(fundsTransfer));

	}

}
