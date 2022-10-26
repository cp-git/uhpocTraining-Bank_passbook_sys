package com.cpa.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Customer;
import com.cpa.service.CustService;

public class CustServiceImplTest {

	static CustService custService = null;
	static Map<String, Customer> map = null;
	static DBManager dbm = null;
	static List<Customer> list = null;
	Connection con = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before");
		custService = new CustServiceImpl();
		map = new HashMap<>();
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
		// Entries from Database
		Customer customer1 = new Customer(29, bank_id, "BOIN0029", "Shradha", "Chinchwad", "Pimpri", "Pune",
				"9856322145");
		Customer customer2 = new Customer(27, bank_id, "BOIN0027", "Komal", "Talera Nagar", "Talera Nagar", "Pune",
				"868613310");

		list.add(customer1);
		list.add(customer2);
		map = custService.initializeHashmap();
//		System.out.println(map.get("BOIN0027"));
//		System.out.println(customer2);
		assertEquals(customer2.getCust_name(), map.get("BOIN0027").getCust_name());
		assertEquals(customer2.getAccount_number(), map.get("BOIN0027").getAccount_number());
		assertEquals(customer2.getCity(), map.get("BOIN0027").getCity());
		assertEquals(customer2.getAddress1(), map.get("BOIN0027").getAddress1());
		assertEquals(customer2.getAddress2(), map.get("BOIN0027").getAddress2());
		assertEquals(customer2.getPhone(), map.get("BOIN0027").getPhone());

		assertEquals(customer1.getCust_name(), map.get("BOIN0029").getCust_name());
		assertEquals(customer1.getAccount_number(), map.get("BOIN0029").getAccount_number());
		assertEquals(customer1.getCity(), map.get("BOIN0029").getCity());
		assertEquals(customer1.getAddress1(), map.get("BOIN0029").getAddress1());
		assertEquals(customer1.getAddress2(), map.get("BOIN0029").getAddress2());
		assertEquals(customer1.getPhone(), map.get("BOIN0029").getPhone());

	}

	// METHOD TO CHECK checkCust()
	@Test
	public void testCheckCust() {
		boolean status = custService.checkCust("9856322145");
		boolean status_2 = custService.checkCust("9856322146");
		assertTrue("success", status);
		assertFalse("success", status_2);

	}

	// METHOD TO CHECK checkCustByAccNum METHOD
	@Test
	public void testCheckCustByAccNum() {
		boolean status = custService.checkCustByAccNum("BOIN0029");
		boolean status_2 = custService.checkCustByAccNum("BOIN0000");
		assertTrue("success", status);
		assertFalse("success", status_2);

	}

	// METHOD TO CHECK checkCustByAccNum METHOD
	@Test
	public void testAddCust() throws SQLException, IOException {

		// String status returns inserted customer's account number
		String status = custService.addCust("Shalva", "Chinchwad", "Pimpri", "Pune", "6826322145");

		assertNotSame(status, "BOIN0027");

	}

	// METHOD TO CHECK getCustomerByAccountNumber
	@Test
	public void testGetCustomerByAccountNumber() throws IOException {

		Properties props = new Properties();

		FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
		props.load(fs);
		int bank_id = Integer.parseInt(props.getProperty("bankId"));
		Customer customer1 = new Customer(29, bank_id, "BOIN0029", "Shradha", "Chinchwad", "Pimpri", "Pune",
				"9856322145");

		Customer customer2 = custService.getCustomerByAccountNumber("BOIN0029");
		assertEquals(customer1.getCust_name(), customer2.getCust_name());
		assertEquals(customer1.getAccount_number(), customer2.getAccount_number());
		assertEquals(customer1.getCity(), customer2.getCity());
		assertEquals(customer1.getAddress1(), customer2.getAddress1());
		assertEquals(customer1.getAddress2(), customer2.getAddress2());
		assertEquals(customer1.getPhone(), customer2.getPhone());

	}
	
	// METHOD TO CHECK getCust_seq_idByAccNum
	@Test
	public void testGetCust_seq_idByAccNum() {
		int actual_cust_seq_id = custService.getCust_seq_idByAccNum("BOIN0029");
		assertEquals("Success", actual_cust_seq_id, 29);
		assertNotEquals("Failure", actual_cust_seq_id, 27);
	}

	
	
	@After
	public void tearDown() {
		dbm.closeConnection(con);
	}

}