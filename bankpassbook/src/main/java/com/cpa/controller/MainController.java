package com.cpa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cpa.exception.CPException;
import com.cpa.service.CustService;
import com.cpa.serviceimpl.CustServiceImpl;

public class MainController {
//comment
	
	public static void customerOption() {
		try {
			CustService custService = new CustServiceImpl();

			System.out.println(
					"Please enter the details for Customer  to be inserted for following fields customer name, customer address1, customer address1, customer city,customer phone respectively");

			Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
			Pattern num_pattern = Pattern.compile("[0-9]");

			Scanner sc = new Scanner(System.in);

//			System.out.println("Enter Pan Number");
//			String emp_panno = sc.next();
//			Matcher matcher1 = pattern.matcher(emp_panno);
//			boolean isStringContainsSpecialCharacter1 = matcher1.find();
//
//			if (isStringContainsSpecialCharacter1) {
//				throw new CPException("001", "Invalid Pan Card number ");
//			}
//
//			System.out.println("Enter PF Number");
//			String emp_pfno = sc.next();
//			Matcher matcher2 = pattern.matcher(emp_pfno);
//			boolean isStringContainsSpecialCharacter2 = matcher2.find();
//			if (isStringContainsSpecialCharacter2) {
//				throw new CPException("002", "Invalid PF number ");
//			}
//
//			System.out.println("Enter Company Id ");
//			int company_id = sc.nextInt();

			System.out.println("Enter Customer Name");
			String cust_name = sc.next();
			Matcher matcher3 = pattern.matcher(cust_name);
			boolean isStringContainsSpecialCharacter3 = matcher3.find();
			if (isStringContainsSpecialCharacter3) {
				throw new CPException("004", "Invalid Customer Name ");
			}
			
			System.out.println("Enter Customer Phone");
			String cust_phone = sc.next();
			Matcher matcher5 = pattern.matcher(cust_phone);
			boolean isStringContainsSpecialCharacter5 = matcher5.find();
			if (isStringContainsSpecialCharacter5 || cust_phone.length() < 10 || cust_phone.length() > 10  ) {
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
					Matcher matcher6 = pattern.matcher(loop_cust_name);
					boolean isStringContainsSpecialCharacter6 = matcher6.find();
					if (isStringContainsSpecialCharacter6) {
						throw new CPException("004", "Invalid Customer Name ");
					}
					
					System.out.println("Enter Customer Phone");
					String loop_cust_phone = sc.next();
					Matcher matcher8 = pattern.matcher(loop_cust_phone);
					boolean isStringContainsSpecialCharacter8 = matcher8.find();
					if (isStringContainsSpecialCharacter8 || cust_phone.length() < 10 || cust_phone.length() > 10  ) {
						throw new CPException("004", "Invalid Customer Phone ");
					}
					

					CustService loopcustService =new CustServiceImpl();

					try {
						
						if(loopcustService.checkCust(loop_cust_phone)) {
							System.out.println("Customer already exists");
							break;
						}
						else {
							

							
							System.out.println("Enter Customer Address1");
							String loop_cust_address1 = sc.next();
							
							System.out.println("Enter Customer Address2");
							String loop_cust_address2 = sc.next();
							
							
							System.out.println("Enter Customer City");
							String loop_cust_city = sc.next();
							Matcher matcher7 = pattern.matcher(loop_cust_city);
							boolean isStringContainsSpecialCharacter7 = matcher7.find();
							if (isStringContainsSpecialCharacter7) {
								throw new CPException("004", "Invalid Customer City ");
							}
							
							
						String loop_acc_num = loopcustService.addCust( loop_cust_name, loop_cust_address1,loop_cust_address2,loop_cust_city, loop_cust_phone);
						System.out.println("Account number for "+loop_cust_name+ " is "+loop_acc_num);
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
				Matcher matcher4 = pattern.matcher(cust_city);
				boolean isStringContainsSpecialCharacter4 = matcher4.find();
				if (isStringContainsSpecialCharacter4) {
					throw new CPException("004", "Invalid Customer City ");
				}
				
				
				try {
					String acc_num = custService.addCust( cust_name,cust_address1, cust_address2,cust_city, cust_phone);
					System.out.println("Account number for "+cust_name+ " is "+ acc_num);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}

		
			}
   catch (InputMismatchException ime) {

			System.out.println("Please enter a valid input");
			// System.exit(0);
		} 
		catch (CPException e) {
			// TODO Auto-generated catch block
			System.out.println("Customer creation failed");
		}

	}
	
	
	public static void main(String[] args) throws Exception {

		while (true) {
			System.out.println("============= Main Menu ============");
			System.out.println("1. Create Company Details");
			System.out.println("2. Create Employee Details");
			System.out.println("3. Print Pay slip");
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
					
				} catch (InputMismatchException ime) {
					System.out.println("invalid input");
				}
				break;

			case 3:
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
