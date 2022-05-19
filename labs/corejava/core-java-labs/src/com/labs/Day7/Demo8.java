package com.labs.Day7;

public class Demo8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create two matrix
        int a[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int b[][] = {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}};

        // creating another matrix to store the result
        int c[][] = new int[3][3]; // 3 rows and 3 columns

        // addition
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                c[i][j] = a[i][j] + b[i][j];


                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
	}

}
