package com.neu.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.exception.AppException;
import com.neu.model.Customer;

@Repository
public class CustomerDA0 extends DAO {
	
	public Customer registerCustomer(String customerEmail, String password, String role, String name) throws AppException{
		
		try {
			begin();
			Customer customer = new Customer(customerEmail,password,role,name);
			getSession().save(customer);
			commit();
			return customer;
		}
		catch(Exception e){
			rollback();
			throw new AppException("Data didn't add to the customer table",e);
		}
		finally {
			close();
		}
	}
	
	public Boolean checkUser(String customerEmail) throws AppException{
		
		
		try {
			begin();
			Query q = getSession().createQuery("from Customer where customerEmail =:customerEmail");
			q.setString("customerEmail",customerEmail);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}
			else {
				return true;
			}

		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng User", e);
		} finally {
			close();
		}
		
	}
	
	public Customer getCustomer(String customerEmail, String password) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where customerEmail =:customerEmail and password =:password");
			q.setString("customerEmail",customerEmail);
			q.setString("password",password);
			Customer cust = (Customer) q.uniqueResult();
			commit();
			return cust;
		}
		catch(Exception e){
			rollback();
			throw new AppException("User retrieval not successful",e);
		}
		finally {
			close();
		}
		
	}

}
