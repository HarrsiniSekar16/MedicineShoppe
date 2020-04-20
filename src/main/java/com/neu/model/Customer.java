package com.neu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id", unique = true, nullable = false)
    private int customer_id;

	@Column(name = "customerEmail")
	private String customerEmail;
	
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "password")
	private String password;
	
	@Column(name="state")
	private int state;
	
	@Column(name="role")
	private String role;
	
	public Customer() {
		
	}
	
    public Customer(String customerEmail, String password, String role, String name) {
    	this.customerEmail = customerEmail;
    	this.password = password;
    	this.role = role;	
    	this.name = name;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
}