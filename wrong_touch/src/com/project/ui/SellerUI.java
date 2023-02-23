package com.project.ui;

import java.util.List;
import java.util.Scanner;

import com.project.dao.SellerOpration;
import com.project.dao.SellerOprationImpl;
import com.project.dto.Seller;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;



//import com.project.dao.BuyersOperation;

public class SellerUI {
	private SellerOpration sellerOpration;
	private Scanner scanner;
	public SellerUI(Scanner scanner) {
		// TODO Auto-generated constructor stub
		sellerOpration = new SellerOprationImpl();
		this.scanner = scanner;
	}

	public void viewAllSellers() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		List<Seller> list = sellerOpration.getAllSellers();
		for(Seller x:list) {
			System.out.println(x);
		}
		//list.forEach(System.out::println);
		
	}

}
