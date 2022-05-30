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

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;

public class EmployeeServiceColImpl implements EmployeeService {

	Map<Integer, Employee> employees = new HashMap<>();
	
	public EmployeeServiceColImpl()
	{
		employees.put(1, new Employee(1, "Anuj", 45, "Manager", "Operations", "India"));
		employees.put(2, new Employee(2, "Charan", 35, "Developer", "IT", "India"));
		employees.put(3, new Employee(3, "Dhanush", 38, "Lead", "IT", "India"));
		employees.put(4, new Employee(4, "Tarun", 36, "Manager", "Admin", "India"));
	}
	
	@Override
	public boolean create(Employee emp) {
		emp.setEmpId(employees.size()+1);
		return employees.put(emp.getEmpId(), emp) != null ? true : false;
	}

	@Override
	public boolean update(Employee emp) {
		
		return employees.put(emp.getEmpId(), emp) != null ? true : false;
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
	
}
