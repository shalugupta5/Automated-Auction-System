package com.project.ui;

import java.sql.SQLException;

import com.project.dao.BuyersOperation;
import com.project.dao.BuyersOperationImpl;
import com.project.dto.Buyers;
import com.project.dto.BuyersImpl;

public class Main {

	public static void main(String[] args) throws SQLException {
		BuyersOperation b=new BuyersOperationImpl();
		
		Buyers bys=new BuyersImpl("shalu", "shalu@gmail.com", "12345", "1234567890");
		b.addBuyer(bys);
		System.out.println("Buyer addedd successfully");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}
	
}
