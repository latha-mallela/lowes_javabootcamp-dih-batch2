package com.labs.Day7;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[][] = {  {1,2,3}, {10,20,30}, {21,22,23}  };
		
		//printing multi dimensional array
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}
