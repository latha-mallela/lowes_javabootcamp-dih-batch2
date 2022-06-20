package com.lowes.empapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="employee_jpa")
public class Employee {

	@Id
	@GeneratedValue
	@Column(name = "empId")
    private int empId;	
	
	@NotBlank(message = "Name cannot be Blank or Empty") 
    private String name;
	
	@NotNull	(message = "Age is required") 
	@Min(value=18, message="Minimum Age should be 18")
	@Max(value=60, message="Maximum Age should be 60")
    private int age;
	
	@NotBlank(message = "Designation cannot be Blank or Empty")
    private String designation;
	
	@NotBlank(message = "Department cannot be Blank or Empty")
    private String department;
	
	@NotBlank(message = "Country cannot be Blank or Empty")
    private String country;
	
	
    @JsonFormat(pattern = "dd-MM-yyyy") 
    private LocalDate doj;
    
   // set createdTime while creating emp
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
   @Basic
   @Column(name = "createdTime")
    private LocalDateTime createdTime;  
   
   @PrePersist
   private void onCreate() {
	   createdTime = LocalDateTime.now();
   }

   // set modifiedTime while updating emp
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    @Basic
    @Column(name = "modifiedTime")
    private LocalDateTime modifiedTime;

    @PreUpdate
    private void onUpdate() {
    	modifiedTime = LocalDateTime.now();
    }


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
