package com.labs.Day7;

public class Demo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[] sourceArray = {'h','e','l','l','o'};
		
		char[] destArray = new char[5];
		
		System.arraycopy(sourceArray, 0, destArray, 0, 5);
		System.out.println(String.valueOf(destArray));
	}

}
