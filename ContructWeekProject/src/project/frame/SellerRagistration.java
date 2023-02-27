package project.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.dao.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SellerRagistration extends JFrame {

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
					SellerRagistration frame = new SellerRagistration();
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
	public SellerRagistration() {
		setTitle("SellerRagistration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel user = new JLabel("Name");
		user.setFont(new Font("Tahoma", Font.BOLD, 15));
		user.setBounds(53, 77, 96, 33);
		contentPane.add(user);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(53, 145, 96, 33);
		contentPane.add(lblEmail);
		
		JLabel username_1_1 = new JLabel("Password");
		username_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		username_1_1.setBounds(53, 208, 96, 33);
		contentPane.add(username_1_1);
		
		JLabel username_1_2 = new JLabel("Phone");
		username_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		username_1_2.setBounds(53, 271, 96, 33);
		contentPane.add(username_1_2);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String INSERT_QUERY = "INSERT INTO sellers (name, email, password, phne) VALUES (?, ?, ?, ?)";
					
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
					new SellerLogIn().setVisible(true);
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
		submit.setBounds(321, 357, 89, 23);
		contentPane.add(submit);
		
		name = new JTextField();
		name.setBounds(219, 77, 290, 33);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(219, 145, 290, 33);
		contentPane.add(email);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(219, 271, 290, 33);
		contentPane.add(phone);
		
		password = new JPasswordField();
		password.setBounds(219, 208, 290, 33);
		contentPane.add(password);
	}

}
