package com.lowes.bankingapp.fundstransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowes.bankingapp.fundstransfer.exception.InsufficientBalanceException;
import com.lowes.bankingapp.fundstransfer.model.Account;
import com.lowes.bankingapp.fundstransfer.model.FundsTransfer;

@Service
public class FundsTransferService {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	RestTemplate accRestTemplate;
	

	public FundsTransfer create(FundsTransfer fundsTransfer) {
		
		//provide gateway-service Ip at localhost
		Account account = accRestTemplate.getForEntity(
				"http://172.17.0.3:8111/banking/api/accounts/" + fundsTransfer.getSrcAccId(), Account.class).getBody();
		if (fundsTransfer.getAmount() <= account.getBalance() && "active".equals(account.getStatus())) {
			String fundsTransDetails = fundsTransfer.getSrcAccId() + "," + fundsTransfer.getTrgtAccId() + ","
					+ fundsTransfer.getAmount() + "," + fundsTransfer.getDescription();
			kafkaTemplate.send("FUNDTRANSFER_CREATED", fundsTransDetails); 
			return fundsTransfer;
		} else {
			throw new InsufficientBalanceException(
					"No sufficient balance for transaction/Source account should in active status");
		}

	}


}
