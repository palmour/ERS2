package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;

public class AllReimburForUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		Employee temp = new Employee();
		HttpSession session = req.getSession();
		String us = (String) session.getAttribute("username");
		temp.setU_USERNAME(us);
		List<Reimbursement> rlist = new ArrayList();
		ReimbursementDaoImpl dao = new ReimbursementDaoImpl();
		rlist = dao.AllReimbursementForUser(temp);
		Gson gson = new Gson();
		String rJSON = gson.toJson(rlist);
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(rJSON);
		
		
	}

}