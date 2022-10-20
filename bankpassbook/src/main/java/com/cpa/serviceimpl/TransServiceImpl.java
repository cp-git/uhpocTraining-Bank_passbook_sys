package com.cpa.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cpa.entity.Transaction;
import com.cpa.repository.CustRepo;
import com.cpa.repository.TransRepo;
import com.cpa.service.CustService;
import com.cpa.service.TransService;

public class TransServiceImpl implements TransService {

	CustRepo custRepo = new CustRepo();
	TransRepo transRepo = new TransRepo();
	CustService custService = new CustServiceImpl();
	Map<Integer, Transaction> trans_map = new HashMap<Integer, Transaction>();

	public static void main(String[] args) {

		TransServiceImpl serviceImpl = new TransServiceImpl();
		// System.out.println(serviceImpl.initializeHashMap());
//		System.out.println(serviceImpl.getTransactionByAccNum("BOIN0022"));
//		System.out.println(serviceImpl.getTransactionId("BOIN0022"));
		System.out.println(serviceImpl.getCurrentBalance(1));
	}

	
	//// METHOD TO INITIALIZE HASHMAP FOR TRANSACTION ENTITY BY TRANSACTION TABLE IN
	//// DB
	@Override
	public HashMap<Integer, Transaction> initializeHashMap() {
		ArrayList<Transaction> trArrayList = transRepo.getAllTransactions();

		for (int i = 0; i < trArrayList.size(); i++) {
			Transaction transaction = trArrayList.get(i);

			Integer al_tran_id = transaction.getTran_id();
			trans_map.put(al_tran_id, transaction);

		}

		return (HashMap<Integer, Transaction>) trans_map;

	}

	


	// METHOD TO GET TRANSACTION BY ACCOUNT NUMBER
	@Override
	public ArrayList<Transaction> getTransactionByAccNum(String acc_number) {
		// TODO Auto-generated method stub
		Integer tran_cust_id = custService.getCust_seq_idByAccNum(acc_number);

		int flag = 1;

		TransService tranService = new TransServiceImpl();

		ArrayList<Transaction> arrayList = new ArrayList<>();
		trans_map = tranService.initializeHashMap();

		for (Transaction transaction : trans_map.values()) {
//				System.out.println("@@@@@@@@@@");
//				System.out.println(cust);

			Integer tranmap_cust_seq_id = transaction.getCust_seq_id();

			if (tranmap_cust_seq_id.equals(tran_cust_id)) {

				arrayList.add(transaction);

			}

		}

		return arrayList;

	}

//FUNCTION TO GET INSERT INTO TRANSACTION ENTITY
	public String addTranDetails(String tran_date, String tran_details, Double credit_amt, Double debit_amt,
			String acc_num) throws SQLException, IOException {
		// TODO Auto-generated method stub

		int cust_seq_id = custService.getCust_seq_idByAccNum(acc_num);
		return transRepo.insertTransaction(cust_seq_id, tran_date, tran_details, credit_amt, debit_amt);

	}
	
	
	public ArrayList<Integer> getTransactionId(String acc_number) 
	{
		Integer tran_cust_id = custService.getCust_seq_idByAccNum(acc_number);

		int flag = 1;

		TransService tranService = new TransServiceImpl();

		ArrayList<Integer> tran_id_list = new ArrayList<>();
		trans_map = tranService.initializeHashMap();

		for (Transaction transaction : trans_map.values()) {
//				System.out.println("@@@@@@@@@@");
//				System.out.println(cust);

			Integer tranmap_cust_seq_id = transaction.getCust_seq_id();

			if (tranmap_cust_seq_id.equals(tran_cust_id)) {

				int tranmap_tran_id = transaction.getTran_id();
				
				tran_id_list.add(tranmap_tran_id);

			}

		}

		return tran_id_list;
	}
	
	public double getCurrentBalance(int tran_id) throws NullPointerException
	{
	

		int flag = 1;

		TransService tranService = new TransServiceImpl();

//		ArrayList<Transaction> arrayList = new ArrayList<>();
		trans_map = tranService.initializeHashMap();

		for (Transaction transaction : trans_map.values()) {
//				System.out.println("@@@@@@@@@@");
//				System.out.println(cust);

			System.out.println("%%%%%%%%%%%%%");
			int tranmap_tran_id = transaction.getTran_id();
			System.out.println(tranmap_tran_id);
			
			if (tranmap_tran_id == tran_id) {
				
				System.out.println("Entered in if loop");
				double tranmap_credit_amt = transaction.getCredit_amt();
				double tranmap_debit_amt = transaction.getDebit_amt();
				System.out.println("$$$$$$$$$$$$$$");
				System.out.println(tranmap_credit_amt);
				System.out.println(tranmap_debit_amt);
				double current_balance = tranmap_credit_amt + tranmap_debit_amt;
				 
				return current_balance;
				
			}

		}

		return 0.0;
		
		
	}

}
