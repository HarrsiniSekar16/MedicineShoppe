package com.neu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="cart_table")
public class Cart implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartid;
	
	@Column(name="status")
	private String status;
		
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
		
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="quantity")
	private int quantity;

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}