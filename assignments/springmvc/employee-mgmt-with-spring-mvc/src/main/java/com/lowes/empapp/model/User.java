package com.lowes.empapp.model;

public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String location;
	private String contact;
	
	public User()
	{
		
	}

	public User(String firstName, String lastName, String userName, String password, String location, String contact) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.location = location;
		this.contact = contact;
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String toString()
	{
		return this.getfirstName() + " " + this.getlastName() + " " + this.getUserName() + " " + this.getPassword() + " " + this.getLocation() + " " + this.getContact();
	}
	

}
