package project.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.dto.Buyers;
import project.dto.BuyersImpl;
import project.dto.Seller;
import project.dto.SellerImpl;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;
import project.frame.SellerLogIn;

public class SellerOprationImpl implements SellerOpration {
	
	 public void addSeller(Seller sel) {
		 
	 
//	Connection conn=null;
//	
//	 try {
//		 conn=DbUtils.ConnectToDatabase();
//       
//         String query="INSERT into sellers(name, email, password, phne) values(?,?,?,?)";
//		 PreparedStatement ps=conn.prepareStatement(query);
//		 ps.setString(1, sel.getName());
//		 ps.setString(2, sel.getEmail());
//		 ps.setString(3, sel.getPassword());
//		 ps.setString(4,sel.getPhne());
//		 ps.executeUpdate();
//         System.out.println("Seller registered successfully.");
//      } catch(Exception e) {
//         System.out.println(e);
//      }
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

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		LoggedINUser.loggedInUserId = 0;
	}

	@Override
	public void Login(String name, String password) throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		
//		Connection connection = null;
//		try {
//			//connect to database
//			connection = DbUtils.ConnectToDatabase();
//			//prepare the query
//			String LOGIN_QUERY = "SELECT Seller_id FROM sellers WHERE name = ? AND password = ?";
//			
//			//get the prepared statement object
//			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
//			
//			//stuff the data in the query
//			ps.setString(1, name);
//			ps.setString(2, password);
//			
//			//execute query
//			ResultSet resultSet = ps.executeQuery();
//			if(DbUtils.isResultSetEmpty(resultSet)) {
//				throw new NoRecordFoundException("Invalid Username and Password Or You do not have Account please Ragister Yourself");
//			}
//			
//			//you are here means username and password combination is correct
//			resultSet.next();
//			LoggedINUser.loggedInUserId = resultSet.getInt("Seller_id");
//		}catch(SQLException sqlEx) {
//			//code to log the error in the file
//			throw new SomeThingWrongException();
//		}finally {
//			try {
//				//close the exception
//				DbUtils.closeConnection(connection);				
//			}catch(SQLException sqlEX) {
//				throw new SomeThingWrongException();
//			}
//		}
//		
	}

	
}
