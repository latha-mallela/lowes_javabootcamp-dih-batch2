package com.lowes.empapp.exception;



public class EmployeeValidationException extends Exception{
    public EmployeeValidationException(){
        System.out.println("Enter a valid Input");
    }
    public EmployeeValidationException(String message){
        System.out.println(message);
    }

}

