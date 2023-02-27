package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtils {

	final static String URL;
	final static String USERNAME;
	final static String PASSWORD;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResourceBundle rs = ResourceBundle.getBundle("dbdetails");
		URL = rs.getString("url");
		USERNAME = rs.getString("username");
		PASSWORD = rs.getString("password");

	}

	public static Connection ConnectToDatabase() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	public static boolean isResultSetEmpty(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return (!rs.isBeforeFirst() && rs.getRow() == 0)?true:false;
		//return false;
	}
}
