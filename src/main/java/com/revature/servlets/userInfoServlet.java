package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;

public class userInfoServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		Employee temp = new Employee();
		ReimbursementDaoImpl dao = new ReimbursementDaoImpl();
		temp = dao.GetUserInfo("Cgarcia");
		Gson gson = new Gson();
		String rJSON = gson.toJson(temp);
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(rJSON);
		
		
	}

}
