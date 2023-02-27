package project.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.frame.AdminLogIn;
import project.frame.BuyerLogIn;
import project.frame.SellerLogIn;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField roleselection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Automated Auction System ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User, What's Your Role?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(216, 91, 276, 48);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("1.  Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(84, 231, 110, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("2.  Seller");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(279, 231, 102, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("3.  Buyer");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(476, 231, 81, 35);
		contentPane.add(lblNewLabel_3);
		
		roleselection = new JTextField();
		roleselection.setBounds(271, 347, 86, 20);
		contentPane.add(roleselection);
		roleselection.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roleselection.getText().equals("1")) {
				
					new AdminLogIn().setVisible(true);
					
				}
				else if(roleselection.getText().equals("2")) {
					 new SellerLogIn().setVisible(true);
				}
				else if(roleselection.getText().equals("3")) {
					new BuyerLogIn().setVisible(true);
				}
			}
		});
		submit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		submit.setBounds(271, 396, 89, 23);
		contentPane.add(submit);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Selection Number");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(242, 303, 168, 14);
		contentPane.add(lblNewLabel_4);
	}

}
