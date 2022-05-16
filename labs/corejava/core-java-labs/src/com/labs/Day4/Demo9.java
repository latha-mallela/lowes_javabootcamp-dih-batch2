package com.labs.Day4;

class Car{
    public final int speedLimit = 50;

    public void drive()
    {
        System.out.println("Car is at a speed of: "+speedLimit);
    }
}
public class Demo9 {
    public static void main(String[] args) {
        Car car = new Car();
       // car.speedLimit = 60;
        car.drive();
    }
}
