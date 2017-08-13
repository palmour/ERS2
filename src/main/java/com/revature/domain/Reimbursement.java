package com.revature.domain;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {

	@Override
	public String toString() {
		return "Reimbursement [R_ID=" + R_ID + ", R_AMOUNT=" + R_AMOUNT + ", R_Description=" + R_Description
				+ ", R_RECEIPT=" + R_RECEIPT + ", R_SUBMITTED=" + R_SUBMITTED + ", R_SOLVED=" + R_SOLVED
				+ ", U_ID_AUTHOR=" + U_ID_AUTHOR + ", U_ID_RESOLVER=" + U_ID_RESOLVER + ", RT_TYPE=" + RT_TYPE
				+ ", RT_STATUS=" + RT_STATUS + "]";
	}
	private int R_ID;
	private int R_AMOUNT;
	private String R_Description;
	private Blob R_RECEIPT;
	private Timestamp R_SUBMITTED;
	private Timestamp R_SOLVED;
	private int U_ID_AUTHOR;
	private int U_ID_RESOLVER;
	private int RT_TYPE;
	private int RT_STATUS;
	public int getR_ID() {
		return R_ID;
	}
	public void setR_ID(int r_ID) {
		R_ID = r_ID;
	}
	public int getR_AMOUNT() {
		return R_AMOUNT;
	}
	public void setR_AMOUNT(int r_AMOUNT) {
		R_AMOUNT = r_AMOUNT;
	}
	public String getR_Description() {
		return R_Description;
	}
	public void setR_Description(String r_Description) {
		R_Description = r_Description;
	}
	public Blob getR_RECEIPT() {
		return R_RECEIPT;
	}
	public void setR_RECEIPT(Blob r_RECEIPT) {
		R_RECEIPT = r_RECEIPT;
	}
	public Timestamp getR_SUBMITTED() {
		return R_SUBMITTED;
	}
	public void setR_SUBMITTED(Timestamp r_SUBMITTED) {
		R_SUBMITTED = r_SUBMITTED;
	}
	public Timestamp getR_SOLVED() {
		return R_SOLVED;
	}
	public void setR_SOLVED(Timestamp r_SOLVED) {
		R_SOLVED = r_SOLVED;
	}
	public int getU_ID_AUTHOR() {
		return U_ID_AUTHOR;
	}
	public void setU_ID_AUTHOR(int u_ID_AUTHOR) {
		U_ID_AUTHOR = u_ID_AUTHOR;
	}
	public int getU_ID_RESOLVER() {
		return U_ID_RESOLVER;
	}
	public void setU_ID_RESOLVER(int u_ID_RESOLVER) {
		U_ID_RESOLVER = u_ID_RESOLVER;
	}
	public int getRT_TYPE() {
		return RT_TYPE;
	}
	public void setRT_TYPE(int rT_TYPE) {
		RT_TYPE = rT_TYPE;
	}
	public int getRT_STATUS() {
		return RT_STATUS;
	}
	public void setRT_STATUS(int rT_STATUS) {
		RT_STATUS = rT_STATUS;
	}
	public Reimbursement(int r_ID, int r_AMOUNT, String r_Description, Blob r_RECEIPT, Timestamp r_SUBMITTED,
			Timestamp r_SOLVED, int u_ID_AUTHOR, int u_ID_RESOLVER, int rT_TYPE, int rT_STATUS) {
		super();
		R_ID = r_ID;
		R_AMOUNT = r_AMOUNT;
		R_Description = r_Description;
		R_RECEIPT = r_RECEIPT;
		R_SUBMITTED = r_SUBMITTED;
		R_SOLVED = r_SOLVED;
		U_ID_AUTHOR = u_ID_AUTHOR;
		U_ID_RESOLVER = u_ID_RESOLVER;
		RT_TYPE = rT_TYPE;
		RT_STATUS = rT_STATUS;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
}
