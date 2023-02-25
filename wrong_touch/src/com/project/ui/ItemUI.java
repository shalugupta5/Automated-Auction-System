package com.project.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


import com.project.dao.ItemOperation;
import com.project.dao.ItemOperationImpl;
import com.project.dto.Item;
import com.project.dto.ItemImpl;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;



public class ItemUI {

	private ItemOperation itemOperation;
	private Scanner scanner;
	public ItemUI(Scanner scanner) {
		super();
		itemOperation = new ItemOperationImpl();
		this.scanner = scanner;
	}
	
	public void addItem() throws SomeThingWrongException {
		//code to take input product details
		System.out.print("Enter item id ");
		int itemId = scanner.nextInt();
		System.out.print("Enter item name ");
		String itemName = scanner.next();
		System.out.print("Enter MRP ");
		double MRP = scanner.nextDouble();
		System.out.print("Enter MFG. Date ");
		LocalDate MFGDate = LocalDate.parse(scanner.next());
		System.out.print("Enter category ");
		String category = scanner.next();
		
		//create object for product with all details
		Item item= new ItemImpl(itemId, itemName, MRP, MFGDate, category);
		
		itemOperation.addItem(item);;
		System.out.println("Item added successfully");
	}

	

	

	public void updateItem() throws NoRecordFoundException {
		// TODO Auto-generated method stub
		//code to take input product details
		System.out.print("Enter product id ");
		int itemId = scanner.nextInt();
		System.out.print("Enter product name ");
		String itemName = scanner.next();
		System.out.print("Enter MRP ");
		double MRP = scanner.nextDouble();
		System.out.print("Enter MFG. Date ");
		LocalDate MFGDate = LocalDate.parse(scanner.next());
		System.out.print("Enter category ");
		String category = scanner.next();
		
		//create object for product with all details
		Item item= new ItemImpl(itemId, itemName, MRP, MFGDate, category);
		
		try {
			itemOperation.updateItem(item);
			System.out.println("Item updated successfully");
		}catch(SomeThingWrongException ex) {
			System.out.println(ex);
		}
	}

	public void removeItem() throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		
		System.out.print("Enter item id ");
		int itemId = scanner.nextInt();
		
		itemOperation.removeItem(itemId);
		System.out.println("Item deleted successfully");
	}

	public void createListOfItem() throws SQLException {
		// TODO Auto-generated method stub
		try {
			List<Item> listItem = itemOperation.getAllItems();
			listItem.forEach(System.out::println);
		}catch(SomeThingWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}



	public void viewItemsByCategory() throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		System.out.print("Enter category ");
		String category = scanner.next();
		itemOperation.getItemsByCategory(category);
		System.out.println("here you go");
		
	}

//	public void viewSoldItemHistory() {
//		// TODO Auto-generated method stub
//		
//	}

	

	
	
}
