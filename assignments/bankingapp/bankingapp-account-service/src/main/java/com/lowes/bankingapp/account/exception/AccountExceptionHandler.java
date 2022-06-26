package com.lowes.bankingapp.account.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lowes.bankingapp.account.model.ResponseMessage;



@ControllerAdvice
public class AccountExceptionHandler {

	Logger logger = LoggerFactory.getLogger(AccountExceptionHandler.class);

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<com.lowes.bankingapp.account.model.ResponseMessage> handleErrors(
			AccountNotFoundException ex) {
		ResponseMessage response = new ResponseMessage("Error ", ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> handleMethodArgumentErrors(MethodArgumentNotValidException ex) {
		logger.info("In MethodArgumentNotValidException " + ex.getBindingResult().toString());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(p -> p.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseMessage response = new ResponseMessage("Error: Invalid Method arguments", errors);
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(CannotCreateTransactionException.class)
	public ResponseEntity<ResponseMessage> handleDatabaseConnectionErrors(CannotCreateTransactionException ex) {
		ResponseMessage response = new ResponseMessage("Db Error", "Database Connection Error");
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleGenericErrors(Exception ex) {
		ResponseMessage response = new ResponseMessage("Generic Error", ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

}