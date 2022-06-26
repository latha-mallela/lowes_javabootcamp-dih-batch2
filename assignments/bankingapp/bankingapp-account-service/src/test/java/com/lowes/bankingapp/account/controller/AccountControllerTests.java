package com.lowes.bankingapp.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.model.ResponseMessage;
import com.lowes.bankingapp.account.service.AccountService;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTests {

	@Autowired
	TestRestTemplate restTemp;


	@MockBean
	AccountService accountService;

	private static List<Account> accounts = new ArrayList<>();

	@BeforeEach
	public void setup() {
		// Initializing Test data
		Account account1 = new Account();
		account1.setId(1);
		account1.setName("Shruthi");
		account1.setStatus("active");
		account1.setType("savings");
		account1.setBalance(80000);
		accounts.add(account1);

		Account account2 = new Account();
		account2.setId(1);
		account2.setName("Shiva");
		account2.setStatus("active");
		account2.setType("current");
		account2.setBalance(50000);
		accounts.add(account2);
	}

	@AfterEach
	public void cleanup() {
		accounts.clear();
	}

	@Test
	public void shouldCreateAccount() throws URISyntaxException {
		Account account = new Account();
		account.setId(1);
		account.setName("Ramya");
		account.setType("savings");
		account.setStatus("active");
		account.setBalance(50000);
		Mockito.when(accountService.create(Mockito.any(Account.class))).thenReturn(account);

		String reqBody = "{\"id\":\"1\",\"name\":\"Ravi\",\"type\":\"savings\",\"status\":\"active\",\"balance\":\"60000\"}";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));
		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);

		System.out.println("Response: " + response.getBody());

		ResponseMessage responseMsg = response.getBody();
		assertEquals("Success", responseMsg.getStatus());
		assertEquals("Account created successfully", responseMsg.getMessage());
		Assertions.assertThat(response).isNotNull();
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test 
	public void shouldReturnAllAccounts() {

		Mockito.when(accountService.getAll()).thenReturn(accounts);

		ResponseEntity<Object> response = restTemp.getForEntity("/", Object.class);

		List<Account> accounts = (List) response.getBody();

		System.out.println("Response: " + accounts);

		Assertions.assertThat(response).isNotNull();
		Assertions.assertThat(accounts.size()).isEqualTo(2);

	}

	@Test 
	public void shouldThrowExceptionWhenNotPassingMandatoryDetails() throws URISyntaxException {


		String reqBody = "{\"id\":\"1\",\"name\":\"\",\"type\":\"savings\",\"status\":\"active\","
				+ "\"balance\":\"50000\"}";


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		RequestEntity request = new RequestEntity(reqBody, headers, HttpMethod.POST, new URI("/"));


		ResponseEntity<ResponseMessage> response = restTemp.exchange(request, ResponseMessage.class);

		ResponseMessage responseMsg = response.getBody();
		assertEquals("Error: Invalid Method arguments", responseMsg.getStatus());
		assertEquals("name cannot be blank", responseMsg.getErrors().get(0));
	}


}
