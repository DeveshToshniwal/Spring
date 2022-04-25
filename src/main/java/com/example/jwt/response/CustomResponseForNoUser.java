package com.example.jwt.response;

import java.util.Date;

public class CustomResponseForNoUser {
	
	private Date date;
	private String message;
	private String status;

	
	
	public CustomResponseForNoUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomResponseForNoUser(Date date, String message, String status) {
		super();
		this.date = date;
		this.message = message;
		this.status = status;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
}
