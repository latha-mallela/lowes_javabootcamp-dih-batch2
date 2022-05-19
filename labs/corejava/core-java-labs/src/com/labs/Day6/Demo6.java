package com.labs.Day6;

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Performance Test");
		long startTime = System.currentTimeMillis();

		System.out.println("*****************************************************");
		//Thread safe, not  performance efficient
		
		StringBuffer stringBuffer =  new StringBuffer("Hello");
		for(int i=0; i<100000; i++)
			stringBuffer.append("World");
		System.out.println("Time taken by StringBuffer: "+(System.currentTimeMillis() - startTime +" ms"));
		
		System.out.println("*****************************************************");
		
		//Not Thread safe, better performance
		
		startTime = System.currentTimeMillis();
		StringBuilder stringBuilder =  new StringBuilder("Hello");
		for(int i=0; i<100000; i++)
			stringBuilder.append("World");
		System.out.println("Time taken by StringBuilder: "+(System.currentTimeMillis() - startTime + " ms"));
		
		System.out.println("******************************************************");
	}

}
