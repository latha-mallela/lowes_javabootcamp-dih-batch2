package com.labs.Day6;

public class Demo8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = {1,2,3,4,5,6,7}; //declaration, instantiation and initialization
		
		for(int i=0; i<a.length; i++)
			System.out.println(a[i]);
		
		System.out.println("*********************************************");
		
		for(int number : a)
			System.out.println(number);
	}

}
