package com.lowes.empapp.service;

import java.util.List;



import com.lowes.empapp.exception.EmployeeException;

import com.lowes.empapp.model.Employee;

public interface EmployeeService {

    public Employee create(Employee emp);

    public Employee update(Employee emp);

    public boolean delete(int empId);

    public Employee get(int empId) throws EmployeeException;

    public List<Employee> getAll();
}
