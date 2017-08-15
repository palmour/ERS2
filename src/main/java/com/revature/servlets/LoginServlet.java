 package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String us = request.getParameter("uname");
		String pw = request.getParameter("pass");
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		
		boolean authentication = rd.LoginIn(us, pw);
		if(authentication) {
			
			Employee loggingIn = rd.GetUserInfo(us, pw);
			int userRole = loggingIn.getUR_ID();
			
			String page = "#";
			
			if(userRole > 2) {page = "./employee_dash.html";}
			else {page = "./manager_dash.html";}
			
			response.setContentType("text/html");
			HttpSession session = request.getSession();
			session.setAttribute("username", us);
			session.setAttribute("password", pw);
			response.sendRedirect(page);
			
		}else {
			Gson gson = new Gson();
			String loginFailedMessage = "<p>Invalid username/password.<br/><a href=\"home.html\">Try Again</a></p>";
			
			PrintWriter out = response.getWriter();
			out.println(loginFailedMessage);
		}
	}
}
