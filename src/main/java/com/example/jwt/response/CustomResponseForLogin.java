package com.example.jwt.response;

import java.util.Date;

public class CustomResponseForLogin {
	
	private Date timestamp;
	private String message;
	private String status;
	private String jwt;
	private String firstName;
	private String lastName;
	private String emailId;
	
	
	
	
	public CustomResponseForLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomResponseForLogin(Date timestamp, String message, String status, String jwt, String firstName,
			String lastName, String emailId) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.jwt = jwt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}


	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
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

}
