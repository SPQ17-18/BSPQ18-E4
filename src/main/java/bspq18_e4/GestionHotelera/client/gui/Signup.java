package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frmSignUp;
	private JTextField tmail;
	private JTextField tuser;
	private JTextField tcc;
	private JPasswordField tpass;
	private Controller ctrl;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Signup window = new Signup();
//					window.frmSignUp.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Signup(Controller ctrl) {
		this.ctrl = ctrl;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignUp = new JFrame();
		frmSignUp.setTitle("Sign up");
		frmSignUp.setBounds(450, 350, 341, 300);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel mail = new JLabel("Email");
		mail.setHorizontalAlignment(SwingConstants.CENTER);
		mail.setBounds(58, 70, 70, 14);
		frmSignUp.getContentPane().add(mail);
		
		JLabel user = new JLabel("Username");
		user.setHorizontalAlignment(SwingConstants.CENTER);
		user.setBounds(58, 100, 70, 14);
		frmSignUp.getContentPane().add(user);
		
		JLabel pass = new JLabel("Password");
		pass.setHorizontalAlignment(SwingConstants.CENTER);
		pass.setBounds(58, 133, 70, 14);
		frmSignUp.getContentPane().add(pass);
		
		JLabel cc = new JLabel("Credit Card");
		cc.setHorizontalAlignment(SwingConstants.CENTER);
		cc.setBounds(58, 165, 70, 14);
		frmSignUp.getContentPane().add(cc);
		
		tmail = new JTextField();
		tmail.setBounds(156, 67, 101, 20);
		frmSignUp.getContentPane().add(tmail);
		tmail.setColumns(10);
		
		tuser = new JTextField();
		tuser.setBounds(156, 97, 101, 20);
		frmSignUp.getContentPane().add(tuser);
		tuser.setColumns(10);
		
		tcc = new JTextField();
		tcc.setBounds(156, 162, 101, 20);
		frmSignUp.getContentPane().add(tcc);
		tcc.setColumns(10);
		
		tpass = new JPasswordField();
		tpass.setBounds(156, 130, 101, 20);
		frmSignUp.getContentPane().add(tpass);
		
		JButton bsignup = new JButton("Sign up");
		bsignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pass = tpass.getPassword();
				UserDTO user = new UserDTO(tmail.getText(), tuser.getText(), String.valueOf(pass), tcc.getText());
				try {
					ctrl.register(user);
					System.out.println(user.toString());
				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("Error: "+e2);
				}
				frmSignUp.dispose();
				new Login(ctrl);
			}
		});
		bsignup.setBounds(71, 216, 89, 23);
		frmSignUp.getContentPane().add(bsignup);
		
		JButton bcancel = new JButton("Cancel");
		bcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignUp.dispose();
				new Login(ctrl);
			}
		});
		bcancel.setBounds(169, 216, 89, 23);
		frmSignUp.getContentPane().add(bcancel);
		frmSignUp.setVisible(true);
	}
}
