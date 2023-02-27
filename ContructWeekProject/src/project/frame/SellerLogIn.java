package project.frame;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.dao.DbUtils;
import project.dao.LoggedINUser;
import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SellerLogIn extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerLogIn frame = new SellerLogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SellerLogIn() {
		setTitle("SellerLogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textfield = new JLabel("User Name");
		textfield.setFont(new Font("Tahoma", Font.BOLD, 15));
		textfield.setBounds(60, 100, 100, 32);
		contentPane.add(textfield);
		
		JLabel text = new JLabel("password");
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		text.setBounds(60, 165, 100, 25);
		contentPane.add(text);
		
		username = new JTextField();
		username.setBounds(192, 108, 301, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(192, 169, 301, 20);
		contentPane.add(password);
		
		JButton login = new JButton("LogIn");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					//connect to database
					connection = DbUtils.ConnectToDatabase();
					//prepare the query
					String LOGIN_QUERY = "SELECT Seller_id FROM sellers WHERE name = ? AND password = ?";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
					
					//stuff the data in the query
					ps.setString(1, username.getText());
					ps.setString(2, password.getText());
					//ps.setString(2, String.valueOf(password.getPassword()));
					
					//execute query
					ResultSet resultSet = ps.executeQuery();
//					
					if(DbUtils.isResultSetEmpty(resultSet)) {
						JOptionPane.showMessageDialog(login,"SomeThingWentWrong, Please check your username or password");
					}
					else {
						JOptionPane.showMessageDialog(login,"Welcome, Seller"+" "+ username.getText());
						new SellerFunctions().setVisible(true);
					}
					//you are here means username and password combination is correct
				//	resultSet.next();
					//LoggedINUser.loggedInUserId = resultSet.getInt("Seller_id");
				}catch(SQLException sqlEx) {
					//code to log the error in the file
					System.out.println(sqlEx);
				}finally {
					try {
						//close the exception
						DbUtils.closeConnection(connection);				
					}catch(SQLException sqlEX) {
						System.out.println(sqlEX);
					}
				}
				
			
			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 15));
		login.setBounds(192, 281, 89, 23);
		contentPane.add(login);
		
		JButton signup = new JButton("SignUp");
		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SellerRagistration().setVisible(true);
			}
		});
		signup.setFont(new Font("Tahoma", Font.BOLD, 15));
		signup.setBounds(404, 281, 89, 23);
		contentPane.add(signup);
	}

}
