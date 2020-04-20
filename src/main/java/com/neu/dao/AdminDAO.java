package com.neu.dao;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.exception.AppException;
import com.neu.model.Admin;

@Repository
public class AdminDAO extends DAO {
	
	public Admin getAdmin(String email, String password) throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Admin where email =:email and password =:password");
			q.setString("email",email);
			q.setString("password",password);
			Admin admin = (Admin) q.uniqueResult();
			commit();
			return admin;
		}
		catch(Exception e){
			rollback();
			throw new AppException("Data didn't add to the customer table",e);
		}
		finally {
			close();
		}
		
	}
	
	public Admin registerAdmin(String email, String password, String role, String name) throws AppException{
		
		try {
			begin();
			Admin admin = new Admin(email,password,role,name);
			getSession().save(admin);
			commit();
			return admin;
		}
		catch(Exception e){
			rollback();
			throw new AppException("Data didn't add to the admin table",e);
		}
		finally {
			close();
		}
	}
	
	public Boolean checkAdmin(String email) throws AppException{
		
		
		try {
			begin();
			Query q = getSession().createQuery("from Admin where email =:email");
			q.setString("email",email);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}
			else {
				return true;
			}

		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
		
	}

}
