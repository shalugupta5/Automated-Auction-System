package project.dao;

import java.sql.Connection;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;
import project.dto.Buyers;
import project.dto.BuyersImpl;

//
//import com.project.exception.NoRecordFoundException;
//import com.project.exception.SomeThingWrongException;
//
//import com.project.dao.DbUtils;
//import com.project.dto.Buyers;
//import com.project.dto.BuyersImpl;

public class BuyersOperationImpl implements BuyersOperation{

//	@Override
//	public void addBuyer(Buyers bys) throws SomeThingWrongException  {
//		Connection connection = null;
//		try {
//			
//		
//		connection =DbUtils.ConnectToDatabase();
//		 String query="INSERT into buyers(name, email, password, phne) values(?,?,?,?)";
//		 PreparedStatement ps=connection.prepareStatement(query);
//		 ps.setString(1, bys.getName());
//		 ps.setString(2, bys.getEmail());
//		 ps.setString(3, bys.getPassword());
//		 ps.setString(4,bys.getPhne());
//		 ps.executeUpdate();
//	}
//		// TODO Auto-generated method stub
//		 catch(SQLException sqlEx) {
////				sqlEx.printStackTrace();
//				//code to log the error in the file
//				throw new SomeThingWrongException();
//			}finally {
//				try {
//					//close the exception
//					DbUtils.closeConnection(connection);				
//				}catch(SQLException sqlEX) {
//					throw new SomeThingWrongException();
//				}
//			}
//	}

	
	private List<Buyers> getByuersListFromResultSet(ResultSet resultSet) throws SQLException{
		List<Buyers> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			Buyers buyers = new BuyersImpl();
			//buyers.setBuyersId(resultSet.getInt("buyers_id"));
		buyers.setName(resultSet.getString("name"));
		buyers.setEmail(resultSet.getString("email"));
		buyers.setPassword(resultSet.getString("password"));
		buyers.setPhne(resultSet.getString("phne"));
			
			list.add(buyers);
		}
		return list;
	}

	@Override
	public List<Buyers> getAllBuyers() throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Buyers> list = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM buyers";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No category Found");
			}
			
			list = getByuersListFromResultSet(resultSet);
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

	@Override
	public void addBuyer(Buyers buyers) throws SQLException {
		
		
		Connection connection = null;
		
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO Buyers (name, email, password, phne) values(?,?,?,?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, buyers.getName());
			ps.setString(2, buyers.getEmail());
			ps.setString(3, buyers.getPassword());
			ps.setString(4, buyers.getPhne());
		
			
			//execute query
			ps.executeUpdate();
		
		
		
		
	}

	@Override
	public void Login(String name, String password) throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT Buyer_id FROM buyers WHERE name = ? AND password = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, name);
			ps.setString(2, password);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("Invalid Username and Password Or You do not have Account please Ragister Yourself");
			}
			
			//you are here means username and password combination is correct
			resultSet.next();
			//LoggedINUser.loggedInUserId = resultSet.getInt("Buyer_id");
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
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		//LoggedINUser.loggedInUserId = 0;
	}


	
	
	

}
