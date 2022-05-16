package com.assignments.assignment1;

public class EvenNumbers {

    public static void main(String[] args) {
        int num = 10, sum = 0;
        /*for (int i=0;i<=num;i++)
        {
            if (i%2 == 0)
            {
                System.out.print(i+" ");
                sum += i;
                System.out.println();
            }
        }*/
        int i = 0;
        while(i <= num)
        {
            if (i%2 == 0)
            {
                System.out.print(i+" ");
                sum += i;
            }
            i++;
        }
        System.out.println();
        System.out.println("Sum of first "+num+" even numbers is: "+sum);
    }

}
