package com.lowes.empapp.dao;

import java.util.List;


import com.lowes.empapp.model.Employee;

public interface EmployeeDao {

	public Employee get(int empId);

	public boolean delete(int empId);

	public Employee update(Employee emp);

	public List<Employee> getAll();

	public Employee create(Employee emp);
	
}
