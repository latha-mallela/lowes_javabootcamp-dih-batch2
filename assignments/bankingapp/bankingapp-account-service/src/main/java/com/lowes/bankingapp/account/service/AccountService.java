package com.lowes.bankingapp.account.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.lowes.bankingapp.account.exception.AccountNotFoundException;
import com.lowes.bankingapp.account.model.Account;
import com.lowes.bankingapp.account.repository.AccountRepository;

@Service
public class AccountService {

	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public Account create(Account account) {
		return accountRepo.save(account);
	}

	public Account get(int id) {
		try {
			return accountRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException("No Account found in the database", e);
		}
	}

	public Account update(int id, Account accNew) {
		try {
			Account accById = accountRepo.findById(id).get();
			accNew.setOpenDate(accById.getOpenDate());
			return accountRepo.save(accNew); 
		} catch (NoSuchElementException e) {
			throw new AccountNotFoundException("No Account found in the database", e);
		}
	}

	public List<Account> getAll() {
		List<Account> accList = accountRepo.findAll();
		if (accList.isEmpty()) {
			throw new AccountNotFoundException("No Account found in the database");
		}
		return accList;
	}

	public void delete(int id) {
		try {
			accountRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new AccountNotFoundException("No Account found in the database", e);
		}
	}


	public void clear() {
		accountRepo.deleteAll();
	}


	@KafkaListener(topics = "FUNDTRANSFER_CREATED", groupId = "account-service")
	public void listenFundTransCreated(ConsumerRecord<?, ?> cr) throws Exception {

		System.out.println("SOP for Fund Transfer Created: " + cr.value());

		String msg = (String) cr.value();
		String[] tokens = msg.split(",");
		String srcAccId = tokens[0];
		String trgtAccId = tokens[1];
		String amount = tokens[2];

		int sourceAccId = Integer.parseInt(srcAccId);
		int targetAccId = Integer.parseInt(trgtAccId);
		double damount = Double.valueOf(amount);

		Account trgtAcc = accountRepo.findById(targetAccId).get();

		if ("active".equals(trgtAcc.getStatus())) {
			Account srcAcc = accountRepo.findById(sourceAccId).get();
			srcAcc.setBalance(srcAcc.getBalance() - damount);
			accountRepo.save(srcAcc);
			trgtAcc.setBalance(trgtAcc.getBalance() + damount);
			accountRepo.save(trgtAcc);
		} else {
			kafkaTemplate.send("ROLLBACK_CREATED", msg);
		}

	}
}
