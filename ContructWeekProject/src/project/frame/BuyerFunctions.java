package project.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import project.dao.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BuyerFunctions extends JFrame {

	private JPanel contentPane;
	private JTextField itemID;
	private JTextField itemName;
	private JTextField itemMRP;
	private JTextField itemDate;
	private JTextField category;
	private JTextField search;
	private JTable table;
	Connection connection;
	private JTextField amount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyerFunctions frame = new BuyerFunctions();
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
	public BuyerFunctions() {
		setTitle("BuyerOperations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 39, 347, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ItemID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 33, 102, 32);
		panel.add(lblNewLabel);
		
		JLabel lblItemname = new JLabel("ItemName");
		lblItemname.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblItemname.setBounds(10, 76, 102, 32);
		panel.add(lblItemname);
		
		JLabel lblMrp = new JLabel("MRP");
		lblMrp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMrp.setBounds(10, 119, 102, 32);
		panel.add(lblMrp);
		
		JLabel lblMfgdate = new JLabel("MFGDate");
		lblMfgdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMfgdate.setBounds(10, 162, 102, 32);
		panel.add(lblMfgdate);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategory.setBounds(10, 205, 102, 32);
		panel.add(lblCategory);
		
		itemID = new JTextField();
		itemID.setBounds(122, 29, 213, 32);
		panel.add(itemID);
		itemID.setColumns(10);
		
		itemName = new JTextField();
		itemName.setColumns(10);
		itemName.setBounds(122, 72, 213, 32);
		panel.add(itemName);
		
		itemMRP = new JTextField();
		itemMRP.setColumns(10);
		itemMRP.setBounds(122, 117, 213, 32);
		panel.add(itemMRP);
		
		itemDate = new JTextField();
		itemDate.setColumns(10);
		itemDate.setBounds(122, 161, 213, 32);
		panel.add(itemDate);
		
		category = new JTextField();
		category.setColumns(10);
		category.setBounds(122, 207, 213, 32);
		panel.add(category);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 349, 347, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ItemID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 11, 102, 32);
		panel_1.add(lblNewLabel_1);
		
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
						category.setText(Category);
						
					}
					else {
						itemID.setText("");
						itemName.setText("");
						itemMRP.setText("");
						itemDate.setText("");
						category.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("No dispute for current date");
					e1.printStackTrace();
				}
			}
		});
		search.setColumns(10);
		search.setBounds(124, 11, 213, 32);
		panel_1.add(search);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 39, 380, 281);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton antiques = new JButton("Antiques");
		antiques.setFont(new Font("Tahoma", Font.PLAIN, 15));
		antiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
					connection = DbUtils.ConnectToDatabase();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// prepare the query
				String INSERT_QUERY = "select * from items where category=?";
				
				
				// get the prepared statement object
				try {
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
					ps.setString(1, "antiques");
					ResultSet resultSet=ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		antiques.setBounds(400, 360, 160, 33);
		contentPane.add(antiques);
		
		JButton electronics = new JButton("Electronics");
		electronics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
					connection = DbUtils.ConnectToDatabase();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// prepare the query
				String INSERT_QUERY = "select * from items where category=?";
				
				
				// get the prepared statement object
				try {
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
					ps.setString(1, "electronics");
					ResultSet resultSet=ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		electronics.setFont(new Font("Tahoma", Font.PLAIN, 15));
		electronics.setBounds(589, 360, 160, 33);
		contentPane.add(electronics);
		
		JButton clothing = new JButton("Clothing");
		clothing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
					connection = DbUtils.ConnectToDatabase();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// prepare the query
				String INSERT_QUERY = "select * from items where category=?";
				
				
				// get the prepared statement object
				try {
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
					ps.setString(1, "clothing");
					ResultSet resultSet=ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		clothing.setFont(new Font("Tahoma", Font.PLAIN, 15));
		clothing.setBounds(400, 417, 160, 33);
		contentPane.add(clothing);
		
		JButton other = new JButton("Other");
		other.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
					connection = DbUtils.ConnectToDatabase();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// prepare the query
				String INSERT_QUERY = "select * from items where NOT category=? AND NOT category=? AND NOT category=?";
				
				
				// get the prepared statement object
				try {
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
					ps.setString(1, "antiques");
					ps.setString(2, "electronics");
					ps.setString(3, "clothing");
					ResultSet resultSet=ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		other.setFont(new Font("Tahoma", Font.PLAIN, 15));
		other.setBounds(589, 417, 160, 33);
		contentPane.add(other);
		
		JButton buy = new JButton("Biding");
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String INSERT_QUERY = "INSERT INTO bid (start_date, end_date, itemid, amount) VALUES (NOW(), CURDATE()+1, ?, ?)";
					
					// get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
	
					// stuff the data in the query
				
					ps.setInt(1, Integer.parseInt(search.getText()));
					
					ps.setInt(2, Integer.parseInt(amount.getText()));
				   

					// execute query
					ps.executeUpdate();
					JOptionPane.showMessageDialog(buy,"Record Inserted Successfully");
					
					//table_load();
				//	table_load();
					itemID.setText("");
					itemName.setText("");
					itemMRP.setText("");
					itemDate.setText("");
					//itemcategory.setText("");
					itemID.requestFocus();
					
				} catch (SQLException sqlEx) {
					// code to log the error in the file
					//throw new SomeThingWrongException();
					JOptionPane.showMessageDialog(buy,"SomeThingWrong");
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
		buy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buy.setBounds(28, 414, 125, 36);
		contentPane.add(buy);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Enter Biding Amount", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(163, 401, 214, 49);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		amount = new JTextField();
		amount.setBounds(10, 21, 194, 28);
		panel_2.add(amount);
		amount.setColumns(10);
	}
}
