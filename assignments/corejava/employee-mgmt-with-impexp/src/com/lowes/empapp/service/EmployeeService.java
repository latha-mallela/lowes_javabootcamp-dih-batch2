package com.lowes.empapp.service;

import java.util.List;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;

public interface EmployeeService {

	public boolean create (Employee emp);
	public boolean update (Employee emp);
	public boolean delete (int empId);
	public Employee get(int empId) throws EmployeeException;
	public List<Employee> getAll();
	public void fileImport();
	public void fileExport();
}
