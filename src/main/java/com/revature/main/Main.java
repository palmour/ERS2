 package com.revature.main;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;
import com.revature.domain.UserNameAlreadyExist;

public class Main {

	public static void main(String[] args) {
		/*
		List<Reimbursement> rlist = new ArrayList();
		List<Reimbursement> rlist2 = new ArrayList();
		List<Reimbursement> rlist3 = new ArrayList();
		List<Employee> elist = new ArrayList();
 		ReimbursementDaoImpl dao = new ReimbursementDaoImpl();
		Employee emp = new Employee();
		emp.setU_ID(1);
		rlist = dao.AllReimbursementForUser(emp);
		System.out.println(rlist);
		System.out.println(rlist.size());
		System.out.println();
		System.out.println();
		rlist2 = dao.AllNewPendingReimbursement();
		System.out.println(rlist2);
		System.out.println(rlist2.size());
		System.out.println();
		System.out.println();
		rlist3 = dao.AllApprovedReimbursement();
		System.out.println(rlist3);
		System.out.println(rlist3.size());
		
		System.out.println();
		System.out.println();
		elist = dao.AllEmployees();
		System.out.println(elist);
		System.out.println(elist.size());
		
		
		*/
		Employee temp = new Employee();
		List<Reimbursement> rlist = new ArrayList();
		temp.setU_USERNAME("cpalm");
		temp.setU_ID(26);
		ReimbursementDao rd = new ReimbursementDaoImpl();
		rlist = rd.AllReimbursementForUser(temp);
		System.out.println(rlist);
		System.out.println(rlist.size());
		
		
		Reimbursement rtemp = new Reimbursement();
		rtemp = rd.CreateNewReimbursement(54, "candy", null, 3, temp);
		System.out.println(rtemp);
		
		rd.updateReimbursement(3, 22, temp);
		rlist = rd.AllReimbursementsEver();
		System.out.println(rlist);
		
		
	}

}
