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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DisplayAllSellers extends JFrame {

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
					DisplayAllSellers frame = new DisplayAllSellers();
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
	public DisplayAllSellers() {
		setTitle("AllSellers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton display = new JButton("Display");
		display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DbUtils.ConnectToDatabase();
					String SELECT_QUERY = "SELECT * FROM Sellers";
					
					//get the prepared statement object
					PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
					
					//execute query
					ResultSet resultSet = ps.executeQuery();
					ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String [] colname=new String[cols];
					for(int i=0;i<cols;i++) {
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						while(resultSet.next()) {
							String name=resultSet.getString(1);
							String email=resultSet.getString(2);
							String password=resultSet.getString(3);
							String phone=resultSet.getString(4);
							String buyer_id=resultSet.getString(5);
							String row[]= {name, email, password, phone, buyer_id};
							model.addRow(row);
							
							
						}
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		display.setFont(new Font("Tahoma", Font.BOLD, 15));
		display.setBounds(10, 158, 111, 23);
		contentPane.add(display);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMenuBar().setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.BOLD, 15));
		back.setBounds(10, 241, 111, 23);
		contentPane.add(back);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 92, 430, 290);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("All Sellers Data");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(269, 45, 139, 31);
		contentPane.add(lblNewLabel);
	}

}
