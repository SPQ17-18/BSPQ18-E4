package bspq18_e4.GestionHotelera.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;
	private HotelDAO dao;
	private JTable retable;
	private DefaultTableModel model2;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	public Home(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 387);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton logOut = new JButton("Log out");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login(ctrl);
			}
		});
		logOut.setBounds(420, 11, 89, 23);
		frame.getContentPane().add(logOut);

		JLabel lblListOfHotels = new JLabel("List of hotels");
		lblListOfHotels.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfHotels.setBounds(91, 13, 130, 14);
		frame.getContentPane().add(lblListOfHotels);

		final JComboBox<String> citybox = new JComboBox<String>();
		citybox.setBounds(22, 77, 113, 20);
		frame.getContentPane().add(citybox);

		HotelDAO dao = new HotelDAO();
		ArrayList<String> cities = dao.getCities();
		citybox.addItem("All");
		for (int i = 0; i < cities.size(); i++) {
			citybox.addItem(cities.get(i));
		}

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(22, 46, 95, 23);
		frame.getContentPane().add(lblCity);

		JLabel lblFirstDay = new JLabel("Arrival Day");
		lblFirstDay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstDay.setBounds(22, 118, 113, 23);
		frame.getContentPane().add(lblFirstDay);

		JLabel lblNewLabel = new JLabel("Departure Day");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 191, 130, 27);
		frame.getContentPane().add(lblNewLabel);

		JButton bsearch = new JButton("Search");
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataByCity(String.valueOf(citybox.getSelectedItem()));
			}
		});
		bsearch.setBounds(34, 280, 89, 23);
		frame.getContentPane().add(bsearch);

		JDateChooser arrivalChooser = new JDateChooser();
		arrivalChooser.setBounds(22, 152, 113, 20);
		frame.getContentPane().add(arrivalChooser);

		JDateChooser departureChooser = new JDateChooser();
		departureChooser.setBounds(22, 229, 113, 20);
		frame.getContentPane().add(departureChooser);

		JButton bBook = new JButton("Book");
		bBook.setBounds(34, 314, 89, 23);
		frame.getContentPane().add(bBook);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 80, 342, 257);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String valorSeleccionado = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				}
			}
		});
		scrollPane.setViewportView(table);
		frame.setVisible(true);

		addData();

	}

	private void addData() {
		
		model = new DefaultTableModel();

		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("Address");
		model.addColumn("Stars");

		HotelDAO dao = new HotelDAO();

		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels = dao.getHotels();

		for (Hotel hotel : hotels) {

			model.addRow(new Object[] { hotel.getName(), hotel.getCity(), hotel.getDir(), hotel.getStars() });
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
	}

	private void addDataByCity(String city) {
		// model = new DefaultTableModel(info, titles);
		model = new DefaultTableModel();

		// Creamos nombres de las columnas de la tabla:
		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("Address");
		model.addColumn("Stars");

		HotelDAO dao = new HotelDAO();

		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels = dao.getHotelsByCity(city);

		for (Hotel hotel : hotels) {

			// Ahora añadimos los valores al modelo:
			model.addRow(new Object[] { hotel.getName(), hotel.getCity(), hotel.getDir(), hotel.getStars() });
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
