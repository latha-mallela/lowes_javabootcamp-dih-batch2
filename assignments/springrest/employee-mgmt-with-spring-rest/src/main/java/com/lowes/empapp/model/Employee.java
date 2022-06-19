package com.lowes.empapp.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {
	
	private int empId;
	private String name;
	private int age;  
	private String designation;
	private String department;
	private String country;
	

	@JsonFormat(pattern = "dd-MM-yyyy")  
    private LocalDate doj;

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime modifiedTime;
	
	public Employee()
	{
		
	}
	
//	public Employee(int empId, String name, int age, String designation, String department, String country) {
//
//		this.empId = empId;
//		this.name = name;
//		this.age = age;
//		this.designation = designation;
//		this.department = department;
//		this.country = country;
//	}
	
	public Employee(int empId, String name, int age, String designation, String department,
			String country, LocalDate doj, LocalDateTime crTime )
	{
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.department = department;
		this.country = country;
		this.doj = doj;
		this.createdTime = crTime;
		//this.modifiedTime = mdTime;
	}
	
	public Employee(int empId, String name, int age, String designation, String department,
			String country, LocalDate doj, LocalDateTime crTime,  LocalDateTime mdTime)
	{
		this.empId = empId;
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.department = department;
		this.country = country;
		this.doj = doj;
		this.createdTime = crTime;
		this.modifiedTime = mdTime;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}  
	
	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
