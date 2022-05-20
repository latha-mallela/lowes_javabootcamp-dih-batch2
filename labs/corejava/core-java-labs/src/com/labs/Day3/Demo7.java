package com.labs.Day3;

class Test{
    public Test()
    {
        System.out.println("Default constructor");
    }
    public Test(int a)
    {
        System.out.println("Parameterized constructor");
    }
    public void display()
    {
        System.out.println("display method");
    }
}
public class Demo7 {
    public static void main(String[] args) {
        Test test = new Test(10);
    }
}
