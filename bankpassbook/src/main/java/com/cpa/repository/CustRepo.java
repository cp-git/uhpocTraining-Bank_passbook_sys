package com.cpa.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.util.PSQLException;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Customer;
import com.cpa.exception.CPException;

public class CustRepo {

	
	public static void main(String[] args) throws SQLException {
		CustRepo custRepo = new CustRepo();
		// custRepo.getAllCustomers();
		// System.out.println(custRepo.isCustExist("9865326589"));;
		//custRepo.insertCustomer("Kshitija", "Talera Nagar", "Talera Nagar", "Pune", "788613310");
//		String acc = "BOIN0022";
		Customer customer = custRepo.getCustomer("BOIN0022");
		System.out.println(customer);;
	}

	
	//FUNCTION TO RETRIEVE ALL ENTRIES IN CUSTOMER TABLE
	public void getAllCustomers() {

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
			// final String SQL_LAST_ENTRY = "SELECT account_number FROM customer WHERE
			// cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
			// rs = st.executeQuery(SQL_LAST_ENTRY);
			// System.out.println(SQL_LAST_ENTRY);
			while (rs.next()) {

				rs.getInt(1);
				rs.getInt(2);
				rs.getString(1);
				rs.getString(4);

				System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4)
						+ " " + rs.getLong("phone"));
				System.out.println(rs.getString(1));
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

				System.out.println(db_customer_phone);
				if ((db_customer_phone.equals(phone)) == true) {
					flag = 0;

				}

				System.out.println(flag);

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
	public void insertCustomer(String customer_name, String customer_address1, String customer_address2,
			String customer_city, String customer_phone) throws SQLException {
		DBManager dbm = null;
		Connection con = null;
		boolean action = false;
		ResultSet rs = null;
		PreparedStatement st = null;

		final String SQL_INSERT = "INSERT INTO customer (bank_id, account_number, cust_name,address1 , address2, city, phone) VALUES (?,?,?,?,?,?,?)";

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			Statement st1 = con.createStatement();
			st = con.prepareStatement(SQL_INSERT);

			st.setInt(1, 1);
			st.setString(2, "BOIN00");
			st.setString(3, customer_name);
			st.setString(4, customer_address1);
			st.setString(5, customer_address2);
			st.setString(6, customer_city);
			st.setLong(7, Long.parseLong(customer_phone));

			// action = st.executeUpdate();

			action = st.execute();
			System.out.println("company inserted successfully");
			System.out.println(action);
			
			//CODE TO INITIALIZE ACCOUNT NUMBER SAME AS CUST_SEQ_ID
			if (action == false) {
//				System.out.println("@@@@@@@@@@@@@@@@@@@@");
				final String SQL_LAST_ENTRY = "SELECT cust_seq_id FROM customer WHERE cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
				rs = st1.executeQuery(SQL_LAST_ENTRY);
				System.out.println(rs);
				while (rs.next()) {
					long last_cust_seq_id = rs.getLong("cust_seq_id");

					String updated_account_number = "BOIN00" + Long.toString(last_cust_seq_id);
					System.out.println(updated_account_number);
					final String SQL_UPDATE = "UPDATE customer SET account_number =" + "'" + updated_account_number
							+ "'" + "WHERE cust_seq_id = " + last_cust_seq_id;
					st = con.prepareStatement(SQL_UPDATE);
					st.executeUpdate();
				}

			}
			

		}

		catch (CPException exp) {
			exp.printStackTrace();

		}
		 catch(PSQLException psqlException)
		 {
		 System.out.println("Company Creation failed ");
		
		 }
		catch (Exception exp1) {
			exp1.printStackTrace();
		} finally {
			dbm.closeConnection(con);

		}

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