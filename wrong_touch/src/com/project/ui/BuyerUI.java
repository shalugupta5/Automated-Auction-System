package com.project.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


import com.project.dao.BuyersOperation;
import com.project.dao.BuyersOperationImpl;
import com.project.dto.Buyers;
import com.project.dto.BuyersImpl;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public class BuyerUI {
	private static BuyersOperation buyersOperation;
	private Scanner scanner;

	public BuyerUI(Scanner scanner) {
		// TODO Auto-generated constructor stub
		buyersOperation=new BuyersOperationImpl();
		this.scanner = scanner;
	}

	public void viewAllBuyers() throws SomeThingWrongException, NoRecordFoundException {
		List<Buyers> list = buyersOperation.getAllBuyers();
		for(Buyers x:list) {
			System.out.println(x);
		}
		//list.forEach(System.out::println);
		
	}

	

	public static void addBuyer(Scanner sc) throws SQLException, SomeThingWrongException {
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
		Buyers buyers = new BuyersImpl(name, email, password, phne);
		
		buyersOperation.addBuyer(buyers);;
		System.out.println("User registered successfully");
	}

	public static boolean login(Scanner sc) throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		boolean loginSuccessful = false;
		//code to take input username and password
		System.out.print("Enter username ");
		String username = sc.next();
		
		System.out.print("Enter password ");
		String password = sc.next();
		
		buyersOperation.Login(username, password);
		loginSuccessful = true;
		return loginSuccessful;
	}
	
	public static void logout() {
		buyersOperation.logout();
	}

	
}
