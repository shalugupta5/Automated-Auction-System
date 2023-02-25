package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.dto.Item;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public interface ItemOperation {

	void addItem(Item item) throws SomeThingWrongException;

//	void updateItem(Item item) ;

	void updateItem(Item item)throws SomeThingWrongException, NoRecordFoundException;

	

	void removeItem(Integer itemId) throws  NoRecordFoundException, SomeThingWrongException;

	List<Item> getAllItems() throws NoRecordFoundException, SomeThingWrongException, SQLException;

	void getItemsByCategory(String category) throws NoRecordFoundException, SomeThingWrongException;
	

}
