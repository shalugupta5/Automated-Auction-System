package project.dao;

import java.util.List;

import project.dto.Seller;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;

public interface SellerOpration {

	// public void register(Seller sel);

	public List<Seller> getAllSellers() throws NoRecordFoundException, SomeThingWrongException;

	public void addSeller(Seller seller);

	public void logout();

	public void Login(String username, String password) throws NoRecordFoundException, SomeThingWrongException;
	 
	
}
