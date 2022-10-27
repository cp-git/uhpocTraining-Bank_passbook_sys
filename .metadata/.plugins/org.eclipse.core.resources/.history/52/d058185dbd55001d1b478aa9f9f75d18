package com.cpa.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Customer;
import com.cpa.repository.CustRepo;

public class CustRepoTest {
	static 	CustRepo custRepo ;
	static DBManager dbm = null;
	Connection con = null;
	static List<Customer> list = null;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		 custRepo = new CustRepo();
		dbm = DBManager.getDBManager();
//		map = new HashMap<>();
//	
		list = new ArrayList<Customer>();
	}

	@Before
	public void setUp() throws Exception {
		con = dbm.getConnection();
	}

	//METHOD TO CHECK getAllCustomers()
	@Test
	public void TestGetAllCustomers()
	{
		ArrayList<Customer> customers_actual = new ArrayList<>();
		Customer customer = new Customer(22, 1, "BOIN0022", "Kshitija","Talera Nagar", "Talera Nagar", "Pune", "778613310");
		customers_actual.add(customer);
		
		list = custRepo.getAllCustomers();
		System.out.println(custRepo.getAllCustomers());
		
		 assertFalse(customers_actual.size() == list.size() );

	
	}
	
	//METHOD TO CHECK isCustExist
	@Test
	public void testIsCustExist()
	{
		Boolean status = custRepo.isCustExist("868613310");
		
		assertTrue("success", status);
		
		
	}

	//METHOD TO CHECK insertCustomer
	@Test
	public void testInsertCustomer() throws SQLException, IOException {
		
		String acc_num = custRepo.insertCustomer("Niraj", "Sangamner","Jalgoan","Nagar", "6589231477");
		
		assertNotSame(acc_num, "BOIN0027");
		
		}
	
	
	//METHOD TO CHECK getCustomer
	@Test
	public void testGetCustomer() throws IOException
	{
		Properties props = new Properties();

		FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
		props.load(fs);
		int bank_id = Integer.parseInt(props.getProperty("bankId"));
		Customer customer1 = new Customer(29, bank_id, "BOIN0029", "Shradha", "Chinchwad", "Pimpri", "Pune",
				"9856322145");

		Customer customer2 =  custRepo.getCustomer("BOIN0029");
		assertEquals(customer1.getCust_name(), customer2.getCust_name());
		assertEquals(customer1.getAccount_number(), customer2.getAccount_number());
		assertEquals(customer1.getCity(), customer2.getCity());
		assertEquals(customer1.getAddress1(), customer2.getAddress1());
		assertEquals(customer1.getAddress2(), customer2.getAddress2());
		assertEquals(customer1.getPhone(), customer2.getPhone());

	}
	
	
	@After
	public void tearDown() {
		dbm.closeConnection(con);
		System.gc();
	}

}
