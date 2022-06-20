package com.lowes.empapp.exception;

import com.lowes.empapp.model.ResponseMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class EmpExceptionHandler{

    Logger logger = LoggerFactory.getLogger(EmpExceptionHandler.class);

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ResponseMessage> handleErrors(EmployeeException ex) {
        ResponseMessage response = new ResponseMessage("Error", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
	
	@ExceptionHandler(CannotCreateTransactionException.class)
	public ResponseEntity<ResponseMessage> handleDatabaseConnectionErrors(CannotCreateTransactionException ex) {
		ResponseMessage responseMessage = new ResponseMessage("Error", "Error connecting to the database");
		return ResponseEntity.internalServerError().body(responseMessage);
	}

			
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> handleMethodArgumentrrors(MethodArgumentNotValidException ex) {
		//System.out.println("In MethodArgumentNotValidException " + ex.getErrorCount());
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(p -> p.getDefaultMessage())
				.collect(Collectors.toList());
		ResponseMessage response = new ResponseMessage("Error: No proper method arguments", errors);
		//System.out.println("Response data" + response.getStatus() + " " + response.getErrors());
		return ResponseEntity.internalServerError().body(response);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericErrors(Exception ex) {
        ResponseMessage response = new ResponseMessage("Other errors", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
