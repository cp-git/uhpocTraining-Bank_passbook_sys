package com.cpa.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import com.cpa.entity.Customer;

public interface CustService {

	
	
	public HashMap<String,Customer> initializeHashmap();
	
	public boolean checkCust(String phone);
	
	public String addCust(String customer_name, String customer_address1, String customer_address2, String customer_city, String customer_phone) throws SQLException, IOException;
	 
	

	Customer getCustomerByAccountNumber(String acc_no);
	
}
