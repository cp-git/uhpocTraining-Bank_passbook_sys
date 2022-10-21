package com.cpa.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.util.PSQLException;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Customer;
import com.cpa.exception.CPException;

public class CustRepo {

	private Properties props = null;
	public static void main(String[] args) throws SQLException, IOException {
		CustRepo custRepo = new CustRepo();
		// custRepo.getAllCustomers();
		// System.out.println(custRepo.isCustExist("9865326589"));;
//	System.out.println(	custRepo.insertCustomer("Komal", "Talera Nagar", "Talera Nagar", "Pune", "868613310"));
//		String acc = "BOIN0022";
//		Customer customer = custRepo.getCustomer("BOIN0022");
//		System.out.println(customer);;
	}

	
	//FUNCTION TO RETRIEVE ALL ENTRIES IN CUSTOMER TABLE
	public ArrayList<Customer> getAllCustomers() {

		DBManager dbm = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		int flag = 1;

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from customer");
			
			// final String SQL_LAST_ENTRY = "SELECT account_number FROM customer WHERE
			// cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
			// rs = st.executeQuery(SQL_LAST_ENTRY);
			// System.out.println(SQL_LAST_ENTRY);
			while (rs.next()) {
				Customer customer = new Customer();
				int db_cust_seq_id = rs.getInt("cust_seq_id");
				customer.setCust_seq_id(db_cust_seq_id);
				
				int db_bank_id = rs.getInt("bank_id");
				customer.setBank_id(db_bank_id);
				
				String db_account_number = rs.getString("account_number");
				customer.setAccount_number(db_account_number);
				
				String db_cust_name = rs.getString("cust_name");
				customer.setCust_name(db_cust_name);
				
				String db_address1 = rs.getString("address1");
				customer.setAddress1(db_address1);
				
				String db_address2 = rs.getString("address2");
				customer.setAddress2(db_address2);
				
				String db_city = rs.getString("city");
				customer.setCity(db_city);
				
				String db_phone = rs.getString("phone");
				customer.setPhone(db_phone);
				
				customerList.add(customer);
				
			


			}
		} catch (CPException exp) {
			exp.printStackTrace();
		} catch (Exception exp1) {
			exp1.printStackTrace();
		} finally {
			dbm.closeConnection(con);
			try {
				rs.close();
				st.close();
			} catch (Exception exp) {

			}
		}
		return customerList ;

	}

	
	//FUNCTION TO CHECK IF CUST. EXIST BY PHONE NUMBER
	public boolean isCustExist(String phone) {

		DBManager dbm = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		int flag = 1;

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from customer");
			while (rs.next()) {

				String db_customer_phone = Long.toString(rs.getLong("phone"));

				//System.out.println(db_customer_phone);
				if ((db_customer_phone.equals(phone)) == true) {
					flag = 0;

				}

//				System.out.println(flag);

			}
		} catch (CPException exp) {
			exp.printStackTrace();
		} catch (Exception exp1) {
			exp1.printStackTrace();
		} finally {
			dbm.closeConnection(con);
			try {
				rs.close();
				st.close();
			} catch (Exception exp) {

			}
		}

		return (flag == 0) ? true : false;
	}
	
	
	//FUNCTION TO INSERT NEW CUSTOMER
	public String insertCustomer(String customer_name, String customer_address1, String customer_address2, String customer_city, String customer_phone) throws SQLException, IOException {
		DBManager dbm = null;
		Connection con = null;
		boolean action = false;
		ResultSet rs = null;
		PreparedStatement st = null;
		
		props = new Properties();
		FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
		props.load(fs);
	//	System.out.println(props.getProperty("bankId"));
		final String SQL_INSERT = "INSERT INTO customer (bank_id, account_number, cust_name,address1 , address2, city, phone) VALUES (?,?,?,?,?,?,?)";

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			Statement st1 = con.createStatement();
			st = con.prepareStatement(SQL_INSERT);

			st.setInt(1,Integer.parseInt(props.getProperty("bankId")));
			st.setString(2, "BOIN00");
			st.setString(3, customer_name);
			st.setString(4, customer_address1);
			st.setString(5, customer_address2);
			st.setString(6, customer_city);
			st.setLong(7, Long.parseLong(customer_phone));

			// action = st.executeUpdate();

			action = st.execute();
			System.out.println("customer inserted successfully");
//			System.out.println(action);
			
			//CODE TO INITIALIZE ACCOUNT NUMBER SAME AS CUST_SEQ_ID
			if (action == false) {
//				System.out.println("@@@@@@@@@@@@@@@@@@@@");
				final String SQL_LAST_ENTRY = "SELECT cust_seq_id FROM customer WHERE cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
				rs = st1.executeQuery(SQL_LAST_ENTRY);
//				System.out.println(rs);
				while (rs.next()) {
					long last_cust_seq_id = rs.getLong("cust_seq_id");

					String updated_account_number = "BOIN00" + Long.toString(last_cust_seq_id);
					//System.out.println(updated_account_number);
					final String SQL_UPDATE = "UPDATE customer SET account_number =" + "'" + updated_account_number
							+ "'" + "WHERE cust_seq_id = " + last_cust_seq_id;
					st = con.prepareStatement(SQL_UPDATE);
					st.executeUpdate();
				}
			}
				if (action == false) {
//					System.out.println("@@@@@@@@@@@@@@@@@@@@");
					final String SQL_LAST_ENTRY_ACC_NUM = "SELECT account_number FROM customer WHERE cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
					rs = st1.executeQuery(SQL_LAST_ENTRY_ACC_NUM);
					System.out.println(rs);
					while (rs.next()) {
					String last_acc_number = rs.getString("account_number");
					
					return last_acc_number;
					
					}

			}
			

		}

		catch (CPException exp) {
			exp.printStackTrace();

		}
		 catch(PSQLException psqlException)
		 {
		 System.out.println("Customer Creation failed ");
		
		 }
		catch (Exception exp1) {
			exp1.printStackTrace();
		} finally {
			dbm.closeConnection(con);

		}
		return null;

	}

	//FUNCTION TO FETCH CUSTOMER ENTRY BY ACCOUNT NUMBER
	public Customer getCustomer(String acc_number) throws NumberFormatException {
		DBManager dbm = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		Customer customer = new Customer();
		
		
		try {
			

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			st = con.createStatement();
			
//			long account_number = Long.valueOf(acc_number);
//			System.out.println( Long.parseLong(acc_number));
			rs = st.executeQuery("select * from customer where account_number ="+"'"+acc_number+"'");
			while (rs.next()) {
			
				int db_cust_seq_id = rs.getInt("cust_seq_id");
				customer.setCust_seq_id(db_cust_seq_id);
				
				int db_bank_id = rs.getInt("bank_id");
				customer.setBank_id(db_bank_id);
				
				String db_account_number = rs.getString("account_number");
				customer.setAccount_number(db_account_number);
				
				String db_cust_name  = rs.getString("cust_name");
				customer.setCust_name(db_cust_name);
				
				String db_address1 = rs.getString("address1");
				customer.setAddress1(db_address1);
				
				String db_address2 = rs.getString("address2");
				customer.setAddress2(db_address2);
				
				String db_city = rs.getString("city");
				customer.setCity(db_city);
				
				String db_phone = Long.toString(rs.getLong("phone"));
				customer.setPhone(db_phone);
				
				
			}
		} catch (CPException exp) {
			exp.printStackTrace();
		} 
		catch (Exception exp1) {
			exp1.printStackTrace();
		} 
		finally {
			dbm.closeConnection(con);
			try {
				rs.close();
				st.close();
			} catch (Exception exp) {

			}
		}

		

		return customer;

	}


	
}
