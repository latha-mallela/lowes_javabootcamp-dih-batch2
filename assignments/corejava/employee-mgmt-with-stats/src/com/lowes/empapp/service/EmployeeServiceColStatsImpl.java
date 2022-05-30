package com.lowes.empapp.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;

public class EmployeeServiceColStatsImpl implements EmployeeService {

	Map<Integer, Employee> employees = new HashMap<>();
	
	public EmployeeServiceColStatsImpl()
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
	
	public boolean validate(Employee emp, String str, Predicate<Employee> condition, Function<String, Boolean> operation) 
	{
		if(!condition.test(emp))
		{
			return operation.apply(str);
		}
		return true;
	}

	@Override
	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {
		long count = employees.values()
				.stream()
				.filter(condition)
				.count();
		return count;
	}

	@Override
	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) {

		List<Integer> empIds = employees.values()
										.stream()
										.filter(emp -> emp.getAge() > age)
										.map(Employee::getEmpId)
										.collect(Collectors.toList());

		return empIds;
	}

	@Override
	public Map<String, Long> getEmployeeCountByDepartment() {
		Map<String, Long> groupByDept = 
				employees.values()
					.stream()
					.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		
		return groupByDept;
	}

	@Override
	public Map<String, Long> getEmployeeCountByDepartmentOdered() {
		Map<String, Long> groupSortByDept = 
				employees.values()
					.stream()
					.sorted(Comparator.comparing(Employee::getDepartment))
					.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
		return groupSortByDept;
	}

	@Override
	public Map<String, Double> getAvgEmployeeAgeByDept() {
		Map<String, Double> avgEmpAgeByDept = 
				employees.values()
					.stream()
					.sorted(Comparator.comparing(Employee::getDepartment))
					.collect(Collectors.groupingBy(Employee::getDepartment, LinkedHashMap::new, Collectors.averagingInt(Employee::getAge)));
		return avgEmpAgeByDept;
	}

	@Override
	public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
		List<String> deptList = employees.values()
				.stream()
				.sorted(Comparator.comparing(Employee::getDepartment))
				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() > criteria).map(Map.Entry::getKey).collect(Collectors.toList());
		return deptList;
	}

	@Override
	public List<String> getEmployeeNamesStartsWith(String prefix) {
		List<String> nameList = employees.values()
				.stream()
				.filter(emp -> emp.getName().startsWith(prefix)).map(emp -> emp.getName())
				.collect(Collectors.toList());
	
		return nameList;
	}

	/*
	 * @Override public Map<String, Integer> getAvgEmployeeServiceByDept() {
	 * Map<String, Integer> avgEmpserByDept = employees.values() .stream()
	 * .sorted(Comparator.comparing(Employee::getDepartment))
	 * .collect(Collectors.groupingBy(Employee::getDepartment, LinkedHashMap::new,
	 * Collectors.averagingInt(Employee::getAge))); return avgEmpserByDept; }
	 */
	
}
