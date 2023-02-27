package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import project.dto.Dispute;
//import project.dto.Item;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;
import project.frame.DailyDispute;

public class DisputeOperationImpl implements DisputeOperation {

	@Override
	public void viewDisputeReport() throws NoRecordFoundException, SomeThingWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		
		try {
			//connect to database
			connection = DbUtils.ConnectToDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM dispute WHERE DATE = CURDATE() ";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DbUtils.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Data Found");
			}
			
			new DailyDispute().setVisible(true);
//			 while (resultSet.next()) {
//	                // Retrieve by column name
//	                int id = resultSet.getInt("buyer_id");
//	                String description = resultSet.getString("description");
//	               // String status = resultSet.getString("status");
//
//	                // Display values
//	                System.out.println("ID: " + id);
//	                System.out.println("Description: " + description);
//	               // System.out.println("Status: " + status);
//	            }

			
			
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

}
