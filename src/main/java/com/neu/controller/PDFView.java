package com.neu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.model.Cart;
import com.neu.model.Customer;


public class PDFView extends AbstractPdfView {

		@Override
		protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			
			
			pdfdoc.add(new Paragraph("Order Receipt", FontFactory.getFont(FontFactory.HELVETICA, 15)));
			pdfdoc.add(Chunk.NEWLINE);
			Customer cust = (Customer) model.get("user");
			String address =(String) request.getSession().getAttribute("address");
			String city =(String) request.getSession().getAttribute("city");
			String state =(String) request.getSession().getAttribute("state");
			String phoneNumber = (String) request.getSession().getAttribute("pnumber");
			String email = (String)model.get("email");
			@SuppressWarnings("unchecked")
			List<Cart> checkedList = (List<Cart>)model.get("checkedList");
			
			pdfdoc.add(new Paragraph("Customer Details", FontFactory.getFont(FontFactory.HELVETICA, 12)));
			pdfdoc.add(new Paragraph("Customer Name: " + cust.getName(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			pdfdoc.add(new Paragraph("Address: "+ address, FontFactory.getFont(FontFactory.HELVETICA, 10)));
			pdfdoc.add(new Paragraph("City: "+ city, FontFactory.getFont(FontFactory.HELVETICA, 10)));
			pdfdoc.add(new Paragraph("State: "+ state, FontFactory.getFont(FontFactory.HELVETICA, 10)));
			pdfdoc.add(new Paragraph("Phone Number: "+ phoneNumber, FontFactory.getFont(FontFactory.HELVETICA, 10)));
			pdfdoc.add(new Paragraph("Email: "+ cust.getCustomerEmail(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
			
			pdfdoc.add(Chunk.NEWLINE);
			
			pdfdoc.add(new Paragraph("Products Ordered", FontFactory.getFont(FontFactory.HELVETICA, 12)));
			pdfdoc.add(Chunk.NEWLINE);
			PdfPTable table = new PdfPTable(5);
			
			table.addCell("Product ID");
			table.addCell("Name");
			table.addCell("Quantity");
			table.addCell("Price");
			table.addCell("Total");
			
			for(Cart lc : checkedList)
			{
				table.addCell(String.valueOf(lc.getProduct().getPid()));
				table.addCell(lc.getProduct().getProductName());
				table.addCell(String.valueOf(lc.getQuantity()));
				table.addCell(String.valueOf(lc.getProduct().getPrice()));
				table.addCell(String.valueOf(lc.getQuantity()*lc.getProduct().getPrice()));
			}
			
			pdfdoc.add(table);
							
		}

	}

