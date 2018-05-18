package bspq18_e4.GestionHotelera.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
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
import java.util.Locale;
import java.util.ResourceBundle;
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
	private ResourceBundle resourcebundle;
	private JComboBox<String> cmbLanguage;
	private JButton btnCambio;
	int cont=0;
	Locale currentLocale = null;

	public Home(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		currentLocale = new Locale("en", "US");
		resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 387);
		frame.setTitle("Logged as "+ userDTO.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JButton logOut = new JButton(resourcebundle.getString("logout"));
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login(ctrl);
			}
		});
		logOut.setBounds(420, 11, 89, 23);
		frame.getContentPane().add(logOut);

		cmbLanguage = new JComboBox<String>();
		cmbLanguage.setBounds(287, 12, 94, 20);
		cmbLanguage.addItem(resourcebundle.getString("espanol"));
		cmbLanguage.addItem(resourcebundle.getString("ingles"));
		frame.getContentPane().add(cmbLanguage);
		cmbLanguage.setVisible(true);
		
		btnCambio = new JButton();
		btnCambio.setText(resourcebundle.getString("translate"));
		btnCambio.setBounds(287, 43, 94, 14);
		frame.getContentPane().add(btnCambio);
		
		btnCambio.setVisible(true);
		final JLabel lblListOfHotels = new JLabel(resourcebundle.getString("lista"));
		lblListOfHotels.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfHotels.setBounds(22, 13, 130, 14);
		frame.getContentPane().add(lblListOfHotels);

		final JComboBox<String> citybox = new JComboBox<String>();
		citybox.setBounds(22, 77, 113, 20);
		frame.getContentPane().add(citybox);

		HotelDAO dao = new HotelDAO();
		ArrayList<String> cities = dao.getCities();
		citybox.addItem(resourcebundle.getString("all"));
		for (int i = 0; i < cities.size(); i++) {
			citybox.addItem(cities.get(i));
		}

		final JLabel lblCity = new JLabel(resourcebundle.getString("city"));
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(22, 46, 95, 23);
		frame.getContentPane().add(lblCity);

		final JLabel lblFirstDay = new JLabel(resourcebundle.getString("arrive"));
		lblFirstDay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstDay.setBounds(22, 118, 113, 23);
		frame.getContentPane().add(lblFirstDay);

		final JLabel lblNewLabel = new JLabel(resourcebundle.getString("back"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 191, 130, 27);
		frame.getContentPane().add(lblNewLabel);

		final JButton bsearch = new JButton(resourcebundle.getString("search"));
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

		final JButton bBook = new JButton(resourcebundle.getString("book"));
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
				ArrayList<Hotel> hotels = new ArrayList<>();
				Hotel hotelSel = null;
				HotelDAO dao = new HotelDAO();
				hotels = dao.getHotels();
				if (e.getClickCount() == 2) {
					int rowSelected = table.getSelectedRow();
					String name = (String) table.getValueAt(rowSelected, 0);
					String city = (String) table.getValueAt(rowSelected, 1);
					String address = (String) table.getValueAt(rowSelected, 2);
					int stars = (int) table.getValueAt(rowSelected, 3);
					for (Hotel hotel : hotels) {
						if (hotel.getName().equals(name)&&hotel.getCity().equals(city)&&hotel.getDir().equals(address)&&hotel.getStars()==stars) {
							int id =hotel.getId();
							hotelSel = dao.geHotelById(id);
							Rooms rooms = new Rooms(ctrl, userDTO, hotelSel);
						}
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		
		final JButton myAccount = new JButton(resourcebundle.getString("account"));
		myAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyAccount acc = new MyAccount(ctrl, userDTO);
			}
		});
		myAccount.setBounds(144, 11, 113, 23);
		frame.getContentPane().add(myAccount);
		frame.setVisible(true);

		addData();
		do {
			btnCambio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					switch(cmbLanguage.getSelectedIndex()){

			        case 0:
			        	currentLocale = new Locale("es", "ES");
						resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);					
						btnCambio.setText(resourcebundle.getString("translate"));
						lblListOfHotels.setText(resourcebundle.getString("lista"));
						lblCity.setText(resourcebundle.getString("city"));
						lblFirstDay.setText(resourcebundle.getString("arrive"));
						lblNewLabel.setText(resourcebundle.getString("back"));
						bsearch.setText(resourcebundle.getString("search"));
						bBook.setText(resourcebundle.getString("book"));
						myAccount.setText(resourcebundle.getString("account"));
						logOut.setText(resourcebundle.getString("logout"));
			            break;
			        case 1:
			        	currentLocale = new Locale("en", "US");
						resourcebundle = ResourceBundle.getBundle("lang/translations", currentLocale);						
						btnCambio.setText(resourcebundle.getString("translate"));
						lblListOfHotels.setText(resourcebundle.getString("lista"));
						lblCity.setText(resourcebundle.getString("city"));
						lblFirstDay.setText(resourcebundle.getString("arrive"));
						lblNewLabel.setText(resourcebundle.getString("back"));
						bsearch.setText(resourcebundle.getString("search"));
						bBook.setText(resourcebundle.getString("book"));
						myAccount.setText(resourcebundle.getString("account"));
						logOut.setText(resourcebundle.getString("logout"));
			            break;
			    }
				}
				});
			cont++;
			}while(cont!=100);
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

		model = new DefaultTableModel();

		model.addColumn("Name");
		model.addColumn("City");
		model.addColumn("Address");
		model.addColumn("Stars");

		HotelDAO dao = new HotelDAO();

		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels = dao.getHotelsByCity(city);

		for (Hotel hotel : hotels) {

			model.addRow(new Object[] { hotel.getName(), hotel.getCity(), hotel.getDir(), hotel.getStars() });
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
	}
}
