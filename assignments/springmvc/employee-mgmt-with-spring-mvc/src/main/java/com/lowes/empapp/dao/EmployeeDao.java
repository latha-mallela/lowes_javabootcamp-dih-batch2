package com.lowes.empapp.dao;

import java.util.List;


import com.lowes.empapp.model.Employee;
import com.lowes.empapp.model.User;

public interface EmployeeDao {

	public Employee get(int empId);

	public boolean delete(int empId);

	public boolean update(Employee emp);

	public List<Employee> getAll();

	public boolean create(Employee emp);
	
}
