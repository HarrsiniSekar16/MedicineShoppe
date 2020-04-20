package com.neu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin {
	

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="adminID", unique = true, nullable = false)
	private int adminID;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@Column(name = "email", nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	
	public Admin() {
		
	}
	
	public Admin(String email, String password, String role, String name) {
    	this.email = email;
    	this.password = password;
    	this.role = role;	
    	this.name = name;
	}

	public int getAdminID() {
		return adminID;
	}


	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	

}
