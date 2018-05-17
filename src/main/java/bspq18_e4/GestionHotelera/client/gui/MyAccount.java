package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

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

public class MyAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;

	
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 80, 342, 257);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		addDataByUser(userDTO);
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
