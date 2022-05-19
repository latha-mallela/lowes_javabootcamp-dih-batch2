package com.labs.Day6;

public class Demo3 {
	public static void main(String args[])
	{
		StringBuffer sb = new StringBuffer("Hello");
		System.out.println(sb);
		
		//append
/*		sb.append("World");
		System.out.println(sb);
*/		
		//insert
/*		sb.insert(0, "World");
		System.out.println(sb);
*/
		//replace
/*		sb.replace(1, 3, "World");
		System.out.println(sb);
*/
		//delete
		/*
		sb.delete(1	,3);
		System.out.println(sb);
		*/
		
		//reverse
		sb.reverse();
		System.out.println(sb);
		
	}

}
