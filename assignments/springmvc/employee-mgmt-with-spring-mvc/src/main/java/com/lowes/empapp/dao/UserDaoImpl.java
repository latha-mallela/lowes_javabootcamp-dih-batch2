package com.lowes.empapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.lowes.empapp.model.User;

public class UserDaoImpl {
	
    @Autowired
    DataSource dataSource;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
		try
		{
			conn = dataSource.getConnection();
			System.out.println("Connection established...."+conn);
		}catch(SQLException e)
		{
			System.out.println("Error in establishing the datebase connection");
		} 
    }
    
//	public Connection getConnection()
//	{
//		try
//		{
//			conn = dataSource.getConnection();
//			System.out.println("Connection established...."+conn);
//		}catch(SQLException e)
//		{
//			System.out.println("Error in establishing the datebase connection");
//		} 
//		return conn;
//	}
	
	public boolean registerUser(User user) {
		boolean status = false;
		try
		{
			System.out.println("Entered register user method in UserDaoImpl");
			String query = "INSERT INTO users (firstname, lastname, username, password, location, contact) VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getfirstName());
			pstmt.setString(2, user.getlastName());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getLocation());
			pstmt.setString(6, user.getContact());
			
			int q = pstmt.executeUpdate();
			if(q > 0)
				status = true;
		}catch(SQLException e)
		{
			System.out.println("Error in Inserting the user into database" + e.getMessage());
			//e.printStackTrace();
		}
		return status;
	}
	
	public boolean validateUser(User user) {

		System.out.println("entered validateUser method");

		boolean status = false;
		String query = "Select * from users where username =  \""+user.getUserName()+ "\"";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next())
				status = true;
			else
				System.out.println("Not a valid user");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println(status);
		return status;
	}

}
