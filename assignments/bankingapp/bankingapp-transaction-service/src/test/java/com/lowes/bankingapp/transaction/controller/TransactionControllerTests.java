package com.lowes.bankingapp.transaction.controller;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.lowes.bankingapp.transaction.model.Transaction;
import com.lowes.bankingapp.transaction.service.TransactionService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTests {
	@Autowired
	TestRestTemplate restTemp;

	@MockBean
	TransactionService transactionService;

	private static List<Transaction> transactions = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initializing Test data
		Transaction transaction1 = new Transaction();
		transaction1.setId(1);
		transaction1.setAccountId(1);
		transaction1.setDescription("paid fee");
		transaction1.setType("credit");
		transaction1.setAmount(5000);
		transactions.add(transaction1);

		Transaction transaction2 = new Transaction();
		transaction2.setId(2);
		transaction2.setAccountId(2);
		transaction2.setDescription("paid fee");
		transaction2.setType("debit");
		transaction2.setAmount(5000);
		transactions.add(transaction2);
	}

	@AfterEach
	public void cleanup() {
		transactions.clear();
	}

	@Test
	public void shouldReturnAllTransactions() {

		Mockito.when(transactionService.getAll()).thenReturn(transactions);
		ResponseEntity<Object> response = restTemp.getForEntity("/", Object.class);

		List<Transaction> transactions = (List) response.getBody();

		System.out.println("Response: " + transactions);

		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(transactions.size()).isEqualTo(2);

	}
}
