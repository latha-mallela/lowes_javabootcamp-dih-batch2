package com.lowes.bankingapp.transaction.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lowes.bankingapp.transaction.model.ResponseMessage;


@ControllerAdvice
public class TransactionExceptionHandler {

	Logger logger = LoggerFactory.getLogger(TransactionExceptionHandler.class);

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<com.lowes.bankingapp.transaction.model.ResponseMessage> handleErrors(
			TransactionNotFoundException ex) {
		ResponseMessage response = new ResponseMessage("Errors ", ex.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> handleMethodArgumentrrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(p -> p.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseMessage response = new ResponseMessage("Error: Invalid method arguments", errors);
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
