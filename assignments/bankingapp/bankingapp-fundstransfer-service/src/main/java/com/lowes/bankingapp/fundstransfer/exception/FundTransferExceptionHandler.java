package com.lowes.bankingapp.fundstransfer.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lowes.bankingapp.fundstransfer.model.ResponseMessage;


@ControllerAdvice
public class FundTransferExceptionHandler {

	Logger logger = LoggerFactory.getLogger(FundTransferExceptionHandler.class);

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<com.lowes.bankingapp.fundstransfer.model.ResponseMessage> handleErrors(
			InsufficientBalanceException ex) {
		ResponseMessage response = new ResponseMessage("Error", ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> handleMethodArgumentrrors(MethodArgumentNotValidException ex) {
		logger.info("In MethodArgumentNotValidException " + ex.getBindingResult().toString());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(p -> p.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseMessage response = new ResponseMessage("Error: Invalid method arguments", errors);
		System.out.println("Response data" + response.getStatus() + " " + response.getErrors());
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleGenericErrors(Exception ex) {
		ResponseMessage response = new ResponseMessage("Generic Error", ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

}
