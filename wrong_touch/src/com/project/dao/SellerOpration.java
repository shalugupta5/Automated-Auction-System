package com.project.dao;

import java.util.List;

import com.project.dto.Seller;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public interface SellerOpration {

	 public void register(Seller sel);

	public List<Seller> getAllSellers() throws NoRecordFoundException, SomeThingWrongException;
	 
	
}
