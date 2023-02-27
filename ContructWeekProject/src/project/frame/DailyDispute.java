package project.frame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import project.dao.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DailyDispute extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailyDispute frame = new DailyDispute();
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
	public DailyDispute() {
		setTitle("DailyDispute");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton display = new JButton("Display");
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DbUtils.ConnectToDatabase();
					String SELECT_QUERY = "SELECT * FROM dispute WHERE DATE = CURDATE() ";
					
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
							String Dispute_id=resultSet.getString(1);
							String Date=resultSet.getString(2);
							String Description=resultSet.getString(3);
							String Seller_id=resultSet.getString(4);
							String Buyer_id=resultSet.getString(5);
							String Item_id=resultSet.getString(6);
							String status=resultSet.getString(7);
							String row[]= {Dispute_id, Date, Description, Seller_id, Buyer_id, Item_id, status};
							model.addRow(row);
							
							
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("No dispute for current date");
					e1.printStackTrace();
				}
			}
		});
		display.setFont(new Font("Tahoma", Font.BOLD, 15));
		display.setBounds(10, 184, 134, 23);
		contentPane.add(display);
		
		JButton back = new JButton("back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMenuBar().setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.BOLD, 15));
		back.setBounds(10, 263, 134, 23);
		contentPane.add(back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 95, 491, 278);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Daily Dispute Report");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(242, 42, 175, 30);
		contentPane.add(lblNewLabel);
		
		JButton solve = new JButton("Resolve");
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {
					// connect to database
					connection = DbUtils.ConnectToDatabase();
					// prepare the query
					String UPDATE_QUERY = "UPDATE dispute set status = ?";
//						int x=Integer.parseInt(search.getText());
					// get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);

					// stuff the data in the query
					
					ps.setString(1, "Resolved");
//					ps.setDouble(2,Double.parseDouble(itemMRP.getText()));
//					ps.setString(3, itemDate.getText());
//					ps.setString(4, itemcategory.getText());
//					ps.setInt(5, x);
					JOptionPane.showMessageDialog(solve,"Solve Successfully");
					// execute query
					ps.executeUpdate();
//					DefaultTableModel model = (DefaultTableModel) table.getModel();
//					model.setRowCount(0);
					
				} catch (SQLException sqlEx) {
					// code to log the error in the file
					//throw new SomeThingWrongException();
					JOptionPane.showMessageDialog(solve,"SomeThingWrong");
				} finally {
					try {
						// close the exception
						DbUtils.closeConnection(connection);
					} catch (SQLException sqlEX) {
						//throw new SomeThingWrongException();
						JOptionPane.showMessageDialog(solve,"SomeThingWrong");
					}
				}
			}
		});
		solve.setFont(new Font("Tahoma", Font.BOLD, 15));
		solve.setBounds(10, 328, 134, 23);
		contentPane.add(solve);
	}

}
