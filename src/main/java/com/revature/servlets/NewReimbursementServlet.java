package com.revature.servlets;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;

@MultipartConfig
public class NewReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String description = request.getParameter("description");
		String typeName = request.getParameter("type");
		int type = 1;
		if(typeName.equals("Option1")) {type=1;}
		else if(typeName.equals("Option2")) {type=2;}
		else if(typeName.equals("Option3")) {type=3;}
		
		Part receiptPart = request.getPart("receipt");
		
		/*
		final byte[] bytes = new byte[1024];
		byte[] buffer = new byte[1024];
		
		File destination = null;
		OutputStream out = null;
		InputStream fileContent = null;
		Clob receiptClob = null;
	
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			destination = new File("/Users/chpal/Documents/sts/workspace/Revature/ERS/photos/input.jpg");
			out = new FileOutputStream(destination);
			fileContent = receiptPart.getInputStream();
			
			int read = 0;
			
			while((read=fileContent.read(bytes)) != -1) {
				
				out.write(bytes, 0, read);
			}
			
			int count;
			while((count=fileContent.read(buffer)) != -1) {
				output.write(buffer, 0, count);
			}
			
			byte[] contents = output.toByteArray();
			
			try{receiptClob = new SerialClob(contents);}
			catch(SerialException e) {e.printStackTrace();}
			catch(SQLException e) {e.printStackTrace();}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(out!=null) {out.close();}
			if(fileContent!=null) {fileContent.close();}
		} */
		
		HttpSession session = request.getSession();
		String un = (String) session.getAttribute("username");
		String pw = (String) session.getAttribute("password");
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		Employee loggedIn = rd.GetUserInfo(un, pw);
		
		rd.CreateNewReimbursement(amount, description, type, loggedIn);
		
		RequestDispatcher reqDisp = request.getRequestDispatcher("./employee_dash.html");
		reqDisp.forward(request, response);
	}
}
