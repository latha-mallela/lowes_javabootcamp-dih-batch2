package com.lowes.empapp.dao;

import java.util.List;

import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.model.Employee;

public interface EmployeeDao {

	public Employee get(int empId) throws EmployeeConnectionException;

	public boolean delete(int empId) throws EmployeeConnectionException;

	public boolean update(Employee emp) throws EmployeeConnectionException;

	public List<Employee> getAll() throws EmployeeConnectionException;

	public boolean create(Employee emp) throws EmployeeConnectionException;
	
}
