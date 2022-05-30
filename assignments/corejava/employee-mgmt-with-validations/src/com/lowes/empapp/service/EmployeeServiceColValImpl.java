package com.lowes.empapp.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;

public class EmployeeServiceColValImpl implements EmployeeService {

	Map<Integer, Employee> employees = new HashMap<>();
	
	public EmployeeServiceColValImpl()
	{
		employees.put(1, new Employee(1, "Anuj", 45, "Manager", "Operations", "India"));
		employees.put(2, new Employee(2, "Charan", 35, "Developer", "IT", "India"));
		employees.put(3, new Employee(3, "Dhanush", 38, "Lead", "IT", "India"));
		employees.put(4, new Employee(4, "Tarun", 36, "Manager", "Admin", "India"));
	}
	
	@Override
	public boolean create(Employee emp) {
		emp.setEmpId(employees.size()+1);
		String error = "";
		
		error = "Invalid Name. Name should be more than 2 characters.";
		boolean valName = this.validate(emp, error, e -> e.getName().length() > 2, m -> {
			System.out.println(m);
			return false;
		});
		
		error = "Invalid age. Age should be between 18 and 60.";
		boolean valAge = this.validate(emp, error, e -> e.getAge() >= 18 && e.getAge() <= 60, m -> {
			System.out.println(m);
			return false;
		});		
		if(valName && valAge)
			return employees.put(emp.getEmpId(), emp) != null ? true : false;
		else
			return false;
	}

	@Override
	public boolean update(Employee emp) {
		String error = "";
		
		error = "Invalid Name. Name should be more than 2 characters.";
		boolean valName = this.validate(emp, error, e -> e.getName().length() > 2, m -> {
			System.out.println(m);
			return false;
		});
		
		error = "Invalid age. Age should be between 18 and 60.";
		boolean valAge = this.validate(emp, error, e -> e.getAge() >= 18 && e.getAge() <= 60, m -> {
			System.out.println(m);
			return false;
		});		
		if(valName && valAge)
			return employees.put(emp.getEmpId(), emp) != null ? true : false;
		else
			return false;
	}

	@Override
	public boolean delete(int empId) {
		
		return employees.remove(empId) != null ? true : false;
	}

	@Override
	public Employee get(int empId) throws EmployeeException {

		Employee emp = employees.get(empId);
		
		if(emp == null)
			System.out.println("No Employee record found for this ID");
		return emp;
	}

	@Override
	public List<Employee> getAll() {
		ArrayList<Employee> empAL = new ArrayList<Employee>(employees.values());
		return empAL;
	}

	public synchronized void fileImport()
	{
		System.out.println("\n"+Thread.currentThread().getName() +" - Import started");
		int count = 0;
		try {
			
			Scanner sc = new Scanner(new FileReader(".\\input\\employee-input.txt"));
			while(sc.hasNextLine())
			{
				Employee emp = new Employee();
				String str = sc.nextLine();
				StringTokenizer tokens = new StringTokenizer(str,",");
				int id = Integer.parseInt(tokens.nextToken());
				emp.setName(tokens.nextToken());
				emp.setAge(Integer.parseInt(tokens.nextToken()));
				emp.setDesignation(tokens.nextToken());
				emp.setDepartment(tokens.nextToken());
				emp.setCountry(tokens.nextToken());
				this.create(emp);
				count++;
			}
			System.out.println(Thread.currentThread().getName()+" - "+count+" Employee records imported");
			
		}catch(IOException e)
		{
			System.out.println("Error in importing employee data from the file"+e.getMessage());
		}
	}	
	
	public synchronized void fileExport()
	{
		System.out.println("\n"+Thread.currentThread().getName() +" - Export started");
		int count = 0;
		try {
			FileWriter fout = new FileWriter(".\\output\\employee-output.txt");
			
			ArrayList<Employee> empAL = new ArrayList<Employee>(employees.values());

			for(Employee emp : empAL)
			{
				String str = emp.getEmpId()+","+emp.getName()+","+emp.getAge()+","+emp.getDesignation()+","+emp.getDepartment()+","+emp.getCountry();
				fout.write(str+"\n");
				count++;
			}
			fout.flush();
			fout.close();
			System.out.println(Thread.currentThread().getName()+" - "+count+" Employee records exported");
			
		}catch(IOException e)
		{
			System.out.println("Error in importing employee data from the file"+e.getMessage());
		}
	}
	
	public boolean validate(Employee emp, String str, Predicate<Employee> condition, Function<String, Boolean> operation) 
	{
		if(!condition.test(emp))
		{
			return operation.apply(str);
		}
		return true;
	}
	
}
