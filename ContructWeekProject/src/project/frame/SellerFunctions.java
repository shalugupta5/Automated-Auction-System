package project.frame;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import project.dao.DbUtils;
import project.exception.SomeThingWrongException;
import project.ui.Main;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SellerFunctions extends JFrame {

	private JPanel contentPane;
	private JTextField itemID;
	private JTextField itemMRP;
	private JTextField itemDate;
	private JTextField itemName;
	private JTextField itemcategory;
	private JTextField search;
	private JTable table;
	Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerFunctions frame = new SellerFunctions();
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
	
	
	public void table_load() {
		try {
			connection = DbUtils.ConnectToDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// prepare the query
		String INSERT_QUERY = "select * from items";
		
		// get the prepared statement object
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			ResultSet resultSet=ps.executeQuery();
			ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int cols=rsmd.getColumnCount();
			String [] colname=new String[cols];
			for(int i=0;i<cols;i++) {
				colname[i]=rsmd.getColumnName(i+1);
				model.setColumnIdentifiers(colname);
				while(resultSet.next()) {
					String Item_id=resultSet.getString(1);
					String ItemName=resultSet.getString(2);
					String MRP=resultSet.getString(3);
					String MFGDate=resultSet.getString(4);
					String Category=resultSet.getString(5);
					//String Item_id=resultSet.getString(5);
					String row[]= {Item_id, ItemName, MRP, MFGDate, Category};
					model.addRow(row);
					
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SellerFunctions() {
		setTitle("Welcome Seller");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seller Operation");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(293, 11, 133, 44);
		contentPane.add(lblNewLabel);
		
		JPanel txt = new JPanel();
		txt.setBounds(35, 74, 322, 303);
		contentPane.add(txt);
		txt.setLayout(null);
		
		JLabel itemid = new JLabel("ItemID");
		itemid.setFont(new Font("Tahoma", Font.BOLD, 14));
		itemid.setBounds(10, 34, 79, 28);
		txt.add(itemid);
		
		JLabel txt1 = new JLabel("ItemName");
		txt1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt1.setBounds(10, 73, 79, 27);
		txt.add(txt1);
		
		JLabel txt2 = new JLabel("MRP");
		txt2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt2.setBounds(10, 111, 79, 27);
		txt.add(txt2);
		
		JLabel txt3 = new JLabel("MFGDate");
		txt3.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt3.setBounds(10, 149, 79, 31);
		txt.add(txt3);
		
		JLabel txt4 = new JLabel("Category");
		txt4.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt4.setBounds(10, 191, 89, 28);
		txt.add(txt4);
		
		itemID = new JTextField();
		itemID.setBounds(108, 34, 204, 26);
		txt.add(itemID);
		itemID.setColumns(10);
		
		itemMRP = new JTextField();
		itemMRP.setColumns(10);
		itemMRP.setBounds(108, 113, 204, 26);
		txt.add(itemMRP);
		
		itemDate = new JTextField();
		itemDate.setColumns(10);
		itemDate.setBounds(108, 153, 204, 26);
		txt.add(itemDate);
		
		itemName = new JTextField();
		itemName.setColumns(10);
		itemName.setBounds(108, 71, 204, 26);
		txt.add(itemName);
		
		itemcategory = new JTextField();
		itemcategory.setColumns(10);
		itemcategory.setBounds(108, 190, 204, 26);
		txt.add(itemcategory);
		
		JButton insert = new JButton("INSERT");
		insert.setFont(new Font("Tahoma", Font.PLAIN, 15));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String INSERT_QUERY = "INSERT INTO items (itemid, itemname, MRP, MFGDate, category) VALUES (?, ?, ?, ?, ?)";
					
					// get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
	
					// stuff the data in the query
					ps.setInt(1,  Integer.parseInt(itemID.getText()));
					ps.setString(2, itemName.getText());
				    ps.setDouble(3, Double.parseDouble(itemMRP.getText()));
					ps.setString(4,itemDate.getText());
					ps.setString(5, itemcategory.getText());

					// execute query
					ps.executeUpdate();
					JOptionPane.showMessageDialog(insert,"Record Inserted Successfully");
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					table_load();
				//	table_load();
					itemID.setText("");
					itemName.setText("");
					itemMRP.setText("");
					itemDate.setText("");
					itemcategory.setText("");
					itemID.requestFocus();
					
				} catch (SQLException sqlEx) {
					// code to log the error in the file
					//throw new SomeThingWrongException();
					JOptionPane.showMessageDialog(insert,"SomeThingWrong");
				} finally {
					try {
						// close the exception
						DbUtils.closeConnection(connection);
					} catch (SQLException sqlEX) {
						//throw new SomeThingWrongException();
					}
				}
			}
		});
		
		
		
		insert.setBounds(10, 256, 89, 23);
		txt.add(insert);
		
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exit.setBounds(117, 256, 89, 23);
		txt.add(exit);
		
		JButton clear = new JButton("CLEAR");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemID.setText("");
				itemName.setText("");
				itemMRP.setText("");
				itemDate.setText("");
				itemcategory.setText("");
			}
		});
		clear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		clear.setBounds(223, 256, 89, 23);
		txt.add(clear);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(33, 394, 324, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel itemid_1 = new JLabel("ItemID");
		itemid_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		itemid_1.setBounds(10, 11, 79, 28);
		panel.add(itemid_1);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					connection = DbUtils.ConnectToDatabase();
					String SELECT_QUERY = "SELECT * FROM items WHERE itemid = ? ";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
					ps.setString(1, search.getText());
					//execute query
					ResultSet resultSet = ps.executeQuery();
					
					if(resultSet.next()) {
						String itemid=resultSet.getString(1);
						String  itemname=resultSet.getString(2);
						String MRP=resultSet.getString(3);
						String MFGDate=resultSet.getString(4);
						String Category=resultSet.getString(5);
						
						itemID.setText(itemid);
						itemName.setText(itemname);
						itemMRP.setText(MRP);
						itemDate.setText(MFGDate);
						itemcategory.setText(Category);
						
					}
					else {
						itemID.setText("");
						itemName.setText("");
						itemMRP.setText("");
						itemDate.setText("");
						itemcategory.setText("");
					}
					
				/*	ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String [] colname=new String[cols];
					for(int i=0;i<cols;i++) {
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						while(resultSet.next()) {
							String Dispute_id=resultSet.getString(1);
							String Date=resultSet.getString(2);
							String Description=resultSet.getString(3);
							String Seller_id=resultSet.getString(4);
							String Buyer_id=resultSet.getString(5);
							String Item_id=resultSet.getString(5);
							String row[]= {Dispute_id, Date, Description, Seller_id, Buyer_id, Item_id};
							model.addRow(row);
							
							
						}
						
					}*/
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("No dispute for current date");
					e1.printStackTrace();
				}
			}
		});
		search.setColumns(10);
		search.setBounds(110, 13, 204, 26);
		panel.add(search);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(370, 77, 464, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton update = new JButton("UPDATE");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String UPDATE_QUERY = "UPDATE items set itemname = ?, MRP = ?, MFGDate = ?, category = ? WHERE itemid = ?";
						int x=Integer.parseInt(search.getText());
					// get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);

					// stuff the data in the query
					
					ps.setString(1, itemName.getText());
					ps.setDouble(2,Double.parseDouble(itemMRP.getText()));
					ps.setString(3, itemDate.getText());
					ps.setString(4, itemcategory.getText());
					ps.setInt(5, x);
					JOptionPane.showMessageDialog(update,"Update Successfully");
					// execute query
					ps.executeUpdate();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					table_load();
				} catch (SQLException sqlEx) {
					// code to log the error in the file
					//throw new SomeThingWrongException();
					JOptionPane.showMessageDialog(update,"SomeThingWrong");
				} finally {
					try {
						// close the exception
						DbUtils.closeConnection(connection);
					} catch (SQLException sqlEX) {
						//throw new SomeThingWrongException();
						JOptionPane.showMessageDialog(update,"SomeThingWrong");
					}
				}
				
			}
		});
		update.setFont(new Font("Tahoma", Font.PLAIN, 15));
		update.setBounds(514, 410, 89, 23);
		contentPane.add(update);
		
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection connection = null;
				try {
					//connect to database
					connection = DbUtils.ConnectToDatabase();
					//prepare the query
					String DELETE_QUERY = "DELETE FROM items WHERE itemid = ?";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
					
					//stuff the data in the query
					ps.setInt(1, Integer.parseInt(search.getText()));
					JOptionPane.showMessageDialog(delete,"Delete Successfully");
					//execute query
					
					ps.executeUpdate();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					table_load();
				}catch(SQLException sqlEx) {
					//code to log the error in the file
					//throw new SomeThingWrongException();
					System.out.println(sqlEx);
				}finally {
					try {
						//close the exception
						DbUtils.closeConnection(connection);				
					}catch(SQLException sqlEX) {
						//throw new SomeThingWrongException();
						System.out.println(sqlEX);
					}
				}
			}
		});
		delete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		delete.setBounds(638, 410, 89, 23);
		contentPane.add(delete);
		
		JButton view = new JButton("VIEW");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				table_load();
			}
		});
		view.setFont(new Font("Tahoma", Font.PLAIN, 15));
		view.setBounds(402, 410, 89, 25);
		contentPane.add(view);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 15));
		back.setBounds(35, 24, 89, 31);
		contentPane.add(back);
		
		JButton sold = new JButton("Sold");
		sold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DbUtils.ConnectToDatabase();
					String SELECT_QUERY = "select itemid, max(amount) from bid  group by itemid; ";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
					
					//execute query
					ResultSet resultSet = ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					int cols=rsmd.getColumnCount();
					String [] colname=new String[cols];
					for(int i=0;i<cols;i++) {
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						while(resultSet.next()) {
							String itemid=resultSet.getString(1);
							String amount=resultSet.getString(2);
							
							String row[]= {itemid, amount};
							model.addRow(row);
							
							
						}
						
					}
					JOptionPane.showMessageDialog(sold,"Sold Items are here");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("No dispute for current date");
					e1.printStackTrace();
				}
			}
		});
		sold.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sold.setBounds(544, 24, 111, 31);
		contentPane.add(sold);
	}
}



