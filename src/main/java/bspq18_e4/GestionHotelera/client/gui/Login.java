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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame frmSignIn;
	private JTextField tmail;
	private JPasswordField passw;
	private JButton register;
	private JLabel lblName;
	private static Controller ctrl;
	private ResourceBundle resourcebundle;
	private JComboBox<String> cmbLanguage;
	private JButton btnCambio;
	int cont=0;
	Locale currentLocale = null;
	public Login(Controller ctrl) {
		this.ctrl = ctrl;
		currentLocale = new Locale("en", "US");
		resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);
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
		
		
		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setBounds(10, 11, 94, 20);
		cmbLanguage.addItem(resourcebundle.getString("espanol"));
		cmbLanguage.addItem(resourcebundle.getString("ingles"));
		frmSignIn.getContentPane().add(cmbLanguage);
		cmbLanguage.setVisible(true);
				
		btnCambio = new JButton();
		btnCambio.setText(resourcebundle.getString("translate"));
		btnCambio.setBounds(10, 38, 94, 20);
		frmSignIn.getContentPane().add(btnCambio);
		btnCambio.setVisible(true);
		
		tmail = new JTextField();
		tmail.setBounds(170, 66, 86, 23);
		frmSignIn.getContentPane().add(tmail);
		tmail.setColumns(10);
		
		passw = new JPasswordField();
		passw.setBounds(170, 110, 86, 23);
		frmSignIn.getContentPane().add(passw);
		
		final JLabel lblEmail = new JLabel(resourcebundle.getString("email"));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(89, 69, 46, 14);
		frmSignIn.getContentPane().add(lblEmail);
		
		final JLabel lblPassword = new JLabel();
		lblPassword.setText(resourcebundle.getString("password"));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(75, 113, 71, 14);
		frmSignIn.getContentPane().add(lblPassword);
		
		final JButton login = new JButton(resourcebundle.getString("signin"));
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
		login.setBounds(60, 163, 104, 23);
		frmSignIn.getContentPane().add(login);
		
		register = new JButton();
		register.setText(resourcebundle.getString("register"));
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSignIn.dispose();
				Signup signup = new Signup(ctrl);
			}
		});
		register.setBounds(167, 163, 109, 23);
		frmSignIn.getContentPane().add(register);
		
		lblName = new JLabel();
		lblName.setText(resourcebundle.getString("name"));
		lblName.setBounds(114, 28, 175, 14);
		frmSignIn.getContentPane().add(lblName);
		frmSignIn.repaint();
		frmSignIn.revalidate();
		
		do {
					btnCambio.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							switch(cmbLanguage.getSelectedIndex()){
			
					        case 0:
					        	currentLocale = new Locale("es", "ES");
								resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);
								lblPassword.setText(resourcebundle.getString("password"));
								register.setText(resourcebundle.getString("register"));
								btnCambio.setText(resourcebundle.getString("translate"));
								frmSignIn.setTitle(resourcebundle.getString("title"));
								login.setText(resourcebundle.getString("signin"));
								lblEmail.setText(resourcebundle.getString("email"));
								lblName.setText(resourcebundle.getString("name"));
					            break;
					        case 1:
					        	currentLocale = new Locale("en", "US");
								resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);
								lblPassword.setText(resourcebundle.getString("password"));
								register.setText(resourcebundle.getString("register"));
								btnCambio.setText(resourcebundle.getString("translate"));
								frmSignIn.setTitle(resourcebundle.getString("title"));
								login.setText(resourcebundle.getString("signin"));
								lblEmail.setText(resourcebundle.getString("email"));
								lblName.setText(resourcebundle.getString("name"));
					            break;
					    }
						}
						});
					cont++;
					}while(cont!=100);
	}
}
