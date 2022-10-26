package com.cpa.serviceimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cpa.entity.Customer;
import com.cpa.repository.CustRepo;
import com.cpa.service.CustService;

public class CustServiceImpl implements CustService{

	CustRepo custRepo = new CustRepo();
	Map<String, Customer> cust_map = new HashMap<String, Customer>();
	public static void main(String[] args) throws SQLException, IOException {
		
		CustServiceImpl custServiceImpl = new CustServiceImpl();
		
//		System.out.println(custServiceImpl.checkCust("778613310"));;
	//	System.out.println(custServiceImpl.getCustomerByAccountNumber("BOIN0024"));
	//	System.out.println(custServiceImpl.getCust_seq_idByAccNum("BOIN0024"));
//		 System.out.println(custServiceImpl.cust_map);
//		 System.out.println(custServiceImpl.addCust("Rohan", "Talera Nagar", "Talera Nagar", "Pune", "889513310"));
//		 System.out.println(custServiceImpl.initializeHashmap());;
	}
	
	
	
	//METHOD TO INITIALIZE HASHMAP FOR CUSTOMER ENTITY BY CUSTOMER TABLE IN DB
	@Override
	public HashMap<String, Customer> initializeHashmap() {
		// TODO Auto-generated method stub
	//System.out.println(	custRepo.getAllCustomers());;
	
 
	ArrayList<Customer> customerList=custRepo.getAllCustomers();
	
	for(int i=0 ; i<customerList.size(); i++)
	{
		Customer customer = customerList.get(i);
//		 System.out.println(customer);
//		 System.out.println("End");
		 
		String al_acc_number = customer.getAccount_number();
//		System.out.println(al_acc_number);
		
		cust_map.put(al_acc_number, customer);
		
	}
	
		return (HashMap<String, Customer>) cust_map;
	}

	
	//METHOD TO CHECK CUSTOMER PRESENSE IN HASHMAP BY PHONE NUMBER
	@Override
	public boolean checkCust(String phone) {
		// TODO Auto-generated method stub
		int flag =1;
		//custRepo.isCustExist(phone);
		
		
		CustService custService = new CustServiceImpl();
		
		cust_map = custService.initializeHashmap();
		
		for (Customer cust : cust_map.values()) {
//			System.out.println("@@@@@@@@@@");
//			System.out.println(cust);
			
			String custmap_phone = cust.getPhone();
			
			if(custmap_phone.equalsIgnoreCase(phone))
			{
				flag = 0;
			}
			
		}
		
		return (flag == 0) ? true : false;
	}
	
	
	//METHOD TO CHECK CUSTOMER PRESENSE IN HASHMAP BY ACCOUNT NUMBER
		@Override
		public boolean checkCustByAccNum(String acc_number) {
			// TODO Auto-generated method stub
			int flag =1;
			//custRepo.isCustExist(phone);
			
			
			CustService custService = new CustServiceImpl();
			
			cust_map = custService.initializeHashmap();
			
			for (Customer cust : cust_map.values()) {
//				System.out.println("@@@@@@@@@@");
//				System.out.println(cust);
				
				String custmap_acc_num = cust.getAccount_number();
				
				if(custmap_acc_num.equalsIgnoreCase(acc_number))
				{
					flag = 0;
				}
				
			}
			
			return (flag == 0) ? true : false;
		}

	//METHOD TO ADD CUSTOMER INTO DATABASE
	@Override
	public String addCust(String customer_name, String customer_address1, String customer_address2, String customer_city, String customer_phone) throws SQLException, IOException {

		return custRepo.insertCustomer(customer_name,customer_address1,customer_address2,customer_city,customer_phone);
		
		
	}

	
	//METHOD TO GET CUSTOMER DETAILS BY ACCOUNT NUMBER
	@Override
	public Customer getCustomerByAccountNumber(String acc_no) {
		// TODO Auto-generated method stub
		int flag =1;
		//custRepo.isCustExist(phone);
		
		
		CustService custService = new CustServiceImpl();
		
		cust_map = custService.initializeHashmap();
		
		for (Customer cust : cust_map.values()) {
//			System.out.println("@@@@@@@@@@");
//			System.out.println(cust);
//			
			String custmap_acc_num = cust.getAccount_number();
			
			if(custmap_acc_num.equalsIgnoreCase(acc_no))
			{
//				System.out.println(custmap_acc_num);
				return cust;
			}
			
		}
		return null;
	}

	
	public int getCust_seq_idByAccNum(String acc_no)
	{
		// TODO Auto-generated method stub
		int flag =1;
		//custRepo.isCustExist(phone);
		
		
		CustService custService = new CustServiceImpl();
		
		cust_map = custService.initializeHashmap();
		
		for (Customer cust : cust_map.values()) {
//			System.out.println("@@@@@@@@@@");
//			System.out.println(cust);
			
			String custmap_acc_num = cust.getAccount_number();
			
			if(custmap_acc_num.equalsIgnoreCase(acc_no))
			{
				int custmap_cust_seq_id = cust.getCust_seq_id();
				
				return custmap_cust_seq_id;
			}
			
		}
		
		return 0;
	}
}
