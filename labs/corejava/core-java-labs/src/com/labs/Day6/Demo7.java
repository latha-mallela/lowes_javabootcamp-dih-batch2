package com.labs.Day6;

public class Demo7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = new int[3]; //declaration and instantiation
		a[0] = 10; //initialization
		a[1] = 20;
		a[2] = 30;
		//a[3] = 40; // array index out of bounds exception
		
		
		for (int i=0; i<a.length; i++)
			System.out.println(a[i]);
		
	}

}
