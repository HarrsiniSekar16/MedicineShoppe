package com.neu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.neu.exception.AppException;
import com.neu.model.Cart;
import com.neu.model.Product;

@Repository
public class ProductDAO extends DAO{

	public Product checkProduct(String productName) throws AppException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Product where productName =:productName");
			q.setString("productName",productName);
			Object obj = q.uniqueResult();
		    return (Product) obj;

		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
	}

	public Product registerProduct(String name, int price, int quantity, String status) throws AppException {
		// TODO Auto-generated method stub
		try {
			begin();
			Product pr = new Product(name,price,quantity,status);
			getSession().save(pr);
			commit();
			return pr;
		}
		catch(Exception e){
			rollback();
			throw new AppException("Data didn't add to the product table",e);
		}
		finally {
			close();
		}
	}



	public int updateProduct(int id,String name, int price, int quantity, String status) throws AppException {
		// TODO Auto-generated method stub
		
	        int result = 0;
	        try{
	            begin();
	            Query q = getSession().createQuery("from Product where pid= :pid");
	            q.setInteger("pid", id);
	            Product m = (Product) q.uniqueResult();
	            m.setPrice(price);
	            m.setProductName(name);
	            m.setQuantity(quantity);
	            m.setStatus(status);
	            
	            getSession().save(m);
	            commit();
	            result = 1;
	        } catch(HibernateException ex){
	        	rollback();
				throw new AppException("Data didn't add to the product table",ex);
	        } finally{
	            close();
	        }
	        return result;
	    }
	
	public List<Product> findProductList() throws AppException {
		try {
			begin();
			Query q = getSession().createQuery("from Product");
			List<Product> pList = q.list();
			commit();
			return pList;
		}
		catch(HibernateException ex){
	        rollback();
			throw new AppException("Data didn't add to the product table",ex);
	    } finally{
	        close();
	    }
	}

	public List<Product> searchProducts(String search) throws AppException {
	
		List<Product> list = new ArrayList<Product>();
    	try
    	{
    		begin();
    		Query q = getSession().createQuery("from Product where productName like :like");
    		q.setString("like", "%"+search+"%");
    		list = q.list();
    		return list;
    	}
    	catch(HibernateException ex){
	        rollback();
			throw new AppException("Data didn't add to the product table",ex);
	    } finally{
	        close();
	    }
    	
	}

	public Product findProduct(int id) throws AppException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Product where pid =:pid");
			q.setInteger("pid",id);
			Object obj = q.uniqueResult();
		    return (Product) obj;

		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
	}

	public int deleteProduct(int id) throws AppException {
		// TODO Auto-generated method stub
        try {
            begin();
            Query q = getSession().createQuery("from Product where pid= :pid");
            q.setInteger("pid", id);
            Product pr = (Product) q.uniqueResult();
            getSession().delete(pr);
            commit();
            return 1;
        } catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
        
 
	}

	public List<Product> findProductbycust(int pid) throws AppException {
		// TODO Auto-generated method stub

		List<Product> list = new ArrayList<Product>();
    	try
    	{
    		begin();
    		Query q = getSession().createQuery("from Product where pid =:pid");
    		q.setInteger("pid",pid);
    		list = q.list();
    		return list;
    	}
    	catch(HibernateException ex){
	        rollback();
			throw new AppException("Data didn't add to the product table",ex);
	    } finally{
	        close();
	    }
	}

	public Product getQty(int pid) throws AppException {
		// TODO Auto-generated method stub
		try
    	{
    		begin();
    		Query q = getSession().createQuery("from Product where pid =:pid");
    		q.setInteger("pid",pid);
    		Product pr = (Product) q.uniqueResult();
    		
    		return pr;
    	}
    	catch(HibernateException ex){
	        rollback();
			throw new AppException("Data didn't add to the product table",ex);
	    } finally{
	        close();
	    
	}
	}

	public void updateQuantity(int pid, int qty) throws AppException {
		// TODO Auto-generated method stub
			try {
			begin();
			Query q = getSession().createQuery("from Product where pid =:pid");
			q.setInteger("pid",pid);
			String Status;
			if(qty == 0) {
				Status = "Out of Stock";
			}
			else {
				Status = "Available";
			}
			Product pr = (Product) q.uniqueResult();
			pr.setQuantity(qty);
			pr.setStatus(Status);
			getSession().save(pr);
	        commit();
		   
		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
		
	}

	public void updateStatus(int i,String Status) throws AppException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Product where pid =:pid");
			q.setInteger("pid",i);
			Product pr = (Product) q.uniqueResult();
			pr.setStatus(Status);
			getSession().save(pr);
	        commit();
		   
		} catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
		
	}
	

}
