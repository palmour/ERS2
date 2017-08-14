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
			response.setContentType("text/html");
			String page = "./employee_dash.html";
			
			HttpSession session = request.getSession();
			session.setAttribute("username", us);
			
			response.sendRedirect(page);
		}else {
			Gson gson = new Gson();
			String loginFailedMessage = "Invalid username/password.<br/><a href='./home.html'>Try Again</a>";
			String loginFailedJson = gson.toJson(loginFailedMessage);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			out.write(loginFailedJson);
		}
	}
}
