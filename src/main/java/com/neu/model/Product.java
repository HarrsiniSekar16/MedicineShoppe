package com.neu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Product")
public class Product {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid", unique = true, nullable = false)
    private int pid;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="price")
	private int price;
	
	@Column(name="status")
	private String status;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	Customer customer;
	
	 public Product() {
			
	}
		
	 public Product(String productName, int price, int quantity, String status) {
	    	this.productName = productName;
	    	this.price = price;
	    	this.quantity = quantity;	
	    	this.status = status;
     }
		


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
   
}
