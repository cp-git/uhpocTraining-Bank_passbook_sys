package com.cpa.entity;

public class Customer {

	
	int cust_seq_id;
	int bank_id;
	String account_number;
	String cust_name;
	String address1;
	String address2;
	String city;
	String phone;
	
	
	
	//DEFAULT CUSTOMER
	public Customer() {
		super();
	}

	//PARAMETERISED CUSTOMER
	public Customer(int cust_seq_id, int bank_id, String account_number, String cust_name, String address1,
			String address2, String city, String phone) {
		super();
		this.cust_seq_id = cust_seq_id;
		this.bank_id = bank_id;
		this.account_number = account_number;
		this.cust_name = cust_name;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.phone = phone;
	}

	
	
	//GETTERS AND SETTERS
	public int getCust_seq_id() {
		return cust_seq_id;
	}

	public void setCust_seq_id(int cust_seq_id) {
		this.cust_seq_id = cust_seq_id;
	}

	public int getBank_id() {
		return bank_id;
	}

	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [cust_seq_id=" + cust_seq_id + ", bank_id=" + bank_id + ", account_number=" + account_number
				+ ", cust_name=" + cust_name + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city
				+ ", phone=" + phone + "]";
	}
	
	//TO STRING
	
	
	
	
	
	
}
