package com.lowes.empapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lowes.empapp.exception.EmployeeConnectionException;
import com.lowes.empapp.model.Employee;
import com.mysql.cj.jdbc.MysqlDataSource;

public class EmployeeDaoImpl implements EmployeeDao {
	
	MysqlDataSource dataSource = null;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public EmployeeDaoImpl() throws EmployeeConnectionException
	{
		dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setDatabaseName("jdbctraining");
		dataSource.setUser("training");
		dataSource.setPassword("training");
		
		try
		{
			conn = dataSource.getConnection();
			System.out.println("Connection established...."+conn);
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in establishing the datebase connection");
		} 
	}

	@Override
	public boolean create(Employee emp) throws EmployeeConnectionException {
		boolean status = false;
		try
		{
//			String query = "INSERT INTO employee (name, age, designation, department, country) VALUES (?, ?, ?, ?, ?)";
			String query = "INSERT INTO employee_date (name, age, designation, department, country, doj, createddate) VALUES (?, ?, ?, ?, ?, ?, ?)";			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getAge());
			pstmt.setString(3, emp.getDesignation());
			pstmt.setString(4, emp.getDepartment());
			pstmt.setString(5, emp.getCountry());
			pstmt.setDate(6, Date.valueOf(emp.getDoj()));
			pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));			
			int q = pstmt.executeUpdate();
			if(q > 0)
				status = true;
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in Inserting the record into database" + e.getMessage());
			//e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public Employee get(int empId) throws EmployeeConnectionException {
		Employee emp = null;
//		String query = "SELECT * FROM employee WHERE id = " + empId;
		String query = "SELECT * FROM employee_date WHERE id = " + empId;
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String desig = rs.getString("designation");
				String dept = rs.getString("department");
				String country = rs.getString("country");
				LocalDate doj = rs.getDate("doj").toLocalDate();
				LocalDateTime createdDate = rs.getTimestamp("createddate").toLocalDateTime();
				LocalDateTime modifiedDate;
				if(rs.getTimestamp("modifieddate") != null)
				{
					modifiedDate = rs.getTimestamp("modifieddate").toLocalDateTime();
					emp = new Employee(id, name, age, desig, dept, country, doj, createdDate, modifiedDate);
				}	
				else
					emp = new Employee(id, name, age, desig, dept, country, doj, createdDate);
									
			
			}
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in fetching the record from database" +e.getMessage());
		}
		return emp;
	}

	@Override
	public boolean delete(int empId) throws EmployeeConnectionException {
		boolean status = false;
		try
		{
			stmt = conn.createStatement();

//			String query = "DELETE FROM employee WHERE id = " + empId;
			String query = "DELETE FROM employee_date WHERE id = " + empId;
			status = stmt.execute(query);
			stmt.close();
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in deleting the record from database" + e.getMessage());
		}
		return status;		
	}

	@Override
	public boolean update(Employee emp) throws EmployeeConnectionException {
		boolean status = false;
		try
		{
			stmt = conn.createStatement();
			
//			String query = "UPDATE employee_date SET name = \""+emp.getName() + "\",age = " +emp.getAge()
//			+",designation = \"" +emp.getDesignation() +"\",department = \""+emp.getDepartment()
//			+"\", country=\""+emp.getCountry()+"\", doj= "+Date.valueOf(emp.getDoj())+",createddate = "+Timestamp.valueOf(emp.getCreatedTime())+", modifieddate = "+Timestamp.valueOf(emp.getModifiedTime())+" WHERE id = "+emp.getEmpId();
			
			String query = "UPDATE employee_date SET name = ?, age = ?, designation = ?, department = ?, country = ?, doj = ?, createddate = ?, modifieddate = ? WHERE id = ?";			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getName());
			pstmt.setInt(2, emp.getAge());
			pstmt.setString(3, emp.getDesignation());
			pstmt.setString(4, emp.getDepartment());
			pstmt.setString(5, emp.getCountry());
			pstmt.setDate(6, Date.valueOf(emp.getDoj()));
			pstmt.setTimestamp(7, Timestamp.valueOf(emp.getCreatedTime()));
			pstmt.setTimestamp(8, Timestamp.valueOf(emp.getModifiedTime()));
			pstmt.setInt(9, emp.getEmpId());
			int q = pstmt.executeUpdate();
			if(q > 0)
				status = true;

//			status = stmt.execute(query);
//			stmt.close();
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in updating the record into database" + e.getMessage());
		}
		return status;		
	}

	@Override
	public List<Employee> getAll() throws EmployeeConnectionException {

		List<Employee> employees = new ArrayList<>();
		
		try
		{
			stmt = conn.createStatement();
//			rs = stmt.executeQuery("SELECT * FROM employee");
			rs = stmt.executeQuery("SELECT * FROM employee_date");
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String desig = rs.getString("designation");
				String dept = rs.getString("department");
				String country = rs.getString("country");
				LocalDate doj = rs.getDate("doj").toLocalDate();
				LocalDateTime createdDate = rs.getTimestamp("createddate").toLocalDateTime();
				LocalDateTime modifiedDate;
				if(rs.getTimestamp("modifieddate") != null)
				{
					modifiedDate = rs.getTimestamp("modifieddate").toLocalDateTime();
					employees.add(new Employee(id, name, age, desig, dept, country, doj, createdDate, modifiedDate));
				}
				else
					employees.add(new Employee(id, name, age, desig, dept, country, doj, createdDate));	
				
									
			}
		}catch(SQLException e)
		{
			throw new EmployeeConnectionException("Error in fetching the records from database" +e.getMessage());
		}
		return employees;
	}



}
