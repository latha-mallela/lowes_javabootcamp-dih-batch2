package com.lowes.empapp;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.service.EmployeeServiceColValImpl;

public class EmployeeManagementMain {

	private static Scanner sc;
	private static EmployeeServiceColValImpl empService;

	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		empService = new EmployeeServiceColValImpl();
		ExecutorService executor = Executors.newCachedThreadPool();
		
		System.out.println("**************************************************************************************************");
		System.out.println("####################           Welcome to the Employee Management App           ##################");
		System.out.println("**************************************************************************************************");
		
		while(true)
		{
			System.out.println("\n1. Add Employee"+"\n"+"2. View an Employee"+"\n"+"3. Update Employee"+"\n"+"4. View all Employees"+"\n"+"5. Delete Employee\n"+"6. Import\n"+"7. Export\n"+"8. Exit\n");
			System.out.print("Please select an option from the above menu: ");
			
			int option = 0;
			try {
				option = Integer.parseInt(sc.next());
			}catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid Input");
				continue;
			}
			
			int empId;
			try
			{
				switch(option)
				{
				case 1:
					addEmployee();
					break;
				case 2:
					System.out.println("Please enter employee id: ");
					empId = sc.nextInt();
					Employee emp = null;
					try
					{
						emp = empService.get(empId);
					}catch(EmployeeException e)
					{
						System.out.println(e.getMessage());
						break;
					}
					printHeader();
					printEmpDetails(emp);
					break;
				case 3:
					System.out.println("Please enter employee Id:");
					empId = sc.nextInt();
					Employee updateEmp = null;
					try {
						updateEmp = empService.get(empId);
					}catch(EmployeeException e) {
						System.out.println(e.getMessage());
						break;
					}
					readEmpDetails(updateEmp);
					empService.update(updateEmp);
					System.out.println("********  Employee updated successfully  ********");
					break;
				case 4:
					List<Employee> employees = empService.getAll();
					printHeader();
					for(Employee employee : employees)
						printEmpDetails(employee);
					break;
				case 5:
					System.out.println("Please enter employee Id:");
					empId = sc.nextInt();
					empService.delete(empId);
					System.out.println("********  Employee record deleted successfully  ********");
					break;
				case 6:
					Future<Boolean> importFutr = executor.submit(new Callable<Boolean>() 
					{
						@Override
						public Boolean call() throws Exception {
							System.out.println("\n"+Thread.currentThread() + " Will start Import process in few secs............");
							Thread.sleep(2000);
							empService.fileImport();
							return true;
						}
					});
					break;
				case 7:
					Future<Boolean> exportFutr = executor.submit(new Callable<Boolean>() 
					{
						@Override
						public Boolean call() throws Exception {
							System.out.println("\n"+Thread.currentThread() + " Will start export process in ew5 secs............");
							Thread.sleep(2000);
							empService.fileExport();
							return true;
						}
					});
					break;
				case 8:
					executor.shutdown();
					System.out.println("THANK YOU!!");
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a valid option!");
					break;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}

		}

	}
	
	private static void printEmpDetails(Employee emp)
	{
		if(emp == null)
			return;
		System.out.format("\n %5d %15s %5d %15s %15s %15s", emp.getEmpId(), emp.getName(), emp.getAge(), emp.getDesignation(), emp.getDepartment(), emp.getCountry());
	}
	
	private static void addEmployee() throws NumberFormatException
	{
		Employee emp = new Employee();
		readEmpDetails(emp);
		boolean status = empService.create(emp);
		if(status)
			System.out.println("********  Employee Added Successfully  ********");
		else
			System.out.println("********  Employee cannot be created   ********");
	}
	
	private static void readEmpDetails(Employee emp) throws NumberFormatException
	{
		System.out.print("Enter Employee name: ");
		emp.setName(sc.next());
		System.out.print("Enter Employee age: ");
		emp.setAge(sc.nextInt());
		System.out.print("Enter Employee Designation: ");
		emp.setDesignation(sc.next());
		System.out.print("Enter Employee Department: ");
		emp.setDepartment(sc.next());
		System.out.print("Enter Employee country: ");
		emp.setCountry(sc.next());
		
	}
	
	private static void printHeader()
	{
		System.out.format("\n%6s %15s %5s %15s %15s %15s", "EmpId", "Name", "Age", "Designation", "Department", "Country");

	}
}
