package com.project.ui;
import java.util.Scanner;

import com.project.dao.LoggedINUser;
import com.project.dto.Buyers;
import com.project.dto.BuyersImpl;
//import com.masaischool.dao.LoggedINUser;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

import java.sql.SQLException;
import java.util.Scanner;

//import com.masaischool.ui.CategeryUI;
//import com.masaischool.ui.OrderUI;
//import com.masaischool.ui.ProductUI;
//import com.masaischool.ui.UserUI;
//import com.project.dao.BuyersOperation;
//import com.project.dao.BuyersOperationImpl;
//import com.project.dao.SellerOpration;
//import com.project.dao.SellerOprationImpl;
//import com.project.dto.Buyers;
//import com.project.dto.BuyersImpl;
//import com.project.dto.Seller;
//import com.project.dto.SellerImpl;

public class Main {
	private static BuyerUI buyerUI;
	private static SellerUI sellerUI;
	private static AdminUI adminUI;
	
	
	
	
	static void displayAdminMenu() {
		System.out.println("1. View all Buyers");
		System.out.println("2. View all Sellers");
		System.out.println("3. View Daily Dispute Report");
		System.out.println("4. View Daily Selling Report");
		System.out.println("5. Solve The Dispute Report");
		System.out.println("0. Exit");
	}
	
	
	static void adminMenu(Scanner sc) throws SomeThingWrongException, NoRecordFoundException {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Bye Bye admin");
					break;
				case 1:
					buyerUI.viewAllBuyers();
					break;
				case 2:
					sellerUI.viewAllSellers();
					break;
//				case 3:
//					categoryUI.updateCategory();
//					break;
//				case 4:
//					categoryUI.deleteCategory();
//					break;
//				case 5:
//					categoryUI.searchCategoriesByName();
//					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
	}
	
	static void adminLogin(Scanner sc) throws SomeThingWrongException, NoRecordFoundException {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username and Password");
		}
	}

	public static void main(String[] args) throws SomeThingWrongException, NoRecordFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		buyerUI = new BuyerUI(sc);
		sellerUI = new SellerUI(sc);
		adminUI = new AdminUI(sc);
		
		
		int choice = 0;
		do {
			System.out.println("1. Admin \n2. Buyer \n3. Seller \n0. Exit");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Thank you, Visit again");
					break;
				case 1:
					adminLogin(sc);
					break;
				case 2:
					buyerLogin(sc);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
		sc.close();
	}


	private static void buyerLogin(Scanner sc) throws SQLException, SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		
		int choice=0;
		do {
			System.out.print("Enter selection\n1. SignUp\n2. LogIn");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
				buyerSignUp(sc);
					break;
				case 2:
					buyerLogIn(sc);
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(LoggedINUser.loggedInUserId != 0);
	}

	
	private static void buyerLogIn(Scanner sc) throws SQLException, SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		//Buyers buyers=new BuyersImpl();
		if(!BuyerUI.login(sc))
			return;
		
		System.out.println("welcome buyer");
		
		int choice = 0;
		do {
			displayBuyerMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					ItemUI.viewAllItemsByCategory();
					break;
				
					//no break statement here i.e. after deletion of user account, logout will also take place
				case 0:
					BuyerUI.logout();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(LoggedINUser.loggedInUserId != 0);
		
	}


	private static void displayBuyerMenu() {
		// TODO Auto-generated method stub
		
	}


	private static void buyerSignUp(Scanner sc) throws SQLException, SomeThingWrongException {
		// TODO Auto-generated method stub
		BuyerUI.addBuyer(sc);
	}
		
	}
	
	
	
	

	/*public static void main(String[] args) throws SQLException {
		Scanner s=new Scanner(System.in);
		System.out.println("enter choice");
		int choice=s.nextInt();
		if(choice==1) {
			
		
		BuyersOperation b=new BuyersOperationImpl();
		
		Buyers bys=new BuyersImpl("Akku", "akku@gmail.com", "12", "12345");
		b.addBuyer(bys);
		System.out.println("Buyer addedd successfully");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
		
		else {
			SellerOpration sp=new SellerOprationImpl();
			Seller sel=new SellerImpl("anu", "anu@gmail.com", "67", "8909");
			sp.register(sel);
			System.out.println("seller addedd successfully");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}
	}*/

