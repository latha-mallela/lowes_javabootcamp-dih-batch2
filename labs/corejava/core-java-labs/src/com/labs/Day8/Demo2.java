package com.labs.Day8;


class EmployeeException extends Exception{

	EmployeeException()
	{
		System.out.println("Employee Exception");
	}
}

public class Demo2 {
    public static void main(String[] args) throws EmployeeException {

//        ArithmeticException ex = new ArithmeticException();
//        throw ex;

        //throw new ArithmeticException();
        throw new EmployeeException();
    }
}
