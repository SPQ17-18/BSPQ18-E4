package main.java.bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import main.java.bspq18_e4.GestionHotelera.client.controller.Controller;
import main.java.bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Home extends JFrame{

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
		
		JPanel panel = new JPanel();
		panel.setBounds(355, 178, 154, 159);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
