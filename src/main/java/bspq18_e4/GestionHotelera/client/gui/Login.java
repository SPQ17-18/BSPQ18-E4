package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frmSignIn;
	private JTextField tmail;
	private JPasswordField passw;
	private JButton register;
	private JLabel lblName;
	private static Controller ctrl;

	public Login(Controller ctrl) {
		this.ctrl = ctrl;
		initialize();
	}

	private void initialize() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();		
			}
		frmSignIn = new JFrame();
		frmSignIn.setTitle("Sign in");
		frmSignIn.setBounds(450, 350, 347, 246);
		frmSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignIn.getContentPane().setLayout(null);
		frmSignIn.setVisible(true);
		
		tmail = new JTextField();
		tmail.setBounds(170, 66, 86, 23);
		frmSignIn.getContentPane().add(tmail);
		tmail.setColumns(10);
		
		passw = new JPasswordField();
		passw.setBounds(170, 110, 86, 23);
		frmSignIn.getContentPane().add(passw);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(89, 69, 46, 14);
		frmSignIn.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(75, 113, 71, 14);
		frmSignIn.getContentPane().add(lblPassword);
		
		JButton login = new JButton("Sign in");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char[] pass = passw.getPassword();
				if(!tmail.getText().trim().equals("") && !String.valueOf(pass).trim().equals("")) {
					try {
						try {
							 UserDTO user = ctrl.signIn(tmail.getText(), String.valueOf(pass));
							if (user!=null) {
								JOptionPane.showMessageDialog(frmSignIn, "Welcome "+user.getName()+"!");
								frmSignIn.dispose();
								Home home = new Home(ctrl, user);
								//home.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(frmSignIn, "Incorrect credentials!", "Error 509", JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frmSignIn, "Write something!", "Error 507", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		login.setBounds(75, 163, 89, 23);
		frmSignIn.getContentPane().add(login);
		
		register = new JButton("Sign up");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignIn.dispose();
				Signup signup = new Signup(ctrl);
			}
		});
		register.setBounds(167, 163, 89, 23);
		frmSignIn.getContentPane().add(register);
		
		lblName = new JLabel("NAME");
		lblName.setBounds(146, 26, 51, 14);
		frmSignIn.getContentPane().add(lblName);
		frmSignIn.repaint();
		frmSignIn.revalidate();
	}
}
