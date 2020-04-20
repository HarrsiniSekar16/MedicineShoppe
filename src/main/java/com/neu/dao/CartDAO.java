package com.neu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.neu.exception.AppException;
import com.neu.model.Cart;
import com.neu.model.Customer;




@Repository
public class CartDAO extends DAO {

	public void addProducttoCart(Cart c) throws AppException
	{
		try {
			begin();
			getSession().save(c);
			commit();
		}
		catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
		
	}
	
	public boolean checkInCart(int id, int prodid) throws AppException
	{
		
		try {
			begin();
			Query q = getSession().createQuery("from Cart where customer_id = :customer_id and product_id = :product_id and status =:status");
			String status = "cart";
			q.setInteger("customer_id", id);
			q.setInteger("product_id", prodid);
			q.setString("status", status);
	
			List<Cart> listcart = q.list();
			if(listcart.size()>0)
			{
				return true;
			}	
			else {
				return false;
			}
		}
		catch (HibernateException e) {
			rollback();
			throw new AppException("Error while finidng Admin", e);
		} finally {
			close();
		}
		
	}
	
	public List<Cart> findProductbycust(int pid) throws AppException {
		// TODO Auto-generated method stub

		List<Cart> list = new ArrayList<Cart>();
    	try
    	{
    		begin();
    		Query q = getSession().createQuery("from Cart where customer_id =:customer_id and status =:status");
    		q.setInteger("customer_id",pid);
    		String status = "cart";
    		q.setString("status", status);
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

	public int updateProduct(Cart cart) throws AppException {
		
		int result = 0;
        try{
            begin();
            Query q = getSession().createQuery("from Cart where product= :product and customer =:customer and status =:status");
            q.setInteger("product", cart.getProduct().getPid());
            q.setInteger("customer", cart.getCustomer().getCustomer_id());
            String status = "cart";
    		q.setString("status", status);
            
            Cart m = (Cart) q.uniqueResult();
            m.setQuantity(cart.getQuantity());
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
		// TODO Auto-generated method stub

	public int updateProductByID(int id, int qty) throws AppException {
		// TODO Auto-generated method stub

		int result = 0;
        try{
            begin();
            Query q = getSession().createQuery("from Cart where cartid= :cartid");
            q.setInteger("cartid", id);
            Cart m = (Cart) q.uniqueResult();
            m.setQuantity(qty);
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

	public int removeProductByID(int id, int i) throws AppException {
		// TODO Auto-generated method stub
		try {
            begin();
            Query q = getSession().createQuery("from Cart where product= :product and customer =:customer and status =:status");
            q.setInteger("product", id);
            q.setInteger("customer", i);
            String status = "cart";
    		q.setString("status", status);
            Cart pr = (Cart) q.uniqueResult();
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

	public List<Cart> getOrderedProducts(Customer u) throws AppException {
		// TODO Auto-generated method stub
		List<Cart> list = new ArrayList<Cart>();
    	try
    	{
    		begin();
    		Query q = getSession().createQuery("from Cart where customer_id =:customer_id and status =:status");
    		q.setInteger("customer_id",u.getCustomer_id());
    		String status = "complete";
    		q.setString("status", status);
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

	public void updateStatus(Cart p) throws AppException
	{
		try {
		begin();
		Query q = getSession().createQuery("from Cart where customer =:customer and status =:status and cartid =:cartid");
		q.setInteger("customer",p.getCustomer().getCustomer_id());
		q.setInteger("cartid",p.getCartid());
		String status = "cart";
		q.setString("status",status);
		Cart pr = (Cart) q.uniqueResult();
		pr.setStatus("complete");
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



	

