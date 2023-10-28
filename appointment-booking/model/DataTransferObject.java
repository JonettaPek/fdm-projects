package com.fdmgroup.appointmentbooking.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataTransferObject {

	private String email;
	private String password;
	private String userType;
	
	public DataTransferObject() {
		super();
	}
	
	public DataTransferObject(String email, String password, String userType) {
		super();
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
