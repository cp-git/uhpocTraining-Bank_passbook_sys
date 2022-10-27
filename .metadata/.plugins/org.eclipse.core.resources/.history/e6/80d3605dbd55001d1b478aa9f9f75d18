package com.cpa.connectionpooling;






import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cpa.exception.CPException;

/**
 * This class is for DB Connection pool purpose. It is implemented using
 * singleton design pattern
 *
 */
public class DBManager {

	private static DBManager dbManager = null;
	private List<Connection> conManager = null;
	private Properties props = null;
	private MessageBundle mb = null;

	/**
	 * Private constructor
	 */
	private DBManager() throws CPException {
		mb = MessageBundle.getBundle();
		loadConnProperty();
		initialization();

	}

	/**
	 * initialize the db pool
	 */
	private void initialization() throws CPException {
		int maxCon = Integer.parseInt(props.getProperty("maxcon"));
		conManager = new ArrayList<Connection>();
		for (int i = 0; i < maxCon; i++) {
			conManager.add(createConnection());
		}
	}

	private Connection createConnection() throws CPException {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(props.getProperty("dburl") + props.getProperty("dbname"),
					props.getProperty("dbusername"), props.getProperty("dbpassword"));
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new CPException("003", mb.getMessage("003") + exp.getMessage());
		}
		return con;
	}

	/**
	 * Method to reads DB properties from the resources/dbcon.properties file
	 */
	private void loadConnProperty() throws CPException {
		FileInputStream fs;
		try {
			props = new Properties();
			fs = new FileInputStream("src/main/resources/dbcon.properties");
			props.load(fs);
			/*
			 * System.out.println(props.getProperty("dburl"));
			 * System.out.println(props.getProperty("dbname"));
			 * System.out.println(props.getProperty("dbusername"));
			 * System.out.println(props.getProperty("dbpassword"));
			 * System.out.println(props.getProperty("maxcon"));
			 */
		} catch (Exception e) {
			e.printStackTrace();
			throw new CPException("002", mb.getMessage("002"));
		}
	}

	/**
	 * To get the DB Manager instance
	 * 
	 * @return DBManager
	 * @throws CPException
	 */
	public static DBManager getDBManager() throws CPException {
		if (null == dbManager) {
			dbManager = new DBManager();
		}
		return dbManager;
	}

	public Connection getConnection() throws CPException {
		Connection con = null;
		if (conManager.size() > 0) {
			con = conManager.get(0);
			conManager.remove(0);
			try {
				if (con.isClosed()) {
					con = createConnection();
				}
			} catch (Exception exp) {
				exp.printStackTrace();
				throw new CPException("003", mb.getMessage("003") + exp.getMessage());
			}
		} else {
			throw new CPException("001", mb.getMessage("001"));
		}
		printConSize();
		return con;
	}

	public void closeConnection(Connection con) {
		conManager.add(con);
		printConSize();
	}

	public void cleanPool() {
		int maxCon = conManager.size();
		for (int i = 0; i < maxCon; i++) {
			Connection con = conManager.get(0);
			conManager.remove(0);
//			System.out.println(conManager.size());
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printConSize();
	}

	private void printConSize() {
//		System.out.println(" Available Connection ... " + conManager.size());
	}

}

