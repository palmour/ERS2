package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;

public class NewUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException{
		String us = request.getParameter("uname");
		String pw = request.getParameter("pass");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		rd.CreateNewEmp(us, pw, fname, lname, email);
		
		HttpSession session = request.getSession();
		session.setAttribute("username", us);
		
		response.setContentType("text/html");
		String page = "./employee_dash.html";
		
		response.sendRedirect(page);
	}
}
