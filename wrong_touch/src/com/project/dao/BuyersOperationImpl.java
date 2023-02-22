package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.dto.Buyers;

public class BuyersOperationImpl implements BuyersOperation{

	@Override
	public void addBuyer(Buyers bys) throws SQLException {
		Connection con = null;
		 con =DbUtils.ConnectToDatabase();
		 String query="INSERT into buyers(name, email, password, phne) values(?,?,?,?)";
		 PreparedStatement ps=con.prepareStatement(query);
		 ps.setString(1, bys.getName());
		 ps.setString(2, bys.getEmail());
		 ps.setString(3, bys.getPassword());
		 ps.setString(4,bys.getPhne());
		 ps.executeUpdate();
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	

}
