package com.example.jwt.response;

import java.util.Date;

import com.example.jwt.models.AdminDetails;

public class AdminDetailsResponse {
	
	private Date timestamp;
	private String message;
	private String status;
	private AdminDetails result;
	
	public AdminDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminDetailsResponse(Date timestamp, String message, String status, AdminDetails result) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
		this.result = result;
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

	public AdminDetails getResult() {
		return result;
	}

	public void setResult(AdminDetails result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AdminDetailsResponse [timestamp=" + timestamp + ", message=" + message + ", status=" + status
				+ ", result=" + result + "]";
	}
	
	
	

}
