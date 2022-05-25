package com.lowes.empapp.service;

import java.util.Scanner;
import com.lowes.empapp.exception.*;
import com.lowes.empapp.model.*;

public class EmployeeService {
	
	Scanner sc = new Scanner(System.in);
	Employee empArr[] = new Employee[10];
	int count=0;
	Employee emp;//= new Employee();
	
	public void addEmployee()
	{		
		emp = new Employee();
		
		System.out.println("Enter Employee Id: ");
		emp.setEmpId(sc.nextInt());
		
		System.out.println("Enter Employee Name: ");
		emp.setName(sc.next());
		
		System.out.println("Enter Employee Designation: ");
		emp.setDesignation(sc.next());
		
		System.out.println("Enter Salary: ");
		emp.setSalary(sc.nextInt());
		
		System.out.println("Enter Employee location:  ");
		emp.setLocation(sc.next());
		
		System.out.println("Enter Email Id: ");
		emp.setEmail(sc.next());
		
		empArr[count] = emp; //addition of employee
        count++;
        
        System.out.println("Employee added succesfully.......");
	}



	public void viewEmployee() {
		
		boolean isPresent = false;
		System.out.println("Enter Employee ID:");
        if (sc.hasNextInt())  
        {
            int id = sc.nextInt();
            if(empArr[0] == null)
            	System.out.println("No Employee records to display");
            else
            {
            	for (int i = 0; i < empArr.length; i++) 
            	{
            		if(empArr[i] != null)
            		{
            			if (id == (empArr[i].getEmpId()))
            			{
            				isPresent = true;
            				System.out.println(empArr[i].getEmpId() + " " + empArr[i].getName() + " " +  empArr[i].getDesignation() + " " +empArr[i].getSalary()+ " "+empArr[i].getLocation() + " " +empArr[i].getEmail());
            				break;
            			}
            		}

            	}
    			if(!isPresent)
    				System.out.println("No employee record found");
            }
        } else System.out.println("Please enter a valid Input");
        	
		
	}



	public void updateEmployee() throws EmployeeException{
		
		boolean isUpdated = false;
		System.out.println("Enter Employee ID:");
        if (sc.hasNextInt()) 
        {
            if(empArr[0] == null)
            	System.out.println("No Employee records to update");
            else
            {
            	int id = sc.nextInt();

            	for (int i = 0; i < empArr.length; i++)
            	{
            		if(empArr != null)
            		{
            			if (id == (empArr[i].getEmpId()))
            			{
            				emp = new Employee();
            				emp.setEmpId(id);

            				System.out.println("Enter Employee Name: ");
            				emp.setName(sc.next());

            				System.out.println("Enter Employee Designation: ");
            				emp.setDesignation(sc.next());

            				System.out.println("Enter Salary: ");
            				emp.setSalary(sc.nextInt());

            				System.out.println("Enter Employee location:  ");
            				emp.setLocation(sc.next());

            				System.out.println("Enter Email Id: ");
            				emp.setEmail(sc.next());

            				empArr[i] = emp;
            				isUpdated = true;
            				System.out.println("Employee details updated..............");
            				break;
            			}
            			
            		}
            	}
            	if(!isUpdated)
        			System.out.println("No employee record found");
            	
            }
        }
        else
        	throw new EmployeeException();
		
	}



	public void viewAllEmployee() {
        if(empArr[0] == null)
        	System.out.println("No Employee records to display, Please select 1.Add Employee from the menu");
        else
        {

        	for (int j = 0; j < empArr.length; j++) 
        	{
        		if(empArr[j] != null)
        		{
        			System.out.println(empArr[j].getEmpId() + " " + empArr[j].getName() + " " +  empArr[j].getDesignation() + " " +empArr[j].getSalary()+ " "+empArr[j].getLocation() + " " +empArr[j].getEmail());
        		}
        	}
        }
		
	}



	public void DeleteEmployee() {
		boolean isDeleted = false;
		System.out.println("Enter Employee ID");
        if (sc.hasNextInt()) 
        {
            if(empArr[0] == null)
            	System.out.println("No Employee records to display");
            else
            {
            	int id = sc.nextInt();

            	for (int i = 0; i < empArr.length; i++) 
            	{
            		if(empArr[i] != null)
            		{
            			if (id == (empArr[i].getEmpId())) 
            			{
            				if (i == (empArr.length - 1)) 
            				{
            					//if Emp Id is in last index.
            				}
            				else 
            				{ 
            					// push all rows upwards for deletion
            					for (int j = i; j < empArr.length-1; j++)
            					{
            						empArr[j] = empArr[j+1];
            					}
            					isDeleted = true;
            				}
            				//empArr.length = empArr.length-1;
            			}
            		}
            	}
            	if(isDeleted)
            		System.out.println("Employee deleted");
            	else
            		System.out.println("No employee record found");
            }
        }

	}
	

}
