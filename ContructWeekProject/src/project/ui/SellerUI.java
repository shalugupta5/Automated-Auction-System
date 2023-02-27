package project.ui;

import java.util.List;

import java.util.Scanner;

import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;
import project.frame.DisplayAllBuyers;
import project.frame.DisplayAllSellers;
import project.dao.SellerOpration;
import project.dao.SellerOprationImpl;
import project.dto.Seller;
import project.dto.SellerImpl;





//import com.project.dao.BuyersOperation;

public class SellerUI {
	private static SellerOpration sellerOpration;
	private String scanner;
	public SellerUI(String string) {
		// TODO Auto-generated constructor stub
		sellerOpration = new SellerOprationImpl();
		this.scanner = string;
	}

	public void viewAllSellers() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		List<Seller> list = sellerOpration.getAllSellers();
		new DisplayAllSellers().setVisible(true);
//		for(Seller x:list) {
//			System.out.println(x);
//		}
		//list.forEach(System.out::println);
		
	}

	public static void addSeller(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("Enter name ");
		String name = sc.next();
		System.out.print("Enter email ");
		String email = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		System.out.print("Enter phne ");
		String phne = sc.next();
		
		//create object for user with all details
		Seller seller = new SellerImpl(name, email, password, phne);
		
		sellerOpration.addSeller(seller);;
		System.out.println("Seller registered successfully");
	}

	public static boolean login(Scanner sc) throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		boolean loginSuccessful = false;
		//code to take input username and password
		System.out.print("Enter username ");
		String username = sc.next();
		
		System.out.print("Enter password ");
		String password = sc.next();
		
		sellerOpration.Login(username, password);
		loginSuccessful = true;
		return loginSuccessful;
	}
	
	public static void logout() {
		sellerOpration.logout();
	
		//return false;
	}

	

}
