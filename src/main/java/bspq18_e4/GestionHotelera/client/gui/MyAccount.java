package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MyAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private ResourceBundle resourcebundle;
	private JComboBox<String> cmbLanguage;
	private JButton btnCambio;
	int cont=0;
	Locale currentLocale = null;
	
	public MyAccount(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		currentLocale = new Locale("en", "US");
		resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);
		initialize();
	}
	
	

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("My account     Logged as "+ userDTO.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.getContentPane().setLayout(null);
		
		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setBounds(335, 47, 94, 20);
		cmbLanguage.addItem(resourcebundle.getString("espanol"));
		cmbLanguage.addItem(resourcebundle.getString("ingles"));
		frame.getContentPane().add(cmbLanguage);
		cmbLanguage.setVisible(true);
		
		btnCambio = new JButton();
		btnCambio.setText(resourcebundle.getString("translate"));
		btnCambio.setBounds(335, 78, 94, 14);
		frame.getContentPane().add(btnCambio);
		btnCambio.setVisible(true);
		
		final JLabel lblEmail = new JLabel(resourcebundle.getString("email"));
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(25, 11, 46, 14);
		frame.getContentPane().add(lblEmail);
		
		final JLabel lblName = new JLabel(resourcebundle.getString("name"));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(25, 30, 46, 14);
		frame.getContentPane().add(lblName);
		
		final JLabel lblCreditCard = new JLabel(resourcebundle.getString("card"));
		lblCreditCard.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditCard.setBounds(25, 50, 72, 14);
		frame.getContentPane().add(lblCreditCard);
		
		JLabel tmail = new JLabel(userDTO.getEmail());
		tmail.setBounds(108, 11, 102, 14);
		frame.getContentPane().add(tmail);
		
		JLabel tname = new JLabel(userDTO.getName());
		tname.setBounds(108, 30, 102, 14);
		frame.getContentPane().add(tname);
		
		JLabel tcc = new JLabel(userDTO.getCc());
		tcc.setBounds(108, 50, 102, 14);
		frame.getContentPane().add(tcc);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 414, 147);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		final JButton bclose = new JButton(resourcebundle.getString("close"));
		bclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		bclose.setBounds(335, 11, 89, 23);
		frame.getContentPane().add(bclose);
		addDataByUser(userDTO);
		
		
		do {
			btnCambio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					switch(cmbLanguage.getSelectedIndex()){

			        case 0:
			        	currentLocale = new Locale("es", "ES");
						resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);				
						btnCambio.setText(resourcebundle.getString("translate"));					
						lblEmail.setText(resourcebundle.getString("email"));
						lblName.setText(resourcebundle.getString("name"));
						lblCreditCard.setText(resourcebundle.getString("card"));
						bclose.setText(resourcebundle.getString("close"));
			            break;
			        case 1:
			        	currentLocale = new Locale("en", "US");
						resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);						
						btnCambio.setText(resourcebundle.getString("translate"));				
						lblEmail.setText(resourcebundle.getString("email"));
						lblName.setText(resourcebundle.getString("name"));
						lblCreditCard.setText(resourcebundle.getString("card"));
						bclose.setText(resourcebundle.getString("close"));
			            break;
			    }
				}
				});
			cont++;
			}while(cont!=100);
	}
	
	private void addDataByUser(UserDTO userDTO) {

		model = new DefaultTableModel();

		model.addColumn("Arrival");
		model.addColumn("Departure");
		model.addColumn("Hotel");

		HotelDAO dao = new HotelDAO();
		Assemble ass = new Assemble();

		List<Reservation> reservations = new ArrayList<Reservation>();
		reservations = dao.getReservationsByUser(ass.userDTO(userDTO));
		for (Reservation reservation : reservations) {

			model.addRow(new Object[] { reservation.getArrival(), reservation.getDeparture(), reservation.getHotel().getName() });
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
