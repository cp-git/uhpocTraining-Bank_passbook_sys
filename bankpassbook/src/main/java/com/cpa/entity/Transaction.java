package com.cpa.entity;

import java.util.Date;

public class Transaction {

	int tran_id;
	int cust_seq_id;
	Date tran_date;
	String tran_details;
	Double credit_amt;
	Double debit_amt;
	
	
	
	//default constructor
	public Transaction() {
		super();
	}


	//parameterised constructor
	public Transaction(int tran_id, int cust_seq_id, Date tran_date, String tran_details, Double credit_amt,
			Double debit_amt) {
		super();
		this.tran_id = tran_id;
		this.cust_seq_id = cust_seq_id;
		this.tran_date = tran_date;
		this.tran_details = tran_details;
		this.credit_amt = credit_amt;
		this.debit_amt = debit_amt;
	}


	//gettters and setters
	public int getTran_id() {
		return tran_id;
	}


	public void setTran_id(int tran_id) {
		this.tran_id = tran_id;
	}


	public int getCust_seq_id() {
		return cust_seq_id;
	}


	public void setCust_seq_id(int cust_seq_id) {
		this.cust_seq_id = cust_seq_id;
	}


	public Date getTran_date() {
		return tran_date;
	}


	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}


	public String getTran_details() {
		return tran_details;
	}


	public void setTran_details(String tran_details) {
		this.tran_details = tran_details;
	}


	public Double getCredit_amt() {
		return credit_amt;
	}


	public void setCredit_amt(Double credit_amt) {
		this.credit_amt = credit_amt;
	}


	public Double getDebit_amt() {
		return debit_amt;
	}


	public void setDebit_amt(Double debit_amt) {
		this.debit_amt = debit_amt;
	}



	//tostring  method
	@Override
	public String toString() {
		return "Transaction [tran_id=" + tran_id + ", cust_seq_id=" + cust_seq_id + ", tran_date=" + tran_date
				+ ", tran_details=" + tran_details + ", credit_amt=" + credit_amt + ", debit_amt=" + debit_amt + "]";
	}
	
	

	
	
	
	
	
	
	
	
}
