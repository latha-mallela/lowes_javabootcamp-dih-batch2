package com.labs.Day8;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a = 10;
		int b = 2;
		int c = 0;
		
		try {
			c = a/b;
			System.out.println(c);
		}catch(ArithmeticException e)
		{
			System.out.println("Invalid number");
		}catch(Exception e)
		{
			System.out.println("Exception");
		}

	}

}
