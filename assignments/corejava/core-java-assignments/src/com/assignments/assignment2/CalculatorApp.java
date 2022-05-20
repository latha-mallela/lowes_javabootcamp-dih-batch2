package com.assignments.assignment2;

import java.util.Scanner;

class Calculator
{
	float res = 0;
	public float add(float a, float b)
	{
		res = a + b;
		return res;
	}
	public float subtract(float a, float b)
	{
		res = a - b;
		return res;
	}
	public float multiple(float a, float b)
	{
		res = a * b;
		return res;
	}
	public float divide(float a, float b)
	{
		if(b > 0 )
			res = a / b;
		else System.out.println("denominator cannot be 0 or -ve");
		return res;
	}
}

public class CalculatorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		float a = 0, b = 0;
		int choice = 0;
		String input1 = "";
		String input2 = "";
		float result = 0;
		
		Calculator cal = new Calculator();
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter first number: ");
		input1 = scanner.next();
		System.out.println("Enter second number: ");
		input2 = scanner.next();
		
		if(input1 != null && input2 != null)
		{
			a = Float.parseFloat(input1);
			b = Float.parseFloat(input2);
			
			System.out.println("Select any option:");
			System.out.println("Press 1: Add"+"\n"+"Press 2: Subtract"+"\n"+"Press 3: Multiply"+"\n"+"Press 4 : Divide"+"\n");
			String opt = scanner.next();
			choice = Integer.parseInt(opt);
			
				switch(choice)
				{
					case(1):
						result = cal.add(a,b);
						break;
					case(2):
						result = cal.subtract(a,b);
						break;
					case(3):
						result = cal.multiple(a,b);
						break;
					case(4):
						result = cal.divide(a,b);
						break;
					default:
						System.out.println("Please enter a valid option");
						break;
				}
				System.out.println("Result: "+result);
		}
		else
		{
			System.out.println("Please enter a valid input");
		}
		
		
	}

}
