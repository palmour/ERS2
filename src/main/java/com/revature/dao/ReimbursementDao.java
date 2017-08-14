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
	
	public List<Reimbursement> AllReimbursementsEver();
	public List<Reimbursement> AllReimbursementForUser(Employee emp);
	public List<Reimbursement> AllresolvedReimbursementForUser(Employee emp);
	public List<Reimbursement> AllunresolvedReimbursementForUser(Employee emp);
	public List<Reimbursement> AllNewPendingReimbursement();
	public List<Reimbursement> AllResolvedReimbursement();
	
	public List<Employee> AllEmployees();
	public Employee EditEmployeeInfo(String un, String pw, String fname, 
			String lname, String email, Employee emp);
}
