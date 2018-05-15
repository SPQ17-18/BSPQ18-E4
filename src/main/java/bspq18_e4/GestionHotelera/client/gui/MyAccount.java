package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;

	
	public MyAccount(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		initialize();
	}
	
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("My account     Logged as "+ userDTO.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(51, 30, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(51, 55, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblCreditCard = new JLabel("Credit Card");
		lblCreditCard.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditCard.setBounds(51, 78, 72, 14);
		frame.getContentPane().add(lblCreditCard);
		
		JLabel tmail = new JLabel(userDTO.getEmail());
		tmail.setBounds(134, 30, 102, 14);
		frame.getContentPane().add(tmail);
		
		JLabel tname = new JLabel(userDTO.getName());
		tname.setBounds(134, 55, 102, 14);
		frame.getContentPane().add(tname);
		
		JLabel tcc = new JLabel(userDTO.getCc());
		tcc.setBounds(134, 80, 102, 14);
		frame.getContentPane().add(tcc);
	}
}
