package com.labs.Day4;

//simple Inheritance

/*class A{
	
	public void test1()
	{
		
	}
}

class B extends A{
	public void test2()
	{
		
	}
}*/

// Multi-level Inheritance

/*class A{
	public void test1()
	{
		
	}
	
}
class B extends A{
	public void test2()
	{
		
	}
}
class C extends B{
	public void test3()
	{
		
	}
}*/

//hierarchical inheritance

class A{
	public void test1()
	{
		
	}
	
}
class B extends A{
	public void test2()
	{
		
	}
}
class C extends A{
	public void test3()
	{
		
	}
}

//multiple Inheritance
//not supported
/*
class A{
	public void test1()
	{
		
	}
	
}
class B {
	public void test2()
	{
		
	}
}
class C extends A,B
{
	public void test3()
	{
		
	}
}
*/


public class Demo3 {

	public static void main(String[] args) {
		A a = new A();
		a.test1();
		
		B b = new B();
		b.test1();
		b.test2();
		
		C c = new C();
		c.test1();
		//c.test2();
		c.test3();
				

	}

}
