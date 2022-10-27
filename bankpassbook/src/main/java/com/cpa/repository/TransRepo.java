package com.cpa.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import com.cpa.connectionpooling.DBManager;
import com.cpa.entity.Transaction;
import com.cpa.exception.CPException;

public class TransRepo {

	public static void main(String[] args) throws SQLException, IOException {
		TransRepo transRepo = new TransRepo();

//	System.out.println(transRepo.getAllTransactions());
		System.out.println(transRepo.insertTransaction(24, "10/10/2022", "Salary November", 50000.0, 0.0d));

	}

	// FUNCTION TO RETRIEVE ALL ENTRIES IN TRANSACTION TABLE
	public ArrayList<Transaction> getAllTransactions() {

		DBManager dbm = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Transaction> tranList = new ArrayList<Transaction>();

		int flag = 1;

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from transaction");

			// final String SQL_LAST_ENTRY = "SELECT account_number FROM customer WHERE
			// cust_seq_id=(SELECT MAX(cust_seq_id) FROM customer)";
			// rs = st.executeQuery(SQL_LAST_ENTRY);
			// System.out.println(SQL_LAST_ENTRY);
			while (rs.next()) {
				Transaction transaction = new Transaction();

				int db_tran_id = rs.getInt("tran_id");
				transaction.setTran_id(db_tran_id);

				int db_cust_seq_id = rs.getInt("cust_seq_id");
				transaction.setCust_seq_id(db_cust_seq_id);

				Date db_tran_date = rs.getDate("tran_date");
				transaction.setTran_date(db_tran_date);

				String db_tran_details = rs.getString("tran_details");
				transaction.setTran_details(db_tran_details);

				Double db_credit_amt = rs.getDouble("credit_amt");
				transaction.setCredit_amt(db_credit_amt);

				Double db_debit_amt = rs.getDouble("debit_amt");
				transaction.setDebit_amt(db_debit_amt);
				tranList.add(transaction);

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
		return tranList;

	}

	// FUNCTION TO INSERT NEW TRANSACTION
	public String insertTransaction(int cust_seq_id, String tran_date, String tran_details, Double credit_amt,
			Double debit_amt) throws SQLException, IOException {
		DBManager dbm = null;
		Connection con = null;

		ResultSet rs = null;
		PreparedStatement st = null;

		final String SQL_INSERT = "INSERT INTO transaction (cust_seq_id,tran_date,tran_details,credit_amt,debit_amt) VALUES (?,?,?,?,?)";

		try {

			dbm = DBManager.getDBManager();
			con = dbm.getConnection();
			Statement st1 = con.createStatement();
			st = con.prepareStatement(SQL_INSERT);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date converted_tran_date = formatter.parse(tran_date);

			java.sql.Date sqlDate = new java.sql.Date(converted_tran_date.getTime());

			st.setInt(1, cust_seq_id);
			st.setDate(2, sqlDate);
			st.setString(3, tran_details);
			st.setDouble(4, credit_amt);
			st.setDouble(5, debit_amt);

			st.executeUpdate();

			System.out.println("Transaction inserted successfully");
			return "Transaction inserted successfully";

		}

		catch (CPException exp) {
			System.out.println("Transaction creation failed");

		} catch (PSQLException psqlException) {
			System.out.println("Transaction Creation failed ");

		} catch (Exception exp1) {
			exp1.printStackTrace();
		} finally {
			dbm.closeConnection(con);

		}
		return null;

	}

}
