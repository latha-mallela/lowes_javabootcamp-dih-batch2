package com.assignments.assignment1;

public class Fact2 {
    public static void main(String[] args) {

        int fact = 1, i=1;
        int num = 4;

        while(i <= num) {
            fact = fact * i;
            i++;
        }
        System.out.println("Factorial of "+num+" is: "+ fact);
    }
}
