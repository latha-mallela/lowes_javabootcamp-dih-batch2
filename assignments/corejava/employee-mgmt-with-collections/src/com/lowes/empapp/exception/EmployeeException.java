package com.lowes.empapp.exception;

public class EmployeeException extends Exception{
    public EmployeeException(){
        System.out.println("Enter a valid Input");
    }
    public EmployeeException(String message){
        System.out.println(message);
    }

}