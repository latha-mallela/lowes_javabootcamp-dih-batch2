package com.lowes.empapp.model;

public class Employee {
	
	private int empId;
	private int salary;  
	private String name;
	private String designation;
	private String location;
	private String email;
	
	public Employee()
	{
		
	}
	
	public Employee(int empId, String name, String designation, int salary, String location, String email)
	{
		this.empId = empId;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.location = location;
		this.email = email;
	}
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}  
	

}
