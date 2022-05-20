package com.labs.Day3;

class Student{

    public Student()
    {
    	System.out.println("1st constructor");
    }
	public Student(int a)
	{
		System.out.println("2nd constructor");
	}
    public Student(String a)
    {
    	System.out.println("3rd constructor");
    }
    public Student(int a, String b)
    {
    	System.out.println("4th constructor");
    }
    public Student(String a, int b)
    {
    	System.out.println("5th constructor");
    }

}
public class Demo8 {
	public static void main(String args[])
	{
		Student student = new Student();
		Student student1 = new Student(10);
		Student student2 = new Student("S");
		Student student3 = new Student(20,"L");
		Student student4 = new Student("M",30);
		
	}
}
