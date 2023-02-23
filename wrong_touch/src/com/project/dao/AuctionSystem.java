package com.project.dao;

import java.sql.*;

public class AuctionSystem {

    public static void main(String[] args) {
    	Connection conn=null;
        try {
            // Connect to the MySQL database
             conn = DbUtils.ConnectToDatabase();
            
            // View the registered buyer list
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM buyers");
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            // View the registered seller list
            rs = stmt.executeQuery("SELECT * FROM sellers");
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            // View the daily dispute report
            rs = stmt.executeQuery("SELECT * FROM disputes WHERE date = CURDATE()");
            while(rs.next()) {
                System.out.println(rs.getString("description"));
            }
            
            // View the daily selling report
            rs = stmt.executeQuery("SELECT * FROM sales WHERE date = CURDATE()");
            while(rs.next()) {
                System.out.println(rs.getString("item") + " sold for " + rs.getFloat("price"));
            }
            
            // Solve the dispute report
            stmt.executeUpdate("UPDATE disputes SET status = 'resolved' WHERE id = 1");
            
            // Close the connection
            conn.close();
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
