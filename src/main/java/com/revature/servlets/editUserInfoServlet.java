package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;

public class editUserInfoServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			
			String username = "I'm the best!";
			String pass = "password";
			String fname = "Cecilia";
			String lname = "Garcia";
			String email = "cgarcia@mail.com";
			String orUN = "Sofia";
			Employee temp = new Employee();
			ReimbursementDaoImpl dao = new ReimbursementDaoImpl();
			dao.EditEmployeeInfo(username, pass, fname, lname, email, orUN);
			temp = dao.GetUserInfo(username);
			Gson gson = new Gson();
			String rJSON = gson.toJson(temp);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(rJSON);
			
			
		}

}
