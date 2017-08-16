package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;

public class DenyReimbursementServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException{
		
		HttpSession session = req.getSession();
		String un= (String)session.getAttribute("username");
		String pw = (String)session.getAttribute("password");
		
		byte[] bytes = new byte[4];
		ServletInputStream is = req.getInputStream();
		String ridString = "";
		is.readLine(bytes, 0, 10);
		for(int i=0; i<bytes.length; i++) {ridString = ridString+ Character.toString((char)(bytes[i]));}
		ridString = ridString.trim();
		int rid = Integer.parseInt(ridString);
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		Employee emp = rd.GetUserInfo(un, pw);
		rd.updateReimbursement(4, rid, emp);
		
		resp.sendRedirect("./manager_dash.html");
	}
}
