package com.revature.dao;

import java.sql.Blob;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;

public interface ReimbursementDao {

	public Employee CreateNewEmp(String us, String pw, String fname, 
			String lname, String email); // done
	public boolean LoginIn(String us, String pw); // done
	public Employee GetUserInfo(String us); // done
	
	public Reimbursement CreateNewReimbursement(int amount, String description, Blob receipt, 
			int ty, Employee emp);
	
	public List<Reimbursement> AllReimbursementsEver(); //done
	public List<Reimbursement> AllReimbursementForUser(Employee emp); //done
	public List<Reimbursement> AllresolvedReimbursementForUser(Employee emp); // done
	public List<Reimbursement> AllunresolvedReimbursementForUser(Employee emp); // done
	public List<Reimbursement> AllNewPendingReimbursement(); // done
	public List<Reimbursement> AllResolvedReimbursement(); // done
	
	public List<Employee> AllEmployees(); // done
	public Employee EditEmployeeInfo(String un, String pw, String fname, 
			String lname, String email, String oun); // not done
}
