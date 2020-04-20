package com.neu.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.Encryption.Encrypt;
import com.neu.dao.AdminDAO;
import com.neu.exception.AppException;
import com.neu.model.Admin;


@Component
@Controller
public class AdminController {
	
	        @Autowired
	        AdminDAO adminDAO;
	        
			@RequestMapping(value = "/adminlogin.htm", method = RequestMethod.GET)
			protected ModelAndView home(Model model) {
				try {
					ModelAndView mv = null;
					mv = new ModelAndView("admin-login");
			        return mv;
				}
				catch(Exception e) {
					ModelAndView mv = null;
					mv = new ModelAndView("admin-login");
			        return mv;
				}
				
			}
			
			@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
			public ModelAndView showAdminSuccess(HttpServletRequest request) {
				try {
				HttpSession session = request.getSession();
				Admin u = (Admin) session.getAttribute("loggedInUser");
				if (u == null) {
					return new ModelAndView("index");
				}
				return new ModelAndView("admin-main");
				}
				catch(Exception e) {
					HttpSession session = request.getSession();
					session.invalidate();
					return new ModelAndView("index");
				}
			}

			
			@RequestMapping(value = "/registerAdminUser.htm", method = RequestMethod.GET)
			public ModelAndView showAdminRegister(HttpServletRequest request) {
				try {
				HttpSession session = request.getSession();
				Admin u = (Admin) session.getAttribute("loggedInUser");
				if (u == null) {
					return new ModelAndView("index");
				}
				return new ModelAndView("register-admin");
			      }
				catch(Exception e) {
					HttpSession session = request.getSession();
					session.invalidate();
					return new ModelAndView("index");
				}
			}
			
			@RequestMapping(value= "/adminloginsuccess.htm", method = RequestMethod.POST)
			protected ModelAndView customerLogin(
		            HttpServletRequest request,
		            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
				try {
				HttpSession session = request.getSession();
				ModelAndView mv = null;
		        
		        String Email  = request.getParameter("email");
		        String password = request.getParameter("password");
		        System.out.println("userEmail -> "+Email);
		        System.out.println("password -> "+password);
		        
		        String pwd = Encrypt.encrypt(password); 
		        System.out.println("password -> "+pwd);
		        Admin admin = adminDAO.getAdmin(Email, password);
		        
		        if(admin == null)
		        {
		        	return new ModelAndView("index","error",true);
		        }
		        session.setAttribute("loggedInUser", admin);
		        session.setAttribute("admin", admin);
		        session.setAttribute("role", "admin");
		        
		        
		       // List<ProductPojo> prod = productDao.getProducts(); //mv = new ModelAndView("user-dashboard", "prodList", prod);	
				mv = new ModelAndView("admin-main");
		        return mv;
			  } catch(Exception e) {
					HttpSession session = request.getSession();
					session.invalidate();
					return new ModelAndView("index");
				
			  }
			}
			
			@RequestMapping(value= "registeradmin-submit.htm", method = RequestMethod.POST)
			protected ModelAndView customerRegister(
		            HttpServletRequest request,
		            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
				try {
				HttpSession session = request.getSession();
				Admin a = (Admin) session.getAttribute("loggedInUser");
				if (a == null) {
					return new ModelAndView("index");
				}
				
				String email  = request.getParameter("email");
			    String password = request.getParameter("password");
			    String name  = request.getParameter("name");
		        String role = request.getParameter("role");
		        String pwd = Encrypt.encrypt(password); 
		        Boolean ad = adminDAO.checkAdmin(email);
		        if(ad == true) {
		        	return new ModelAndView("register-admin","error",true);
		        }
		        Admin admin= adminDAO.registerAdmin(email, password, role, name);
		        ModelAndView mv = new ModelAndView("admin-main","success",true);
		  		return mv;
				}catch(Exception e) {
					HttpSession session = request.getSession();
					session.invalidate();
					return new ModelAndView("index");
				} 
			}	
			
}
