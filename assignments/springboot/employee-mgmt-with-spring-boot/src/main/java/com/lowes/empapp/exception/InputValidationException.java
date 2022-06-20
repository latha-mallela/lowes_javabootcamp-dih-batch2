package com.lowes.empapp.exception;

import java.util.List;

public class InputValidationException extends RuntimeException {
    private List<String> errors;

    public InputValidationException(String message) {
        super(message);
    }
    
    public InputValidationException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

}
