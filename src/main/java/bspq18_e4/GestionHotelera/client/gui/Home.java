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
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JLabel lbl;
	private JLabel label;

	public Home(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 387);
		frame.setTitle("Logged as " + userDTO.getName());
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
		citybox.setBounds(25, 260, 113, 20);
		frame.getContentPane().add(citybox);

		HotelDAO dao = new HotelDAO();
		ArrayList<String> cities = dao.getCities();
		citybox.addItem("All");
		for (int i = 0; i < cities.size(); i++) {
			citybox.addItem(cities.get(i));
		}

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(25, 229, 95, 23);
		frame.getContentPane().add(lblCity);

		JButton bsearch = new JButton("Search");
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataByCity(String.valueOf(citybox.getSelectedItem()));
			}
		});
		bsearch.setBounds(35, 303, 89, 23);
		frame.getContentPane().add(bsearch);

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
						if (hotel.getName().equals(name) && hotel.getCity().equals(city)
								&& hotel.getDir().equals(address) && hotel.getStars() == stars) {
							int id = hotel.getId();
							hotelSel = dao.geHotelById(id);
							if (!dao.getRooms(hotelSel).isEmpty()) {
								Rooms rooms = new Rooms(ctrl, userDTO, hotelSel);
							} else {
								lbl.setText("No available");
								label.setText("rooms");
							}
						}
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton myAccount = new JButton("My account");
		myAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyAccount acc = new MyAccount(ctrl, userDTO);
			}
		});
		myAccount.setBounds(243, 11, 113, 23);
		frame.getContentPane().add(myAccount);

		lbl = new JLabel("");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setForeground(Color.RED);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbl.setBounds(10, 104, 147, 66);
		frame.getContentPane().add(lbl);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(10, 137, 147, 66);
		frame.getContentPane().add(label);
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
