package com.neu.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.Encryption.*;
import com.neu.dao.CustomerDA0;
import com.neu.exception.AppException;
import com.neu.model.Admin;
import com.neu.model.Customer;



/**
 * Handles requests for the application home page.
 */
@Component
@Controller
@RequestMapping(value = "/")
public class CustomerHomeController {

        @Autowired
        CustomerDA0 customerDAO;

        
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String home(Model model) {
			return "index";
		}

		@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
		public String homePage(Model model) {
			return "index";
		}
		
		@RequestMapping(value = "/custhome.htm", method = RequestMethod.GET)
		public ModelAndView showCustHome(HttpServletRequest request) {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			return new ModelAndView("cust-main");
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			}
		}
		
		@RequestMapping(value = "/myprofile.htm", method = RequestMethod.GET)
		public ModelAndView showAdminSuccess(HttpServletRequest request) {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			return new ModelAndView("myprofile");
		}catch(Exception e) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("index");
		}
		}

		
		@RequestMapping(value = "/registercustomer.htm", method = RequestMethod.GET)
		public String registerpage(Model model) {
			return "register-customer";
		}
		
		@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
		public String logOut(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.invalidate();
			return "index";
		}

		@RequestMapping(value= "/customerlogin.htm", method = RequestMethod.POST)
		protected ModelAndView customerLogin(
	            HttpServletRequest request,
	            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
	       try {
			ModelAndView mv = null;
	        
	        String customerEmail  = request.getParameter("customer-email");
	        String password = request.getParameter("customer-password");
	        System.out.println("userEmail -> "+customerEmail);
	        System.out.println("password -> "+password);
	        
	        String pwd = Encrypt.encrypt(password); 
	        System.out.println("password -> "+pwd);
	        Customer customer = customerDAO.getCustomer(customerEmail, pwd);
	        
	        if(customer == null)
	        {
	        	return new ModelAndView("index","error",true);
	        }
	        
	        HttpSession session = request.getSession();
	        session.setAttribute("loggedInUser", customer);
	        session.setAttribute("customer", customer);
	        session.setAttribute("role", "customer");
	        
	       // List<ProductPojo> prod = productDao.getProducts(); //mv = new ModelAndView("user-dashboard", "prodList", prod);	
			mv = new ModelAndView("cust-main");
	        return mv;
	        
		}catch(Exception e) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("index");
		}
		}
		
		@RequestMapping(value= "registercustomer-submit.htm", method = RequestMethod.POST)
		protected ModelAndView customerRegister(
	            HttpServletRequest request,
	            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
			try {
			HttpSession session = request.getSession();
			ModelAndView mv = null;
			String customerEmail  = request.getParameter("customer-email");
		    String password = request.getParameter("customer-password");
		    String name  = request.getParameter("name");
	        String role = request.getParameter("role");
	        String pwd = Encrypt.encrypt(password); 
	        Boolean customer = customerDAO.checkUser(customerEmail);
	        if(customer == true) {
	        	return new ModelAndView("register-customer","error",true);
	        }
	        Customer cust= customerDAO.registerCustomer(customerEmail, pwd, role, name);
	        mv = new ModelAndView("index","success",true);
	  		return mv;
			}catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			}
		}
			
		
		

	
}
