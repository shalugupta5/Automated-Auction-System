package project.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.dao.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BuyerRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField phone;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyerRegistration frame = new BuyerRegistration();
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
	public BuyerRegistration() {
		setTitle("BuyerRegistraion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(58, 98, 109, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(58, 157, 109, 30);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(58, 213, 109, 30);
		contentPane.add(lblPassword);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhone.setBounds(58, 274, 109, 30);
		contentPane.add(lblPhone);
		
		name = new JTextField();
		name.setBounds(220, 97, 298, 37);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(220, 156, 298, 37);
		contentPane.add(email);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(220, 267, 298, 37);
		contentPane.add(phone);
		
		password = new JPasswordField();
		password.setBounds(220, 212, 298, 37);
		contentPane.add(password);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String INSERT_QUERY = "INSERT INTO buyers (name, email, password, phne) VALUES (?, ?, ?, ?)";
					
					// get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
	
					// stuff the data in the query
					ps.setString(1,  name.getText());
					ps.setString(2, email.getText());
				    ps.setString(3, password.getText());
					ps.setString(4, phone.getText());
					

					// execute query
					ps.executeUpdate();
					JOptionPane.showMessageDialog(submit,"Record Inserted Successfully");
					//DefaultTableModel model = (DefaultTableModel) table.getModel();
					//model.setRowCount(0);
				//	table_load();
					new BuyerLogIn().setVisible(true);
					name.setText("");
					email.setText("");
					password.setText("");
					phone.setText("");
					
					
				} catch (SQLException sqlEx) {
					// code to log the error in the file
					//throw new SomeThingWrongException();
					JOptionPane.showMessageDialog(submit,"SomeThingWrong");
				} finally {
					try {
						// close the exception
						DbUtils.closeConnection(connection);
					} catch (SQLException sqlEX) {
						//throw new SomeThingWrongException();
						JOptionPane.showMessageDialog(submit,"SomeThingWrong");
					}
				}
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submit.setBounds(328, 373, 89, 23);
		contentPane.add(submit);
	}
}
