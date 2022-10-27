package com.cpa.repo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cpa.connectionpooling.TestDBManager;
import com.cpa.entity.Transaction;
import com.cpa.repository.TransRepo;

public class TransRepoTest {

	static 	TransRepo transRepo ;
	static TestDBManager dbm = null;
	Connection con = null;
	static List<Transaction> list = null;

	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		 transRepo = new TransRepo();
		dbm = TestDBManager.getDBManager();
//		map = new HashMap<>();
//	
		list = new ArrayList<Transaction>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}

	//METHOD TO getAllTransactions()
	@Test
	public void testGetAllTransactions()
	{
		ArrayList<Transaction> transactions_actual = new ArrayList<>();
		java.util.Date date =  (java.util.Date) new Date(2022,01,10); ;
		
		
		Transaction transaction = new Transaction(1,22,date,"Salary",50000d,0.0d);
		ArrayList<Transaction> arrayList_expected= transRepo.getAllTransactions();
	  
		transactions_actual.add(transaction);
		
		 assertFalse(transactions_actual.size() ==arrayList_expected.size());
	  
	  
	}
	
	//METHOD TO insertTransaction
	@Test
	public void testInsertTransaction() throws SQLException, IOException
	{
		//String status should contain message "Transaction inserted successfully" after successfull insertion

		String status = transRepo.insertTransaction(27,"2022/25/2", "SWIGGY",0.0d , 40000d);

		assertNotSame(status, "Transaction not inserted");

	}

	@After
	public void tearDown() {
		dbm.closeConnection(con);
		System.gc();
	}
}
