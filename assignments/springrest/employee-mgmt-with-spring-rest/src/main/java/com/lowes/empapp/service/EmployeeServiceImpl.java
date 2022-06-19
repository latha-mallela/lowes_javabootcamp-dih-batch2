package com.lowes.empapp.service;


import java.util.ArrayList;

import java.util.List;

import java.util.function.Predicate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowes.empapp.dao.EmployeeDao;
import com.lowes.empapp.dao.EmployeeDaoImpl;

import com.lowes.empapp.exception.InputValidationException;
import com.lowes.empapp.model.Employee;
import com.mysql.cj.util.StringUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    EmployeeDao empDao;
    List<Employee> employees = null;

    @Override
    public Employee create(Employee emp) {
        return empDao.create(validateEmpInput(emp));
    }

    @Override
    public Employee get(int empId) {
        return empDao.get(empId);
    }

    @Override
    public Employee update(Employee empNew) {
        return empDao.update(validateEmpInput(empNew));
    }

    @Override
    public boolean delete(int empId) {
        return empDao.delete(empId);
    }

    @Override
    public List<Employee> getAll() {
        return empDao.getAll();
    }

    // Validation Logic

    public Employee validateEmpInput(Employee emp) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isNullOrEmpty(emp.getName())) {
            errors.add("Name should not be empty");
        }

        if (emp.getAge() <= 0 || StringUtils.isNullOrEmpty(String.valueOf(emp.getAge()))) {
            errors.add("Age cannot be 0 or empty");
        }

        // validate age
        if (!validate(emp, (employee) -> employee.getAge() >= 20 && employee.getAge() <= 60)) {
            errors.add("Invalid age: Enter age > 20 and < 60");
        }

        if (StringUtils.isNullOrEmpty(emp.getDesignation())) {
            errors.add("Designation should not be empty");
        }
        if (StringUtils.isNullOrEmpty(emp.getDepartment())) {
            errors.add("Department should not be empty");
        }
        if (StringUtils.isNullOrEmpty(emp.getCountry())) {
            errors.add("Country should not be empty");
        }

       

        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        return emp;
    }


    // Validation Method
    public boolean validate(Employee emp, Predicate<Employee> condition) {
        return condition.test(emp);
    }


}
