package com.labs.Day7;

public class Demo6 {

	public static void main(String args[])
	{
		int arr[] = {1,2,3,4};
		
		System.out.println("Printing original array.....");
		for (int i:arr)
			System.out.print(i+" ");
		System.out.println();
		
		int newArr[] = arr.clone();
		System.out.println("Printing cloned array......");
		for (int i:newArr)
			System.out.print(i+" ");
		System.out.println();
		
		System.out.println(arr == newArr); //comparing both the arrays
	}
}
