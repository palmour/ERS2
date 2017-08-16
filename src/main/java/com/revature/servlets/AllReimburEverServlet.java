package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Reimbursement;

public class AllReimburEverServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{

		ReimbursementDao rd = new ReimbursementDaoImpl();
		List<Reimbursement> rlist = rd.AllReimbursementsEver();
	
		Gson gson = new Gson();
		String rJSON = gson.toJson(rlist);
	
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.write(rJSON);
	}
}
