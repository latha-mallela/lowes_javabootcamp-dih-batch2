package com.lowes.empapp.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.exception.EmployeeValidationException;
import com.lowes.empapp.model.Employee;

public interface EmployeeService {

	public boolean create (Employee emp) throws EmployeeValidationException, EmployeeConnectionException;
	public boolean update (Employee emp) throws EmployeeValidationException, EmployeeConnectionException;
	public boolean delete (int empId) throws EmployeeConnectionException;
	public Employee get(int empId) throws EmployeeException, EmployeeConnectionException;
	public List<Employee> getAll() throws EmployeeConnectionException;
	public void fileImport() throws EmployeeConnectionException;
	public void fileExport()  throws EmployeeConnectionException;
	public boolean validate(Employee emp, String str, Predicate<Employee> condition, Function<String,Boolean> operation);
	
	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) throws EmployeeConnectionException;

    public List<Integer> getEmployeeIdsAgeGreaterThan(int age);

    public Map<String, Long> getEmployeeCountByDepartment();

    public Map<String, Long> getEmployeeCountByDepartmentOdered();

    public Map<String, Double> getAvgEmployeeAgeByDept();

    public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria);

    public List<String> getEmployeeNamesStartsWith(String prefix);
 
    public Map<String, Double> getAvgEmployeeServiceByDept();	
}
