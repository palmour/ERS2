package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.domain.Employee;
import com.revature.domain.Reimbursement;
import com.revature.domain.UserNameAlreadyExist;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	// implemented
	//semi tested
	// creates a new employee to be added to the DATABASE
	// also returns the U_ID and sets the UR_ID to 3 as the default. 
	@Override
	public Employee CreateNewEmp(String us, String pw, String fname, String lname, String email) {
		Employee temp = new Employee();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			System.out.println("connected");
			String sql = "{CALL P_ADD_NEW_USER(?, ?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, us);
			cs.setString(3, pw);
			cs.setString(4, fname);
			cs.setString(5, lname);
			cs.setString(6, email);
			cs.execute();
			temp.setU_ID(cs.getInt(1));
			temp.setU_USERNAME(us);
			temp.setU_PASSWORD(pw);
			temp.setU_FIRSTNAME(fname);
			temp.setU_LASTNAME(lname);
			temp.setU_EMAIL(email);
			
			cs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return temp;
	}

	
	// implemented
	// this login returns true or false;
	@Override
	public boolean LoginIn(String us, String pw) {
		Employee temp = new Employee();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "{CALL P_CHECK_USER_PASSWORD( ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.setString(3, us);
			cs.setString(4, pw);
			cs.execute();
			if(cs.getString(1).equals(us) && cs.getString(2).equals(pw)) {
				System.out.println("UserName and password match");
				return true;
			}else if(!cs.getString(1).equals(us) || !cs.getString(2).equals(pw) ) {
				System.out.println("Either username or password does not match");
				return false;
			}
			
		}catch(SQLException e) {
			System.out.println("Either username or password does not match");
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}


	// implemented
	// this login returns user info for current user
	// that's if their info does match our info
	@Override
	public Employee GetUserInfo(String us, String pw) {
		Employee temp = new Employee();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "{CALL P_GET_USER_INFO_FROM_PASSWORD( ?, ?, ?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setString(1, us);
			cs.setString(2, pw);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.execute();
			temp.setU_ID(cs.getInt(3));
			temp.setU_USERNAME(us);
			temp.setU_PASSWORD(pw);
			temp.setU_FIRSTNAME(cs.getString(4));
			temp.setU_LASTNAME(cs.getString(5));
			temp.setU_EMAIL(cs.getString(6));
			temp.setUR_ID(cs.getInt(7));
		}catch(SQLException e) {
			System.out.println("User info does not match our records.");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	

	
	@Override
	public Reimbursement CreateNewReimbursement(int amount, String description, int ty, Employee emp) {
		Reimbursement temp = new Reimbursement();
		CallableStatement cs = null;
		InputStream in = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "{CALL P_ADD_NEW_REIMBUR( ?, ?, ?, ?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, amount);
			cs.setString(2, description);
			cs.setInt(3, emp.getU_ID());
			cs.setInt(4, ty);
			cs.execute();
			temp.setR_AMOUNT(amount);
			temp.setR_Description(description);
			temp.setRT_TYPE(ty);
			temp.setU_ID_AUTHOR(emp.getU_ID());
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}  
		
		return temp;
	}


	
	// return all request ever submitted by the user
	// either resolved or unresolved
	@Override
	public List<Reimbursement> AllReimbursementForUser(Employee emp) {
		List<Reimbursement> rlist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = emp.getU_ID();
				int rids = rs.getInt("R_ID");
				int amount = rs.getInt("R_AMOUNT");
				String des = rs.getString("R_DESCRIPTION");
				Blob rreceipt = rs.getBlob("R_RECEIPT");
				Timestamp rsub = rs.getTimestamp("R_SUBMITTED");
				Timestamp rreso = rs.getTimestamp("R_RESOLVED");
				int idreso = rs.getInt("U_ID_RESOLVER");
				int rttype = rs.getInt("RT_TYPE");
				int rtstatus = rs.getInt("RT_STATUS");
				
				Reimbursement r = new Reimbursement(rids, amount, des, rreceipt, rsub, 
						rreso, uid, idreso, rttype, rtstatus);
				rlist.add(r);
				
			}
			rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rlist;
	}
	
	@Override
	public List<Reimbursement> AllNewPendingReimbursement(){
		List<Reimbursement> rlist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE "
					+ "RT_STATUS = 1 OR RT_STATUS = 2";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = rs.getInt("U_ID_AUTHOR");
				int rids = rs.getInt("R_ID");
				int amount = rs.getInt("R_AMOUNT");
				String des = rs.getString("R_DESCRIPTION");
				Blob rreceipt = rs.getBlob("R_RECEIPT");
				Timestamp rsub = rs.getTimestamp("R_SUBMITTED");
				Timestamp rreso = rs.getTimestamp("R_RESOLVED");
				int idreso = rs.getInt("U_ID_RESOLVER");
				int rttype = rs.getInt("RT_TYPE");
				int rtstatus = rs.getInt("RT_STATUS");
				
				Reimbursement r = new Reimbursement(rids, amount, des, rreceipt, rsub, 
						rreso, uid, idreso, rttype, rtstatus);
				rlist.add(r);
				
			}
			rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rlist;
		
	}


	@Override
	public List<Reimbursement> AllApprovedReimbursement() {
		List<Reimbursement> rlist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS WHERE "
					+ "RT_STATUS = 3";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = rs.getInt("U_ID_AUTHOR");
				int rids = rs.getInt("R_ID");
				int amount = rs.getInt("R_AMOUNT");
				String des = rs.getString("R_DESCRIPTION");
				Blob rreceipt = rs.getBlob("R_RECEIPT");
				Timestamp rsub = rs.getTimestamp("R_SUBMITTED");
				Timestamp rreso = rs.getTimestamp("R_RESOLVED");
				int idreso = rs.getInt("U_ID_RESOLVER");
				int rttype = rs.getInt("RT_TYPE");
				int rtstatus = rs.getInt("RT_STATUS");
				
				Reimbursement r = new Reimbursement(rids, amount, des, rreceipt, rsub, 
						rreso, uid, idreso, rttype, rtstatus);
				rlist.add(r);
				
			}
			rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}


	@Override
	public List<Employee> AllEmployees() {
		List<Employee> elist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_USERS";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = rs.getInt("U_ID");
				String us = rs.getString("U_USERNAME");
				String pw = rs.getString("U_PASSWORD");
				String fname = rs.getString("U_FIRSTNAME");
				String lname = rs.getString("U_LASTNAME");
				String email = rs.getString("U_EMAIL");
				String urid = rs.getString("UR_ID");
				
				Employee e = new Employee(uid, us, pw, fname, lname, email, urid);
				elist.add(e);
				
			}rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return elist;
		
	}


	@Override
	public String getStatusByKey(int key) {
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String result = "";
			String sql = "SELECT RS_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE RS_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("RS_STATUS"); return result;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public String getTypeByKey(int key) {
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String result = "";
			String sql = "SELECT RT_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE RT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("RT_TYPE"); return result;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<Reimbursement> AllresolvedReimbursementForUser(Employee emp) {
		List<Reimbursement> rlist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS"
					+ " where U_ID_AUTHOR = "+emp.getU_ID()+" AND RT_STATUS = 3 OR U_ID_AUTHOR = "+emp.getU_ID()+""
							+ " AND RT_STATUS = 4";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = emp.getU_ID();
				int rids = rs.getInt("R_ID");
				int amount = rs.getInt("R_AMOUNT");
				String des = rs.getString("R_DESCRIPTION");
				Blob rreceipt = rs.getBlob("R_RECEIPT");
				Timestamp rsub = rs.getTimestamp("R_SUBMITTED");
				Timestamp rreso = rs.getTimestamp("R_RESOLVED");
				int idreso = rs.getInt("U_ID_RESOLVER");
				int rttype = rs.getInt("RT_TYPE");
				int rtstatus = rs.getInt("RT_STATUS");
				
				Reimbursement r = new Reimbursement(rids, amount, des, rreceipt, rsub, 
						rreso, uid, idreso, rttype, rtstatus);
				rlist.add(r);
				
			}
			rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	@Override
	public List<Reimbursement> AllunresolvedReimbursementForUser(Employee emp) {
		List<Reimbursement> rlist = new ArrayList();
		CallableStatement cs = null;
		try(Connection conn = ConnectionUtil.getConnectionProp()){
			String sql = "SELECT * FROM ERS_REIMBURSEMENTS"
					+ " where U_ID_AUTHOR = "+emp.getU_ID()+" AND RT_STATUS = 1 OR U_ID_AUTHOR = "+emp.getU_ID()+""
							+ " AND RT_STATUS = 2";
			cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				int uid = emp.getU_ID();
				int rids = rs.getInt("R_ID");
				int amount = rs.getInt("R_AMOUNT");
				String des = rs.getString("R_DESCRIPTION");
				Blob rreceipt = rs.getBlob("R_RECEIPT");
				Timestamp rsub = rs.getTimestamp("R_SUBMITTED");
				Timestamp rreso = rs.getTimestamp("R_RESOLVED");
				int idreso = rs.getInt("U_ID_RESOLVER");
				int rttype = rs.getInt("RT_TYPE");
				int rtstatus = rs.getInt("RT_STATUS");
				
				Reimbursement r = new Reimbursement(rids, amount, des, rreceipt, rsub, 
						rreso, uid, idreso, rttype, rtstatus);
				rlist.add(r);
				
			}
			rs.close();	
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return rlist;
	}

}
