package com.lowes.empapp.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lowes.empapp.dao.EmployeeDao;
import com.lowes.empapp.dao.EmployeeDaoImpl;
import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.service.EmployeeService;
import com.lowes.empapp.service.EmployeeServiceImpl;
import com.mysql.cj.jdbc.MysqlDataSource;


@Configuration
public class EmployeeConfig {
	
//	@Bean
//	public EmployeeService employeeService(@Autowired EmployeeDao employeeDao) throws EmployeeConnectionException {
//		EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeDao);
//		return employeeService;
//	}
//
//	@Bean
//	public EmployeeDao employeeDao(@Autowired MysqlDataSource mysqlDataSource) throws EmployeeConnectionException
//	{
//		return new EmployeeDaoImpl(mysqlDataSource);
//	}
//	
/* Setter method injection */	
	
	@Bean
	public EmployeeService employeeService() throws EmployeeConnectionException {
		EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
		employeeService.setEmployeeDao(employeeDao());
		return employeeService;
	}
	
	@Bean(initMethod = "init")
	public EmployeeDao employeeDao() throws EmployeeConnectionException
	{
		return new EmployeeDaoImpl(getDataSource());
	}
	
	
	@Bean
    public MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("jdbctraining");
        dataSource.setUser("training");
        dataSource.setPassword("training");
        return dataSource;
    }
	
}
