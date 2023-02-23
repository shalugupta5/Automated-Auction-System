package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.Buyers;
import com.project.dto.BuyersImpl;
import com.project.dto.Seller;
import com.project.dto.SellerImpl;
import com.project.exception.NoRecordFoundException;
import com.project.exception.SomeThingWrongException;

public class SellerOprationImpl implements SellerOpration {
	 public void register(Seller sel) {
		 
	 
	Connection conn=null;
	
	 try {
		 conn=DbUtils.ConnectToDatabase();
       
         String query="INSERT into buyers(name, email, password, phne) values(?,?,?,?)";
		 PreparedStatement ps=conn.prepareStatement(query);
		 ps.setString(1, sel.getName());
		 ps.setString(2, sel.getEmail());
		 ps.setString(3, sel.getPassword());
		 ps.setString(4,sel.getPhne());
		 ps.executeUpdate();
         System.out.println("Seller registered successfully.");
      } catch(Exception e) {
         System.out.println(e);
      }
	 }

	@Override
	public List<Seller> getAllSellers() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		List<Seller> list = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM sellers";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No category Found");
			}
			
			list = getSellerListFromResultSet(resultSet);
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

	private List<Seller> getSellerListFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Seller> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			Seller seller = new SellerImpl();
			//buyers.setBuyersId(resultSet.getInt("buyers_id"));
			seller.setName(resultSet.getString("name"));
			seller.setEmail(resultSet.getString("email"));
			seller.setPassword(resultSet.getString("password"));
			seller.setPassword(resultSet.getString("phne"));
			
			list.add(seller);
		}
		return list;
		//return null;
	}
	
}
