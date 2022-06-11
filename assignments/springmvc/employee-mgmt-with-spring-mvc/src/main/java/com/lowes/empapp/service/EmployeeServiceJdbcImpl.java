package com.lowes.empapp.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
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

import org.springframework.beans.factory.annotation.Autowired;

import com.lowes.empapp.dao.EmployeeDao;
import com.lowes.empapp.dao.EmployeeDaoImpl;
import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.exception.EmployeeValidationException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.model.User;

public class EmployeeServiceJdbcImpl implements EmployeeService {

	@Autowired
	EmployeeDao empDao;

//	public EmployeeServiceJdbcImpl(EmployeeDao employeeDao) {
//		this.empDao = employeeDao;
//	}

	@Override
	public boolean create(Employee emp) throws EmployeeValidationException, EmployeeConnectionException {
		String error = "";
		
//		boolean isString = ((emp.getName() != null) && (!emp.getName().equals(""))
//	            && (emp.getName().chars().allMatch(Character::isLetter)));
//		
//		if(!isString)
//			System.out.println("Name should be given in characters");

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
		if (valName && valAge /*&& isString*/)
			return empDao.create(emp);
		else
			return false;

	}

	@Override
	public boolean update(Employee emp) throws EmployeeValidationException, EmployeeConnectionException {
		String error = "";

		System.out.println("Entered update in service class");
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
		if (valName && valAge)
		{
			System.out.println("Entered update method after validation");
			return empDao.update(emp);
		}
		else
			return false;

	}

	@Override
	public boolean delete(int empId) throws EmployeeConnectionException {

		return empDao.delete(empId);
	}

	@Override
	public Employee get(int empId) throws EmployeeConnectionException {
		return empDao.get(empId);
	}

	@Override
	public List<Employee> getAll() throws EmployeeConnectionException {
		return empDao.getAll();
	}

//	public synchronized void fileImport() throws EmployeeConnectionException {
//		System.out.println("\n" + Thread.currentThread().getName() + " - Import started");
//		int count = 0;
//		try {
//
//			Scanner sc = new Scanner(new FileReader(".\\input\\employee-input.txt"));
//			while (sc.hasNextLine()) {
//				Employee emp = new Employee();
//				String str = sc.nextLine();
//				StringTokenizer tokens = new StringTokenizer(str, ",");
//				int id = Integer.parseInt(tokens.nextToken());
//				emp.setName(tokens.nextToken());
//				emp.setAge(Integer.parseInt(tokens.nextToken()));
//				emp.setDesignation(tokens.nextToken());
//				emp.setDepartment(tokens.nextToken());
//				emp.setCountry(tokens.nextToken());
//				emp.setDoj(Date.valueOf(tokens.nextToken()).toLocalDate());
//				this.create(emp);
//				count++;
//			}
//			System.out.println(Thread.currentThread().getName() + " - " + count + " Employee records imported");
//
//		} catch (IOException e) {
//			System.out.println("Error in importing employee data from the file" + e.getMessage());
//		} catch (EmployeeValidationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public synchronized void fileExport() throws EmployeeConnectionException {
//		System.out.println("\n" + Thread.currentThread().getName() + " - Export started");
//		int count = 0;
//		try {
//			FileWriter fout = new FileWriter(".\\output\\employee-output.txt");
//
//			ArrayList<Employee> empAL = new ArrayList<Employee>(empDao.getAll());
//
//			String str = "";
//			for (Employee emp : empAL) {
//				str = emp.getEmpId() + "," + emp.getName() + "," + emp.getAge() + "," + emp.getDesignation() + ","
//						+ emp.getDepartment() + "," + emp.getCountry() + "," + emp.getDoj() + "," + emp.getCreatedTime()
//						+ "," + emp.getModifiedTime();
//				fout.write(str + "\n");
//				count++;
//			}
//			fout.flush();
//			fout.close();
//			System.out.println(Thread.currentThread().getName() + " - " + count + " Employee records exported");
//
//		} catch (IOException e) {
//			System.out.println("Error in importing employee data from the file" + e.getMessage());
//		}
//	}

	public boolean validate(Employee emp, String str, Predicate<Employee> condition,
			Function<String, Boolean> operation) {
		if (!condition.test(emp)) {
			return operation.apply(str);
		}
		return true;
	}



//	List<Employee> employees;
//
//	public List<Employee> empCollection() throws EmployeeConnectionException {
//		if (employees == null) {
//			employees = getAll();
//		}
//		return employees;
//	}
//
//	@Override
//	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) throws EmployeeConnectionException {
//		employees = empCollection();
//		long count = employees.stream().filter(condition).count();
//		return count;
//	}
//
//	@Override
//	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) {
//
//		List<Integer> empIds = employees.stream().filter(emp -> emp.getAge() > age).map(Employee::getEmpId)
//				.collect(Collectors.toList());
//
//		return empIds;
//	}
//
//	@Override
//	public Map<String, Long> getEmployeeCountByDepartment() {
//		Map<String, Long> groupByDept = employees.stream()
//				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
//
//		return groupByDept;
//	}
//
//	@Override
//	public Map<String, Long> getEmployeeCountByDepartmentOdered() {
//		Map<String, Long> groupSortByDept = employees.stream().sorted(Comparator.comparing(Employee::getDepartment))
//				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
//		return groupSortByDept;
//	}
//
//	@Override
//	public Map<String, Double> getAvgEmployeeAgeByDept() {
//		Map<String, Double> avgEmpAgeByDept = employees.stream().sorted(Comparator.comparing(Employee::getDepartment))
//				.collect(Collectors.groupingBy(Employee::getDepartment, LinkedHashMap::new,
//						Collectors.averagingInt(Employee::getAge)));
//		return avgEmpAgeByDept;
//	}
//
//	@Override
//	public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
//		List<String> deptList = employees.stream().sorted(Comparator.comparing(Employee::getDepartment))
//				.collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting())).entrySet()
//				.stream().filter(entry -> entry.getValue() > criteria).map(Map.Entry::getKey)
//				.collect(Collectors.toList());
//		return deptList;
//	}
//
//	@Override
//	public List<String> getEmployeeNamesStartsWith(String prefix) {
//		List<String> nameList = employees.stream().filter(emp -> emp.getName().startsWith(prefix))
//				.map(emp -> emp.getName()).collect(Collectors.toList());
//
//		return nameList;
//	}
//
//	
//	  @Override public Map<String, Double> getAvgEmployeeServiceByDept() {
//	  Map<String, Double> avgEmpserByDept = employees
//			  .stream()
//			  .collect(Collectors.groupingBy(Employee::getDepartment,
//					  Collectors.averagingInt(emp -> {Period period = Period.between(emp.getDoj(), LocalDate.now());
//					  return period.getYears();
//					  })));
//	  return avgEmpserByDept;
//	  }
//	 

}
