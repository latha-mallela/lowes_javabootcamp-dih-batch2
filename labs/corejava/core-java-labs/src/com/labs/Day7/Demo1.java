package com.labs.Day7;

public class Demo1 {
	
	static void minNumber(int arrayName[])
	{
		int min = arrayName[0];
		for (int i=1; i<arrayName.length; i++)
		{
			if (min > arrayName[i])
				min = arrayName[i];
		}
		System.out.println("Minimum number of the array: " + min);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {3,5,9,2,4};
		minNumber(a);
		
	}

}
