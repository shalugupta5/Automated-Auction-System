package com.project.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.project.dto.Item;
import com.project.dto.ItemImpl;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public class ItemOperationImpl implements ItemOperation {

	@Override
	public void addItem(Item item) throws SomeThingWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			// connect to database
			connection = DbUtils.ConnectToDatabase();
			// prepare the query
			String INSERT_QUERY = "INSERT INTO items (itemid, itemname, MRP, MFGDate, category) VALUES (?, ?, ?, ?, ?)";

			// get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);

			// stuff the data in the query
			ps.setInt(1, item.getItemId());
			ps.setString(2, item.getItemName());
			ps.setDouble(3, item.getMRP());
			ps.setDate(4, Date.valueOf(item.getMFGDate()));
			ps.setString(5, item.getCategory());

			// execute query
			ps.executeUpdate();
		} catch (SQLException sqlEx) {
			// code to log the error in the file
			throw new SomeThingWrongException();
		} finally {
			try {
				// close the exception
				DbUtils.closeConnection(connection);
			} catch (SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}

	}

	@Override
	public void updateItem(Item item) throws SomeThingWrongException {
		// TODO Auto-generated method stub
		// getItemById(item.getItemId());
		// getItemById(product.getProdId());
		Connection connection = null;
		try {
			// connect to database
			connection = DbUtils.ConnectToDatabase();
			// prepare the query
			String UPDATE_QUERY = "UPDATE items set itemname = ?, MRP = ?, MFGDate = ?, category = ? WHERE itemid = ?";

			// get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);

			// stuff the data in the query
			ps.setString(1, item.getItemName());
			ps.setDouble(2, item.getMRP());
			ps.setDate(3, Date.valueOf(item.getMFGDate()));
			ps.setString(4, item.getCategory());
			ps.setInt(5, item.getItemId());

			// execute query
			ps.executeUpdate();
		} catch (SQLException sqlEx) {
			// code to log the error in the file
			throw new SomeThingWrongException();
		} finally {
			try {
				// close the exception
				DbUtils.closeConnection(connection);
			} catch (SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
	}

	@Override
	public void removeItem(Integer itemId){
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String DELETE_QUERY = "DELETE FROM items WHERE itemid = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, itemId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			//throw new SomeThingWrongException();
			System.out.println(sqlEx);
		}finally {
			try {
				//close the exception
				DbUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				//throw new SomeThingWrongException();
				System.out.println(sqlEX);
			}
		}
	}

	public List<Item> getAllItems() throws  SomeThingWrongException, NoRecordFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Item> list = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * from items";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No product Found");
			}
			
			list = getItemListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomeThingWrongException();
		}finally {
			try {
				//close the exception
				DbUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomeThingWrongException();
			}
		}
		return list;
		//return null;
	}

	private List<Item> getItemListFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		List<Item> list = new ArrayList<>();
		while(resultSet.next()) {
			Item item = new ItemImpl();
			item.setItemId(resultSet.getInt("itemid"));
			item.setItemName(resultSet.getString("itemname"));
			item.setMRP(resultSet.getDouble("MRP"));
			item.setMFGDate(resultSet.getDate("MFGDate").toLocalDate());
			
//			if(resultSet.getInt("itemid") == 0) {
//				//you are here means the product does not belong to any category
//				item.setCategory(null);
//			}else {
//				//you are here means the product belongs to a category
//				item.setCategory(resultSet.getString("category"));
//			}

			list.add(item);
		}
		return list;
		//return null;
	}

	@Override
	public void getItemsByCategory(String category)    {
		// TODO Auto-generated method stub
		Connection connection = null;
		//List<Product> list = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM items WHERE category = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, category);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No product Found in this category");
			}
			 while (resultSet.next()) {
	                int itemid = resultSet.getInt("itemid");
	                String itemname = resultSet.getString("itemname");
	                double MRP = resultSet.getDouble("MRP");
	                String MFGDate =resultSet.getString("MFGDate");
	                
	                System.out.println(itemid + "\t" + itemname + "\t" + MRP + "\t" + MFGDate);
	            }
			//list = getProductListFromResultSet(resultSet);
		}catch(SQLException |NoRecordFoundException sqlEx) {
			//code to log the error in the file
			//throw new SomeThingWrongException();
			System.out.println(sqlEx);
		}finally {
			try {
				//close the exception
				DbUtils.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				//throw new SomeThingWrongException();
				System.out.println(sqlEX);
			}
		}
		
	}

}


