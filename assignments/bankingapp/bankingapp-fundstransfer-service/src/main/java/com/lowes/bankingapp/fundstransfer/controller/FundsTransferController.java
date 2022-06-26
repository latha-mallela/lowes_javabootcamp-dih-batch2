package com.lowes.bankingapp.fundstransfer.controller;

import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lowes.bankingapp.fundstransfer.model.FundsTransfer;
import com.lowes.bankingapp.fundstransfer.model.ResponseMessage;
import com.lowes.bankingapp.fundstransfer.service.FundsTransferService;

@RestController
public class FundsTransferController {

	Logger logger = LoggerFactory.getLogger(FundsTransferController.class);

	@Autowired
	FundsTransferService fundsTransService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseMessage> create(@Valid @RequestBody FundsTransfer fundsTransfer)
			throws URISyntaxException {
		fundsTransService.create(fundsTransfer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		ResponseMessage response = new ResponseMessage("Success", "Funds Transferred successfully");
		return ResponseEntity.created(location).body(response);
	}
}
