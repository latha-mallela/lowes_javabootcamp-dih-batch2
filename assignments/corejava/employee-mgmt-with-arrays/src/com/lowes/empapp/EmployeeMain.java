package com.lowes.empapp;

import java.util.Scanner;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.*;
import com.lowes.empapp.service.*;

public class EmployeeMain {

	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EmployeeService empSer = new EmployeeService();

		do {

			System.out.println("**************************************************************************************************");
			System.out.println("####################           Welcome to the Employee Management App           ##################");
			System.out.println("**************************************************************************************************");
			System.out.println("1. Add Employee"+"\n"+"2. View an Employee"+"\n"+"3. Update Employee"+"\n"+"4. View all Employees"+"\n"+"5. Delete Employee"+"\n"+"6. Exit\n");
			System.out.println("Please select an option from the above menu");

			int input = sc.nextInt();

			switch (input) {
			case 1:
			{
				empSer.addEmployee();
				break;
			}
			case 2:
			{
				empSer.viewEmployee();
				break;
			}
			case 3:
			{
				try {
					empSer.updateEmployee();
				} catch (EmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case 4:
			{
				empSer.viewAllEmployee();
				break;
			}
			case 5:
			{
				empSer.DeleteEmployee();
				break;
			}
			case 6:
			{
				System.out.println("THANK YOU!!");
				System.exit(0);
				break;
			}
			default:
				System.out.println("Please enter a valid option");
				break;
			}

		}while(true);
	}
}
