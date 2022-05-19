package com.labs.Day4;

class Parent{
	public Parent(int a)
	{
		System.out.println("Parent class constructor:"+a);
	}
	public void parentMethod()
	{
		System.out.println("Parent class method");
	}
}

class Child extends Parent{
	public Child(int a)
	{
		super(a);
		System.out.println("Child class constructor");
	}
	public void childMethod()
	{
		System.out.println("Child class method");
	}
}
public class Demo2 {

	public static void main(String[] args) {
		
		Parent p = new Parent(200);
		p.parentMethod();
		
		Child c = new Child(100);
		c.childMethod();
		c.parentMethod();

	}

}
