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

public class Main {
	private static BuyerUI buyerUI;
	private static SellerUI sellerUI;
	private static AdminUI adminUI;
	private static ItemUI itemUI;
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
			switch (choice) {
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
		} while (choice != 0);
	}

	static void adminLogin(Scanner sc) throws SomeThingWrongException, NoRecordFoundException {
		System.out.print("Enter username ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();

		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			adminMenu(sc);
		} else {
			System.out.println("Invalid Username and Password");
		}
	}

	public static void main(String[] args) throws SomeThingWrongException, NoRecordFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		buyerUI = new BuyerUI(sc);
		sellerUI = new SellerUI(sc);
		adminUI = new AdminUI(sc);
		itemUI=new ItemUI(sc);
		int choice = 0;
		do {
			System.out.println("1. Admin \n2. Buyer \n3. Seller \n0. Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Thank you, Visit again");
				break;
			case 1:
				adminLogin(sc);
				break;
			case 2:
				buyerLogin(sc);
				break;
			case 3:
				sellerLogin(sc);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
		sc.close();
	}

	private static void sellerLogin(Scanner sc) throws NoRecordFoundException, SomeThingWrongException, SQLException {
		// TODO Auto-generated method stub
		int choice = 0;
		do {
			System.out.print("Enter selection\n1. SignUp\n2. LogIn");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				sellerSignUp(sc);
				break;
			case 2:
				sellerLogIn(sc);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (LoggedINUser.loggedInUserId != 0);
	}

	private static void sellerLogIn(Scanner sc) throws NoRecordFoundException, SomeThingWrongException, SQLException {
		// TODO Auto-generated method stub
		if (!SellerUI.login(sc))
			return;
		System.out.println("welcome Seller");

		int choice = 0;
		do {
			displaySellerMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
//			
			switch (choice) {
				case 1:
					itemUI.addItem();
					break;
//
				case 2:
					 itemUI.updateItem();
					break;
//
				case 3:
					itemUI.removeItem();
				break;
//				
				case 4:
					itemUI.createListOfItem();
				break;
//
	//			case 5:
	//				itemUI.viewSoldItemHistory();
	//				break;

			case 0:
				SellerUI.logout();
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
			
		} while (LoggedINUser.loggedInUserId != 0);

	}

	private static void displaySellerMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. Add Item.");
		System.out.println("2. Update Item.");
		System.out.println("3. Remove Item.");
		System.out.println("4. Create List Of Items.");
		System.out.println("5. View the sold Item history.");
		System.out.println("0. Exit.");
	}

	private static void sellerSignUp(Scanner sc) {
		// TODO Auto-generated method stub
		SellerUI.addSeller(sc);
	}

	private static void buyerLogin(Scanner sc) throws SQLException, SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub

		int choice = 0;
		do {
			System.out.print("Enter selection\n1. SignUp\n2. LogIn");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				buyerSignUp(sc);
				break;
			case 2:
				buyerLogIn(sc);
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (LoggedINUser.loggedInUserId != 0);
	}

	private static void buyerLogIn(Scanner sc) throws SQLException, SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		// Buyers buyers=new BuyersImpl();
		if (!BuyerUI.login(sc))
			return;

		System.out.println("welcome buyer");

		int choice = 0;
		do {
			displayBuyerMenu();
			System.out.print("Enter selection ");
			choice = sc.nextInt();
			switch (choice) {
//			case 1:
//				ItemUI.viewAllItemsByCategory();
//				break;
//
//			// no break statement here i.e. after deletion of user account, logout will also
//			// take place
			case 0:
				BuyerUI.logout();
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (LoggedINUser.loggedInUserId != 0);

	}

	private static void displayBuyerMenu() {
		// TODO Auto-generated method stub

	}

	private static void buyerSignUp(Scanner sc) throws SQLException, SomeThingWrongException {
		// TODO Auto-generated method stub
		BuyerUI.addBuyer(sc);
	}

}
