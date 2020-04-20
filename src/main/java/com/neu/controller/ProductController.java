package com.neu.controller;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.CartDAO;
import com.neu.dao.ProductDAO;
import com.neu.exception.AppException;
import com.neu.model.Admin;
import com.neu.model.Customer;
import com.neu.model.Product;
import com.neu.model.Cart;

@Component
@Controller
public class ProductController {

	    @Autowired
        ProductDAO productDAO;
	    
	    @Autowired
        CartDAO cartDAO;
	    
	    @PostConstruct
		public void init() throws AppException {
	    	
			List<Product> pr  = productDAO.findProductList();
			for(Product p: pr) {
				if(p.getQuantity()>0) {
					String Status ="Available";
					productDAO.updateStatus(p.getPid(),Status);
				}
				else {
					String Status = "Out of Stock";
					productDAO.updateStatus(p.getPid(),Status);
				}
			}
		}
	    
	    @RequestMapping(value = "/viewCart.htm", method = RequestMethod.GET)
		public ModelAndView viewCart(HttpServletRequest request) throws AppException {
			try {
	    	HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			List<Cart> checkedList = new ArrayList();
			ModelAndView mv = new ModelAndView();
			checkedList =cartDAO.findProductbycust(u.getCustomer_id());
			mv.addObject("checkedList", checkedList);
			mv.setViewName("viewcart");
			return mv;
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}
     
		@RequestMapping(value = "/registerProduct.htm", method = RequestMethod.GET)
		public ModelAndView registerProduct(HttpServletRequest request) {
			try {
			HttpSession session = request.getSession();
			Admin u = (Admin) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			return new ModelAndView("register-product");
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/updateProduct.htm", method = RequestMethod.POST)
		public ModelAndView updateProduct(HttpServletRequest request,
				HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Admin u = (Admin) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			
			int id = Integer.parseInt(request.getParameter("prod"));
			Product pr = productDAO.findProduct(id);
			
			if(pr == null) {
				return new ModelAndView("view-products","error",true);
			}
			ModelAndView mv = new ModelAndView();
			mv.addObject("pr", pr);
			mv.setViewName("update-product");
			return mv;
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
			
		}
		
		@RequestMapping(value = "/deleteProduct.htm", method = RequestMethod.POST)
		public ModelAndView deleteProduct(HttpServletRequest request,
				HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Admin u = (Admin) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			
			int id = Integer.parseInt(request.getParameter("product"));
			System.out.println(id);
			int pr = productDAO.deleteProduct(id);
			
			if(pr == 0) {
				return new ModelAndView("view-products","error",true);
			}
	
			return new ModelAndView("admin-main","error",true);
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
			
		}
		
		@RequestMapping(value= "registerproduct-submit.htm", method = RequestMethod.POST)
		protected ModelAndView productRegisterSubmit(
	            HttpServletRequest request,
	            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
			try {
			HttpSession session = request.getSession();
			Admin a = (Admin) session.getAttribute("loggedInUser");
			if (a == null) {
				return new ModelAndView("index");
			}
			
			String name  = request.getParameter("pname");
		    int price = Integer.parseInt(request.getParameter("price"));
		    int quantity = Integer.parseInt(request.getParameter("quantity"));
		    String status ="";
		    if(quantity > 0) {
		    	 status = "Available";
		    }
		    else {
		    	status = "Out of Stock";
		    }
	        
	        Product ad = productDAO.checkProduct(name);
	        if(ad == null) {
	        	Product pr = productDAO.registerProduct(name,price,quantity,status);
	 	       
		        ModelAndView mv = new ModelAndView("admin-main","success",true);
		  		return mv;
	        }
	        else {
	        	return new ModelAndView("register-product","error",true);
	        	
	        }
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
			
		}
		
		@RequestMapping(value= "updateproduct-submit.htm", method = RequestMethod.POST)
		protected ModelAndView updateProductSubmit(
	            HttpServletRequest request,
	            HttpServletResponse response) throws AppException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
			try {
			HttpSession session = request.getSession();
			Admin a = (Admin) session.getAttribute("loggedInUser");
			if (a == null) {
				return new ModelAndView("index");
			}
			int id = Integer.parseInt(request.getParameter("id"));
			String name  = request.getParameter("pname");
			System.out.println(name);
		    int price = Integer.parseInt(request.getParameter("price"));
		    System.out.println(price);
		    int quantity = Integer.parseInt(request.getParameter("quantity"));
		    System.out.println(quantity);
		    String status ="";
		    if(quantity > 0) {
		    	 status = "Available";
		    }
		    else {
		    	status = "Out of Stock";
		    }
		    System.out.println(status);
	        
			int pr = productDAO.updateProduct(id,name,price,quantity,status);
			
			if(pr == 1)
			{
				 ModelAndView mv = new ModelAndView("admin-main","success",true);
			  		return mv;
			}
			
			else {
				ModelAndView mv = new ModelAndView("update-product","success",true);
		  		return mv;
			}
			}catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
 
		}
		
		@RequestMapping(value = "/viewProducts.htm", method = RequestMethod.GET)
		protected ModelAndView viewProducts(HttpServletRequest request) throws AppException {
			
			try{
				HttpSession session = request.getSession();
			
			Admin u = (Admin) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			List<Product> checkedList = new ArrayList();
			ModelAndView mv = new ModelAndView();
			checkedList = productDAO.findProductList();
			mv.addObject("checkedList", checkedList);
			mv.setViewName("view-products");
			return mv;
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
			
		}
		
		@RequestMapping(value = "/viewProductsCust.htm", method = RequestMethod.GET)
		protected ModelAndView viewProductsCust(HttpServletRequest request) throws AppException {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			List<Product> checkedList = new ArrayList();
			ModelAndView mv = new ModelAndView();
			checkedList = productDAO.findProductList();
			mv.addObject("checkedList", checkedList);
			mv.setViewName("view-products-cust");
			return mv;
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		
		@RequestMapping(value = "/searchProducts.htm", method = RequestMethod.POST)
		protected ModelAndView searchProducts(HttpServletRequest request, HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Admin u = (Admin) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			String search = request.getParameter("search");
			List<Product> checkedList = new ArrayList();
			ModelAndView mv = new ModelAndView();
			checkedList = productDAO.searchProducts(search);
			mv.addObject("checkedList", checkedList);
			mv.setViewName("view-products");
			return mv;
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/searchProductCust.htm", method = RequestMethod.POST)
		protected ModelAndView searchProductCust(HttpServletRequest request, HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			String search = request.getParameter("search");
			List<Product> checkedList = new ArrayList();
			ModelAndView mv = new ModelAndView();
			checkedList = productDAO.searchProducts(search);
			mv.addObject("checkedList", checkedList);
			mv.setViewName("view-products-cust");
			return mv;
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/Addtocart.htm", method = RequestMethod.POST)
		protected ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			int id = Integer.parseInt(request.getParameter("prod"));
			Product p = productDAO.findProduct(id);
			int qty = 1;
			Cart cart = new Cart();
			cart.setCustomer(u);
			cart.setProduct(p);
			cart.setStatus("cart");
			
			ModelAndView mv = new ModelAndView();
			boolean status = cartDAO.checkInCart(u.getCustomer_id(), p.getPid());
			if(status == true)
			{
				ModelAndView mav = new ModelAndView();
				List<Product> checkedList = new ArrayList();
				checkedList = productDAO.findProductList();
				mav.addObject("checkedList", checkedList);
				mav.setViewName("view-products-cust");
				mav.addObject("errorMessage", "Product Already exists in the cart");
				return mav;
				
			}
			else {
				cart.setQuantity(qty);
			    cartDAO.addProducttoCart(cart);
			}
			
			List<Product> userProduct = new ArrayList();
			userProduct = productDAO.findProductbycust(p.getPid());
			System.out.println(cart);
			List<Product> checkedList = new ArrayList();
			session.setAttribute("cart", cart);
			session.setAttribute("product",userProduct);
			
			checkedList = productDAO.findProductList();
			mv.addObject("checkedList", checkedList);
			mv.addObject("success", true);
			mv.setViewName("view-products-cust");
			return mv;	
			
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/updateCart.htm", method = RequestMethod.POST)
		protected ModelAndView updateCart(HttpServletRequest request, HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			int pid = Integer.parseInt(request.getParameter("pid"));
			int id = Integer.parseInt(request.getParameter("prod"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			Product pqty = productDAO.getQty(pid);
			System.out.println(pqty);
			if(pqty.getQuantity() >= qty) {
				int update = cartDAO.updateProductByID(id,qty);
				if(update == 1) {
					List<Cart> checkedList = new ArrayList();
					ModelAndView mv = new ModelAndView();
					checkedList =cartDAO.findProductbycust(u.getCustomer_id());
					mv.addObject("checkedList", checkedList);
					mv.setViewName("viewcart");
					mv.addObject("success",true);
					return mv;
				}
				else {
					return new ModelAndView("cust-main");
				}
			}
			else {
				List<Cart> checkedList = new ArrayList();
				ModelAndView mv = new ModelAndView();
				checkedList =cartDAO.findProductbycust(u.getCustomer_id());
				mv.addObject("checkedList", checkedList);
				mv.setViewName("viewcart");
				mv.addObject("errormsg",true);
				mv.addObject("qty",pqty.getQuantity());
				return mv;	
			}
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/removeCart.htm", method = RequestMethod.POST)
		protected ModelAndView removeCart(HttpServletRequest request, HttpServletResponse response) throws AppException {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			int id = Integer.parseInt(request.getParameter("prod"));
			
			int update = cartDAO.removeProductByID(id,u.getCustomer_id());
			if(update == 1) {
				List<Cart> checkedList = new ArrayList();
				ModelAndView mv = new ModelAndView();
				checkedList =cartDAO.findProductbycust(u.getCustomer_id());
				mv.addObject("checkedList", checkedList);
				mv.setViewName("viewcart");
				return mv;
			}
			else {
				return new ModelAndView("cust-main");
			}
			}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 

		}
		
		@RequestMapping(value="/checkout.htm", method=RequestMethod.POST)
		public ModelAndView addOrder(HttpServletRequest request) {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			
			List<Cart> checkedList = cartDAO.findProductbycust(u.getCustomer_id());
			System.out.println("Size is"+checkedList.size());
			return new ModelAndView("order_review", "checkedList", checkedList);
		} catch(Exception e) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("index");
		} 
	}	

		
		
		@RequestMapping(value="/success.htm", method=RequestMethod.POST)
		public ModelAndView success(HttpServletRequest request) 
				throws Exception{
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			List<Cart> checkList = cartDAO.findProductbycust(u.getCustomer_id());
			System.out.println("Size is"+checkList.size());
			String streetAddress = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pnumber = request.getParameter("phonenumber");
			String email = request.getParameter("email");
			request.getSession().setAttribute("address", streetAddress);
			request.getSession().setAttribute("city", city);
			request.getSession().setAttribute("state", state);
			request.getSession().setAttribute("pnumber", pnumber);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("checkedList", checkList);
			
			for(Cart p:checkList) {
				int pid = p.getProduct().getPid();
				int pqty = p.getProduct().getQuantity();
				int cqty = p.getQuantity();
				int qty = pqty - cqty;
				productDAO.updateQuantity(pid, qty);
				cartDAO.updateStatus(p);
			}
		    	
				return new ModelAndView("order-Success", "checkedList", checkList);
				
		    }
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
			
		
		
		@RequestMapping(value="/orderHistory.htm", method=RequestMethod.GET)
		public ModelAndView techsPurchased(HttpServletRequest request) 
				throws Exception{
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			
			List<Cart> checkedList  = cartDAO.getOrderedProducts(u);
			request.getSession().setAttribute("checkedList", checkedList);
			
			return new ModelAndView("orderedProducts","checkedList", checkedList);
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	
		
		@RequestMapping(value = "/report.pdf", method = RequestMethod.GET)
		public ModelAndView generateReport(HttpServletRequest request) {
			try {
			HttpSession session = request.getSession();
			Customer u = (Customer) session.getAttribute("loggedInUser");
			if (u == null) {
				return new ModelAndView("index");
			}
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("checkedList", request.getSession().getAttribute("checkedList"));
			model.put("address", request.getSession().getAttribute("address"));
			model.put("city", request.getSession().getAttribute("city"));
			model.put("state", request.getSession().getAttribute("state"));
			model.put("pnumber", request.getSession().getAttribute("pnumber"));
			model.put("email", request.getSession().getAttribute("email"));
			model.put("user", request.getSession().getAttribute("loggedInUser"));
			return new ModelAndView(new PDFView(), model);
		}
			catch(Exception e) {
				HttpSession session = request.getSession();
				session.invalidate();
				return new ModelAndView("index");
			} 
		}	

}
