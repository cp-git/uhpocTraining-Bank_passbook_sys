package com.cpa.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Transaction;
import com.cpa.service.TransService;

public class TransServiceImplTest {

	static TransService transService = null;
	static Map<Integer, Transaction> map = null;
	static DBManager dbm = null;
	static List<Transaction> list = null;
	Connection con = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		transService = new TransServiceImpl();
		map = new HashMap<Integer,Transaction>();
		dbm = DBManager.getDBManager();
		list = new ArrayList<>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}


	// method to check initializeHashmap() method
	@Test
	public void testinitializeHashmap() throws IOException {

		Properties props = new Properties();

		FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
		props.load(fs);
		int bank_id = Integer.parseInt(props.getProperty("bankId"));
		java.util.Date date ;
		date = new Date("01/10/2022");
		
		// Entries from Database
		Transaction transaction = new Transaction(1,22,date,"Salary",50000d,0.0d);
		System.out.println(transaction);
		list.add(transaction);
		
		map = transService.initializeHashMap();

		assertEquals(transaction.getTran_id(),map.get(1).getTran_id() );
		assertEquals(transaction.getTran_details(),map.get(1).getTran_details() );
		assertEquals(transaction.getCredit_amt(),map.get(1).getCredit_amt() );
		assertEquals(transaction.getDebit_amt(),map.get(1).getDebit_amt() );


	}


	
	//METHOD TO CHECK getTransactionByAccNum
	@Test
	 public void testGetTransactionByAccNum()
	 {
		java.util.Date date =  (java.util.Date) new Date(2022,01,10); ;
		
		
		Transaction transaction = new Transaction(1,22,(java.sql.Date)date,"Salary",50000d,0.0d);
		
		Transaction transaction_2 = new Transaction(2,22,(java.sql.Date)date,"Salary October",50000d,0.0d);

		
		list.add(transaction);
		list.add(transaction_2);
		
		System.out.println(list);
		
		ArrayList<Transaction> arrayList = new ArrayList<>();
		
		arrayList = transService.getTransactionByAccNum("BOIN0022");
		System.out.println(arrayList);
//		 assertTrue(list.size() ==arrayList.size() &&  arrayList.containsAll(list));
		 assertTrue(list.size() ==arrayList.size());
	 }
	
	//METHOD TO CHECK addTranDetails
	@Test
	public void testAddTranDetails() throws SQLException, IOException
	{
		//String status should contain message "Transaction inserted successfully" after successfull insertion
		String status = transService.addTranDetails("2022/25/2", "SWIGGY",0.0d , 40000d, "BOIN0029" );
		
		assertNotSame(status, "Transaction not inserted");
	}
	
	@Test
	public void testGetTransactionId()
	{
		List<Integer> tran_id_list = new ArrayList<>();
		List<Integer> tran_id_list_expected = new ArrayList<>();

		tran_id_list= transService.getTransactionId("BOIN0022");
		tran_id_list_expected.add(1);
		tran_id_list_expected.add(2);
		
	 assertTrue(tran_id_list.size() ==tran_id_list_expected.size() &&  tran_id_list.containsAll(tran_id_list_expected));

		
	 
	}
	
	@Test
	public void testGetCurrentBalance()
	{
		Double curr_balance = transService.getCurrentBalance(3);
		Double curr_balance_expected = 50000d;
		
		assertEquals(curr_balance, curr_balance_expected);
	}
	
	
}