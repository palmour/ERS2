package com.revature.dao;

import java.io.File;
import java.sql.Blob;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;

public interface ReimbursementDao {

	public Employee CreateNewEmp(String us, String pw, String fname, 
			String lname, String email, String role);
	public boolean LoginIn(String us, String pw);
	public Employee GetUserInfo(String us, String pw);
	
	public Reimbursement CreateNewReimbursement(int amount, String description, 
			int ty, Employee emp);
	
	public List<Reimbursement> AllReimbursementForUser(Employee emp);
	public List<Reimbursement> AllNewPendingReimbursement();
	public List<Reimbursement> AllApprovedReimbursement();
	
	public List<Employee> AllEmployees();
	public String getStatusByKey(int key);
	public String getTypeByKey(int key);
	public List<Reimbursement> AllresolvedReimbursementForUser(Employee emp);
	public List<Reimbursement> AllunresolvedReimbursementForUser(Employee emp);
	public List<Reimbursement> AllReimbursementsEver();
	public List<Reimbursement> AllResolvedReimbursement();
	public void updateReimbursement(int status, int r_id, Employee emp);
}
