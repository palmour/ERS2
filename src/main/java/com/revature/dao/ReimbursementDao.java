package com.revature.dao;

import java.sql.Blob;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;

public interface ReimbursementDao {

	public Employee CreateNewEmp(String us, String pw, String fname, 
			String lname, String email);
	public boolean LoginIn(String us, String pw);
	public Employee GetUserInfo(String us, String pw);
	
	public Reimbursement CreateNewReimbursement(int amount, String description, Blob receipt, 
			int ty, Employee emp);
	
	public List<Reimbursement> AllReimbursementForUser(Employee emp);
	public List<Reimbursement> AllNewPendingReimbursement();
	public List<Reimbursement> AllApprovedReimbursement();
	
	public List<Employee> AllEmployees();
}
