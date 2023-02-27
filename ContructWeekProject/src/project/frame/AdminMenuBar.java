package project.frame;

import java.awt.EventQueue;


import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import project.exception.NoRecordFoundException;
import project.exception.SomeThingWrongException;
import project.dto.Buyers;
import project.dto.Dispute;
import project.ui.BuyerUI;
import project.ui.DisputeUI;
import project.ui.Main;
import project.ui.SellerUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenuBar extends JFrame {

	private JPanel contentPane;
	private JTextField adminselection;
	private static BuyerUI buyerUI;
	private static SellerUI sellerUI;
	private static DisputeUI disputeUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//String x=null;
		//Scanner sc=new Scanner(System.in);
		//buyerUI = new BuyerUI(null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenuBar frame = new AdminMenuBar();
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
	public AdminMenuBar() {
		setTitle("AdminMenuDisplay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel buyerlist = new JLabel("1.  View the registered buyer list.");
		buyerlist.setFont(new Font("Tahoma", Font.BOLD, 15));
		buyerlist.setBounds(215, 64, 307, 29);
		contentPane.add(buyerlist);
		
		JLabel sellerlist = new JLabel("2.  View the registered Seller list.");
		sellerlist.setFont(new Font("Tahoma", Font.BOLD, 15));
		sellerlist.setBounds(215, 117, 278, 29);
		contentPane.add(sellerlist);
		
		JLabel disptelist = new JLabel("3.  View the daily dispute report.");
		disptelist.setFont(new Font("Tahoma", Font.BOLD, 15));
		disptelist.setBounds(215, 170, 278, 29);
		contentPane.add(disptelist);
		
		JLabel selllist = new JLabel("4.  View the daily selling report.");
		selllist.setFont(new Font("Tahoma", Font.BOLD, 15));
		selllist.setBounds(215, 220, 278, 29);
		contentPane.add(selllist);
		
		JLabel solvedispute = new JLabel("5.  Solve the dispute report.");
		solvedispute.setFont(new Font("Tahoma", Font.BOLD, 15));
		solvedispute.setBounds(215, 268, 278, 34);
		contentPane.add(solvedispute);
		
		JLabel selectionadmin = new JLabel("Enter Selection NUmber");
		selectionadmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selectionadmin.setBounds(258, 325, 176, 20);
		contentPane.add(selectionadmin);
		
		adminselection = new JTextField();
		adminselection.setBounds(288, 356, 89, 20);
		contentPane.add(adminselection);
		adminselection.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminselection.getText().equals("1")) {
					try {
						buyerUI = new BuyerUI(adminselection.getText());
						
						buyerUI.viewAllBuyers();
					} catch (SomeThingWrongException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoRecordFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} ;
				}
				
				else if(adminselection.getText().equals("2")) {
					try {
						sellerUI = new SellerUI(adminselection.getText());
						
						sellerUI.viewAllSellers();
					} catch (SomeThingWrongException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoRecordFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} ;
				}
				
				else if(adminselection.getText().equals("3")) {
					try {
						disputeUI = new DisputeUI(adminselection.getText());
						
						disputeUI.viewDisputeReport();
					} catch (SomeThingWrongException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoRecordFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} ;
				}
				
				else if(adminselection.getText().equals("4")) {
					new DailySellingReport().setVisible(true);
					
				}
				
				else if(adminselection.getText().equals("5")) {
					new DailyDispute().setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(submit,"SomeThingWrong");
				}
			}
		});
		submit.setFont(new Font("Tahoma", Font.BOLD, 15));
		submit.setBounds(288, 387, 89, 23);
		contentPane.add(submit);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main().setVisible(true);
			}
		});
		back.setFont(new Font("Tahoma", Font.BOLD, 15));
		back.setBounds(39, 389, 89, 23);
		contentPane.add(back);
	}

}
