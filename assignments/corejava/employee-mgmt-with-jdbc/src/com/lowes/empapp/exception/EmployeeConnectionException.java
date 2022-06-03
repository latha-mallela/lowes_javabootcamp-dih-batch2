package com.lowes.empapp.exception;

import java.sql.SQLException;

public class EmployeeConnectionException extends SQLException {

    public EmployeeConnectionException(){
        System.out.println("Database connection error");
    }
    public EmployeeConnectionException(String message){
        System.out.println(message);
    }


}
