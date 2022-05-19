package com.labs.Day6;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		System.out.println(sb.capacity());
		
		sb.append("Hello");
		System.out.println(sb.capacity());
		
		sb.append("Welcome to the Java class");
		System.out.println(sb.capacity());
		
		/*
		 * default capacity = 16;
		 * 
		 * (oldcapacity * 2) + 2;
		 * 
		 * (16 * 2) + 2 = 34;
		 * 
		 * 	 */

	}

}
