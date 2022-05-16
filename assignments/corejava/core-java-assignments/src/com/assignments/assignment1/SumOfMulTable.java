package com.assignments.assignment1;

public class SumOfMulTable {
    public static void main(String[] args) {

        int num = 8, sum = 0;
        for (int i=1; i<=10; i++)
        {
            sum += num*i;
        }
        System.out.println("Sum of the numbers in the table "+num+" is: "+sum);
    }
}
