package com.lowes.empapp.exception;

import com.lowes.empapp.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmpExceptionHandler {

    Logger logger = LoggerFactory.getLogger(EmpExceptionHandler.class);

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ResponseMessage> handleErrors(EmployeeException ex) {
        ResponseMessage response = new ResponseMessage("Error", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<ResponseMessage> handleInputErrors(InputValidationException ex) {
        ResponseMessage response = new ResponseMessage("Input error", ex.getErrors());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(DbConnectionException.class)
    public ResponseEntity<ResponseMessage> handleDatabaseErrors(DbConnectionException ex) {
        ResponseMessage response = new ResponseMessage("DB error", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericErrors(Exception ex) {
        ResponseMessage response = new ResponseMessage("Other errors", ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
