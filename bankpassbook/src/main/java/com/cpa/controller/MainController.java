package com.cpa.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cpa.entity.Customer;
import com.cpa.entity.Transaction;
import com.cpa.exception.CPException;
import com.cpa.service.CustService;
import com.cpa.service.TransService;
import com.cpa.serviceimpl.CustServiceImpl;
import com.cpa.serviceimpl.TransServiceImpl;

public class MainController {
//comment

	private static boolean validateJavaDate(String strDate) {
		/* Check if date is 'null' */
		if (strDate.trim().equals("")) {
			return true;
		}
		/* Date is not 'null' */
		else {
			/*
			 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
//				System.out.println(strDate + " is valid date format");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				System.out.println(" Invalid Date format");

				return false;

			}
			/* Return true if date format is valid */
			return true;
		}
	}

	private static void customerOption() {
		try {
			CustService custService = new CustServiceImpl();

			System.out.println(
					"Please enter the details for Customer  to be inserted for following fields customer name, customer address1, customer address1, customer city,customer phone respectively");

			Pattern name_pattern = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
			Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
			Pattern num_pattern = Pattern.compile("[^0-9]");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter Customer Name");
			String cust_name = sc.next();
			Matcher matcher3 = name_pattern.matcher(cust_name);
			boolean isStringContainsSpecialCharacter3 = matcher3.find();
			if (isStringContainsSpecialCharacter3) {
				throw new CPException("004", "Invalid Customer Name ");
			}

			System.out.println("Enter Customer Phone");
			String cust_phone = sc.next();
			Matcher matcher5 = num_pattern.matcher(cust_phone);
			boolean isStringContainsSpecialCharacter5 = matcher5.find();
			if (isStringContainsSpecialCharacter5 || cust_phone.length() < 10 || cust_phone.length() > 10) {
				throw new CPException("004", "Invalid Customer Phone ");
			}

			if (custService.checkCust(cust_phone)) {
				System.out.println("Customer already exists");
				System.out.println("Do you want to add another Customer [Y]es or [N]o?");

				Scanner sc_y_n = new Scanner(System.in);
				String yn_option = sc_y_n.next();

				switch (yn_option.toUpperCase()) {
				case "Y":

					System.out.println(
							"Please enter the details for Customer  to be inserted for following fields customer name, customer address1, customer address1, customer city,customer phone respectively");

					Scanner sc2 = new Scanner(System.in);

					System.out.println("Enter Customer Name");
					String loop_cust_name = sc2.next();
					Matcher matcher6 = name_pattern.matcher(loop_cust_name);
					boolean isStringContainsSpecialCharacter6 = matcher6.find();
					if (isStringContainsSpecialCharacter6) {
						throw new CPException("004", "Invalid Customer Name ");
					}

					System.out.println("Enter Customer Phone");
					String loop_cust_phone = sc.next();
					Matcher matcher8 = num_pattern.matcher(loop_cust_phone);
					boolean isStringContainsSpecialCharacter8 = matcher8.find();
					if (isStringContainsSpecialCharacter8 || cust_phone.length() < 10 || cust_phone.length() > 10) {
						throw new CPException("004", "Invalid Customer Phone ");
					}

					CustService loopcustService = new CustServiceImpl();

					try {

						if (loopcustService.checkCust(loop_cust_phone)) {
							System.out.println("Customer already exists");
							break;
						} else {

							System.out.println("Enter Customer Address1");
							String loop_cust_address1 = sc.next();

							System.out.println("Enter Customer Address2");
							String loop_cust_address2 = sc.next();

							System.out.println("Enter Customer City");
							String loop_cust_city = sc.next();
							Matcher matcher7 = name_pattern.matcher(loop_cust_city);
							boolean isStringContainsSpecialCharacter7 = matcher7.find();
							if (isStringContainsSpecialCharacter7) {
								throw new CPException("004", "Invalid Customer City ");
							}

							String loop_acc_num = loopcustService.addCust(loop_cust_name, loop_cust_address1,
									loop_cust_address2, loop_cust_city, loop_cust_phone);
							System.out.println("Account number for " + loop_cust_name + " is " + loop_acc_num);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;

				case "N":

					break;

				default:
					System.out.println("Invalid input");

					break;
				}

			}

			else {
				System.out.println("Enter Customer Address1");
				String cust_address1 = sc.next();

				System.out.println("Enter Customer Address2");
				String cust_address2 = sc.next();

				System.out.println("Enter Customer City");
				String cust_city = sc.next();
				Matcher matcher4 = name_pattern.matcher(cust_city);
				boolean isStringContainsSpecialCharacter4 = matcher4.find();
				if (isStringContainsSpecialCharacter4) {
					throw new CPException("004", "Invalid Customer City ");
				}

				try {
					String acc_num = custService.addCust(cust_name, cust_address1, cust_address2, cust_city,
							cust_phone);
					System.out.println("Account number for " + cust_name + " is " + acc_num);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (InputMismatchException ime) {

			System.out.println("Please enter a valid input");
			// System.exit(0);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			System.out.println("Customer creation failed");
		}

	}

	private static void transactionLoop(String acc_num) throws CPException, SQLException, IOException {
		TransService transService = new TransServiceImpl();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter transaction date");
		String tran_date = sc.next();

		if (!validateJavaDate(tran_date)) {
			throw new CPException("008", "");
		}

		System.out.println("Enter transaction details");
		String tran_details = sc.next();
//		

		System.out.println("Do you want to enter credit or debit amount .Press C  for credit and D for debit amount ");

		String c_d_option = sc.next();
		switch (c_d_option.toUpperCase()) {
		case "C":
			try {
				System.out.println("Please enter credit amount");
				Double credit_amt = sc.nextDouble();

				Double debit_amt = 0.0d;
				String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
						acc_num);
				// transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
				// acc_num);
				if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
					System.out.println(
							"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
					String op = sc.next();
					if (op.equalsIgnoreCase("y")) {
						transactionLoop(acc_num);
					} else if (op.equalsIgnoreCase("n")) {

						break;
					} else {
						throw new CPException("010", "Invalid input ");
					}
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid credit amount");
			}
			break;
		case "D":
			try {
				System.out.println("Please enter debit amount");
				Double debit_amt = sc.nextDouble();

				Double credit_amt = 0.0d;
				String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
						acc_num);
				if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
					System.out.println(
							"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
					String op = sc.next();
					if (op.equalsIgnoreCase("y")) {
						transactionLoop(acc_num);
					} else if (op.equalsIgnoreCase("n")) {

						break;
					} else {
						throw new CPException("010", "Invalid input ");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid debit amount");
			}
			break;
		default:
			System.out.println("Invalid input");
		}
	}

	private static void transactionOption() throws CPException, SQLException, IOException {
		try {
			Transaction transaction = new Transaction();
			CustService custService = new CustServiceImpl();
			TransService transService = new TransServiceImpl();
			System.out.println(
					"Please enter the customer account number, transaction date and tranasaction details, credit amount and debit amount respectively");

			Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
//		Pattern date_pattern = Pattern.compile("[^a-z0-9/- ]", Pattern.CASE_INSENSITIVE);
			Pattern num_pattern = Pattern.compile("[0-9]");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter Customer Account Number");
			String acc_num = sc.next();
			Matcher matcher3 = pattern.matcher(acc_num);
			boolean isStringContainsSpecialCharacter3 = matcher3.find();
			if (isStringContainsSpecialCharacter3) {
				throw new CPException("001", "Invalid Customer account Number. ");
			}

			if (custService.checkCustByAccNum(acc_num) == false) {

				System.out.println(
						"Customer account Number does not exist.Do you want to re-enter account number [Y]es or [N]o");
				String y_n_option = sc.next();
				switch (y_n_option.toUpperCase()) {
				case "Y":
					System.out.println("Enter Customer Account Number");
					String acc_num1 = sc.next();
					if (isStringContainsSpecialCharacter3) {
						throw new CPException("001", "Invalid Customer account Number");
//					System.out.println( "Invalid Customer account Number. Do you want to re-enter account number [Y]es or [N]o");
					}
					System.out.println("Enter transaction date");
					String tran_date = sc.next();

					if (!validateJavaDate(tran_date)) {
						throw new CPException("008", "");
					}

					System.out.println("Enter transaction details");
					String tran_details = sc.next();
//					

					System.out.println(
							"Do you want to enter credit or debit amount .Press C  for credit and D for debit amount ");

					String c_d_option = sc.next();
					switch (c_d_option.toUpperCase()) {
					case "C":
						try {
							System.out.println("Please enter credit amount");
							Double credit_amt = sc.nextDouble();

							Double debit_amt = 0.0d;
							String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt,
									debit_amt, acc_num1);
							// transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
							// acc_num);

							if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
								System.out.println(
										"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
								String op = sc.next();
								if (op.equalsIgnoreCase("y")) {
									transactionLoop(acc_num1);
								} else if (op.equalsIgnoreCase("n")) {
									break;
								} else {
									throw new CPException("010", "Invalid input ");
								}
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid credit amount");
						}
						break;
					case "D":
						try {
							System.out.println("Please enter debit amount");
							Double debit_amt = sc.nextDouble();

							Double credit_amt = 0.0d;
							String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt,
									debit_amt, acc_num1);
							if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
								System.out.println(
										"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
								String op = sc.next();
								if (op.equalsIgnoreCase("y")) {
									transactionLoop(acc_num1);
								} else if (op.equalsIgnoreCase("n")) {
									break;
								} else {
									throw new CPException("010", "Invalid input ");
								}
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid debit amount");
						}
						break;
					default:
						System.out.println("Invalid input");

					}

					break;
				case "N":
					break;
				default:
					System.out.println();
				}

			}

			System.out.println("Enter transaction date");
			String tran_date = sc.next();
			if (!validateJavaDate(tran_date)) {
				throw new CPException("008", "");
			}

			System.out.println("Enter transaction details");
			String tran_details = sc.next();
//		Matcher matcher5 = pattern.matcher(tran_details);
//		boolean isStringContainsSpecialCharacter5 = matcher5.find();
//		if (isStringContainsSpecialCharacter5) {
//			throw new CPException("004", "Invalid transaction details");
//		}

			System.out.println(
					"Do you want to enter credit or debit amount .Press C  for credit and D for debit amount ");

			String c_d_option = sc.next();
			switch (c_d_option.toUpperCase()) {
			case "C":
				try {
					System.out.println("Please enter credit amount");
					Double credit_amt = sc.nextDouble();

					Double debit_amt = 0.0d;
					String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
							acc_num);
					if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
						System.out.println(
								"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
						String op = sc.next();
						if (op.equalsIgnoreCase("y")) {
							transactionLoop(acc_num);
						} else if (op.equalsIgnoreCase("n")) {

							break;
						} else {
							throw new CPException("010", "Invalid input ");
						}
					}

				} catch (InputMismatchException e) {
					System.out.println("Invalid credit amount");
				}
				break;
			case "D":
				try {
					System.out.println("Please enter debit amount");
					Double debit_amt = sc.nextDouble();

					Double credit_amt = 0.0d;
					String succes_message = transService.addTranDetails(tran_date, tran_details, credit_amt, debit_amt,
							acc_num);
					if (succes_message.equalsIgnoreCase("Transaction inserted successfully")) {
						System.out.println(
								"Do you want to enter more transaction details for same account number .Press [Y]es or [N]o");
						String op = sc.next();
						if (op.equalsIgnoreCase("y")) {
							transactionLoop(acc_num);
						} else if (op.equalsIgnoreCase("n")) {

							break;
						} else {
							throw new CPException("010", "Invalid input ");
						}
					}
				} catch (InputMismatchException e) {
					System.out.println("Invalid debit amount");
				}
				break;
			default:
				System.out.println("Invalid input");

			}
		} catch (CPException e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println();
		}

	}

	private static void passbookOption() throws CPException, IOException {
		try {
			TransService transService = new TransServiceImpl();
			CustService custService = new CustServiceImpl();
			Customer customer = new Customer();
			Customer customer1 = new Customer();
			Transaction transaction = new Transaction();
			System.out.println("Please enter the customer account number respectively");

			Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
//		Pattern date_pattern = Pattern.compile("[^a-z0-9/- ]", Pattern.CASE_INSENSITIVE);
			Pattern num_pattern = Pattern.compile("[0-9]");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter Customer Account Number");
			String acc_num = sc.next();
			Matcher matcher3 = pattern.matcher(acc_num);
			boolean isStringContainsSpecialCharacter3 = matcher3.find();
			if (isStringContainsSpecialCharacter3) {
				throw new CPException("001", "Invalid Customer account Number. ");
			}

			if (custService.checkCustByAccNum(acc_num) == false) {

				System.out.println(
						"Customer account Number does not exist.Do you want to re-enter account number [Y]es or [N]o");
				String y_n_option = sc.next();
				switch (y_n_option.toUpperCase()) {
				case "Y":
					System.out.println("Enter Customer Account Number");
					String second_acc_num = sc.next();
					Matcher matcher8 = pattern.matcher(acc_num);
					boolean isStringContainsSpecialCharacter8 = matcher8.find();
					if (isStringContainsSpecialCharacter8) {
						throw new CPException("001", "Invalid Customer account Number. ");
					}
					if (custService.checkCustByAccNum(second_acc_num) == false) {

						throw new CPException("003", "Account Number does not exist");

					}
					customer1 = custService.getCustomerByAccountNumber(second_acc_num);
					ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
					arrayList = transService.getTransactionByAccNumOfCurrentMonth(second_acc_num);
					int sr_no = 0;

					int length = arrayList.size();
					Properties props = new Properties();
					FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
					props.load(fs);
					System.out.println(
							"						" + props.getProperty("bankName") + "						");
					System.out.println(
							"						" + props.getProperty("bankBranch") + "						");
					System.out.println(
							"						" + props.getProperty("bankCity") + "						");
					System.out.println("Customer Name		" + customer1.getCust_name());
					System.out.println("Account Number		" + customer1.getAccount_number());
					System.out.println("Address1 		" + customer1.getAddress1());
					System.out.println("Address2		" + customer1.getAddress2());
					System.out.println("City			" + customer1.getCity());
					System.out.println("Phone			" + customer1.getPhone());
					System.out.println("Current Month Transaction");
					System.out.println("Sr.No	" + "Tran Date	" + "Tran details			" + "Credit		"
							+ "Debit		" + "Current Balance");

					for (int i = 0; i < arrayList.size(); i++) {
						Transaction transaction2 = arrayList.get(i);
						System.out.println(sr_no++ + "	" + transaction2.getTran_date() + "		"
								+ transaction2.getTran_details() + "			" + transaction2.getCredit_amt()
								+ "		" + transaction2.getDebit_amt() + "		"
								+ transService.getCurrentBalance(transaction2.getTran_id()));
					}
					break;
				case "N":
					break;
				default:
					System.out.println("Invalid input");
					break;

				}
			} else {
				customer = custService.getCustomerByAccountNumber(acc_num);
				ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
				arrayList = transService.getTransactionByAccNumOfCurrentMonth(acc_num);
				int sr_no = 0;

				int length = arrayList.size();
				Properties props = new Properties();
				FileInputStream fs = new FileInputStream("src/main/resources/bank_entity.properties");
				props.load(fs);
				System.out
						.println("						" + props.getProperty("bankName") + "						");
				System.out
						.println("						" + props.getProperty("bankBranch") + "						");
				System.out
						.println("						" + props.getProperty("bankCity") + "						");
				System.out.println("Customer Name		" + customer.getCust_name());
				System.out.println("Account Number		" + customer.getAccount_number());
				System.out.println("Address1 		" + customer.getAddress1());
				System.out.println("Address2		" + customer.getAddress2());
				System.out.println("City			" + customer.getCity());
				System.out.println("Phone			" + customer.getPhone());
				System.out.println("Current Month Transaction");
				System.out.println("Sr.No	" + "Tran Date	" + "Tran details			" + "Credit		"
						+ "Debit		" + "Current Balance");

				for (int i = 0; i < arrayList.size(); i++) {
					Transaction transaction2 = arrayList.get(i);
					System.out.println(sr_no++ + "	" + transaction2.getTran_date() + "		"
							+ transaction2.getTran_details() + "			" + transaction2.getCredit_amt()
							+ "		" + transaction2.getDebit_amt() + "		"
							+ transService.getCurrentBalance(transaction2.getTran_id()));
				}
			}
		} catch (NullPointerException e) {
			System.out.println(" ");
		} catch (CPException e) {
			System.out.println(" ");
		}

	}

	public static void main(String[] args) throws Exception {

		while (true) {
			System.out.println("============= Main Menu ============");
			System.out.println("1. Create Company Details");
			System.out.println("2. Create Transaction Details");
			System.out.println("3. Print Bank Passbook");
			System.out.println("4. Exit");
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				try {
					customerOption();
				} catch (InputMismatchException ime) {
					System.out.println("invalid input");
				}
				break;
			case 2:
				try {
					transactionOption();
				} catch (InputMismatchException ime) {
					System.out.println("Invalid input");
				} catch (CPException e) {
					System.out.println("transaction creation failed");
				}
				break;

			case 3:
				passbookOption();
				break;
			case 4:
				System.exit(0);
				break;

			default:
				System.out.println("Please enter valid menu option from 1-4");
				break;

			}
		}

	}

}
