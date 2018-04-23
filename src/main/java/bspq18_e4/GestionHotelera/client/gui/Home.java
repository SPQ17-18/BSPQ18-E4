package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame{

	private static final long serialVersionUID = 1L;


	private JFrame frame;

	
	private Controller ctrl;
	private UserDTO userDTO;
	

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Home window = new Home();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}	

	/**
	 * Create the application.
	 */
	public Home(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO=userDTO;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton logOut = new JButton("Log out");
		logOut.setBounds(420, 11, 89, 23);
		frame.getContentPane().add(logOut);
		
		JLabel lblListOfHotels = new JLabel("List of hotels");
		lblListOfHotels.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfHotels.setBounds(326, 73, 130, 14);
		frame.getContentPane().add(lblListOfHotels);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login(ctrl);
			}
		});
		btnExit.setBounds(420, 314, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(22, 91, 113, 20);
		frame.getContentPane().add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(326, 100, 130, 108);
		frame.getContentPane().add(panel);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(22, 60, 95, 23);
		frame.getContentPane().add(lblCity);
		
		JLabel lblFirstDay = new JLabel("Arrival Day");
		lblFirstDay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstDay.setBounds(22, 122, 113, 23);
		frame.getContentPane().add(lblFirstDay);
		
		JLabel lblNewLabel = new JLabel("Departure Day");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 189, 130, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(22, 158, 113, 20);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(22, 227, 113, 20);
		frame.getContentPane().add(comboBox_2);
		frame.setVisible(true);
	}
}
