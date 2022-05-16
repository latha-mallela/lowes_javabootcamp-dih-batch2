package com.labs.Day2;

class Test{
    static int  y =100;
    int x = 10;

    public void addNumber()
    {
       // static int z = 20; //static variables not allowed inside a method
        int a = 20;
        System.out.println("a = "+a);
    }
    public void foo()
    {
        System.out.println(x);
        System.out.println(y);
    }
}

public class Demo1 {
    public static void main(String[] args) {

        Test obj = new Test();
        System.out.println(obj.x);
        obj.addNumber();
        obj.foo();
        System.out.println(Test.y);

    }
}
