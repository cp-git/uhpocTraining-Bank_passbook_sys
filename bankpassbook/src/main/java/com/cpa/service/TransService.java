package com.cpa.service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.cpa.entity.Transaction;

public interface  TransService {

	public HashMap<Integer,Transaction> initializeHashMap();
	
	
	
	

	public ArrayList<Transaction> getTransactionByAccNum(String acc_number);



	public String addTranDetails( String tran_date, String tran_details, Double credit_amt, Double debit_amt,String acc_num) throws SQLException, IOException;
}
