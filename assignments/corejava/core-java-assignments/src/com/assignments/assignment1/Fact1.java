package com.assignments.assignment1;

public class Fact1 {
    public static void main(String[] args) {

        int fact = 1;
        int num = 6;

        for (int i=1; i<=num; i++)
            fact = fact * i;
        System.out.println("Factorial of "+num+" is: "+ fact);
    }
}
