package com.lowes.empapp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeValidationException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.service.EmployeeServiceJdbcImpl;

public class EmployeeManagementMain {

	private static Scanner sc;
	private static EmployeeServiceJdbcImpl empService;
	private static boolean updateFlag = false;

	public static void main(String[] args) throws EmployeeConnectionException, EmployeeException {
		
		sc = new Scanner(System.in);
		empService = new EmployeeServiceJdbcImpl();
		ExecutorService executor = Executors.newCachedThreadPool();
		
		System.out.println("**************************************************************************************************");
		System.out.println("####################           Welcome to the Employee Management App           ##################");
		System.out.println("**************************************************************************************************");
		
		while(true)
		{
			System.out.println("\n1. Add Employee"+"\n"+"2. View an Employee"+"\n"+
								"3. Update Employee"+"\n"+"4. View all Employees"+"\n"+
								"5. Delete Employee\n"+"6. Print Statistics\n"+"7. Import\n"+
								"8. Export\n"+"9. Exit\n");
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
					Employee emp = empService.get(empId);
     				printHeader();
					printEmpDetails(emp);
					break;
				case 3:
					System.out.println("Please enter employee Id:");
					empId = sc.nextInt();
					Employee updateEmp = empService.get(empId);
					updateFlag = true;
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
					printStatistics();
					break;					
				case 7:
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
				case 8:
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
				case 9:
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
				throw new EmployeeException();
			}

		}

	}
	
	private static void printStatistics() throws EmployeeConnectionException {
		System.out.println("No. of Employees older than 35 years: "+empService.getEmployeeCountAgeGreaterThan(e -> e.getAge()>35));
		System.out.println("List of Employee Ids older than 40 years: "+empService.getEmployeeIdsAgeGreaterThan(40));
		System.out.println("Employee count by Department: "+empService.getEmployeeCountByDepartment());
		System.out.println("Employee count by Department ordered: "+empService.getEmployeeCountByDepartmentOdered());
		System.out.println("Average Employee Age by Department: "+empService.getAvgEmployeeAgeByDept());
		System.out.println("List of Departments having more than 3 employees: "+empService.getDepartmentsHaveEmployeesMoreThan(3));
		System.out.println("List of Employee starts with 'S': "+empService.getEmployeeNamesStartsWith("S"));
		System.out.println("Average Employee Service by Department: "+empService.getAvgEmployeeServiceByDept());
	}

	private static void printEmpDetails(Employee emp)
	{
		if(emp == null)
			return;
//		System.out.format("\n %5d %15s %5d %20s %15s %15s", emp.getEmpId(), emp.getName(), emp.getAge(), emp.getDesignation(), emp.getDepartment(), emp.getCountry());
		System.out.format("\n %5d %15s %5d %20s %15s %15s %15s %25s %25s", emp.getEmpId(), emp.getName(), emp.getAge(), emp.getDesignation(), emp.getDepartment(), emp.getCountry(),emp.getDoj(),emp.getCreatedTime(),emp.getModifiedTime());		
	}
	
	private static void addEmployee() throws NumberFormatException, EmployeeValidationException, EmployeeConnectionException
	{
		Employee emp = new Employee();
		readEmpDetails(emp);
		boolean status = empService.create(emp);
		if(status)
			System.out.println("********  Employee Added Successfully  ********");
		else
			System.out.println("********  Employee cannot be created   ********");
	}
	
	private static void readEmpDetails(Employee emp)  
	{
		try {
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
			
			System.out.print("Enter Employee DOJ: ");
			emp.setDoj(Date.valueOf(sc.next()).toLocalDate());
			if(!updateFlag)
				emp.setCreatedTime(LocalDateTime.now());
			else
				emp.setModifiedTime(LocalDateTime.now());
			updateFlag = false;
		} catch (NumberFormatException e) {
			System.out.println("Please enter respective valid Integer/String");
		}
		
	}
	
	private static void printHeader()
	{
//		System.out.format("\n%6s %15s %5s %20s %15s %15s", "EmpId", "Name", "Age", "Designation", "Department", "Country");
		System.out.format("\n%6s %15s %5s %20s %15s %15s %15s %25s %25s", "EmpId", "Name", "Age", "Designation", "Department", "Country","DOJ","Created Date","Modified Date");		
	}
}
