package com.example.jwt.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long adminId;
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String adminRole;
	private String department;
	private String adminAuthorities;
	
	public AdminDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDetails(long adminId, String firstName, String lastName, String emailId, String password,
			String adminRole, String department, String adminAuthorities) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.adminRole = adminRole;
		this.department = department;
		this.adminAuthorities = adminAuthorities;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAdminAuthorities() {
		return adminAuthorities;
	}

	public void setAdminAuthorities(String adminAuthorities) {
		this.adminAuthorities = adminAuthorities;
	}

	@Override
	public String toString() {
		return "AdminDetails [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", password=" + password + ", adminRole=" + adminRole + ", department=" + department
				+ ", adminAuthorities=" + adminAuthorities + "]";
	}
	
	
	
	

}
