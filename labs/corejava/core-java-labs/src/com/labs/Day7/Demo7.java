package com.labs.Day7;

public class Demo7 {

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
              c[i][j] = 0;
              for (int z = 0; z < 3; z++) {
                  c[i][j] = a[i][z] + b[z][j];
              }
              // end the z loop
              System.out.print(c[i][j] + " ");
          }
          System.out.println();
      }
	}

}
