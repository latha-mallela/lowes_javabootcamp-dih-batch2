package com.lowes.empapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
