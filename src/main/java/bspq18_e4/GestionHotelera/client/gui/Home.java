package bspq18_e4.GestionHotelera.client.gui;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Home extends JFrame{

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Controller ctrl;
	private UserDTO userDTO;	
	private HotelDAO dao;
	private JTable table;
	private JTable retable;

	public Home(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO=userDTO;
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
		lblListOfHotels.setBounds(210, 41, 130, 14);
		frame.getContentPane().add(lblListOfHotels);

		final JComboBox<String> citybox = new JComboBox<String>();
		citybox.setBounds(22, 91, 113, 20);
		frame.getContentPane().add(citybox);
		
		HotelDAO dao = new HotelDAO();
		ArrayList<String> cities = dao.getCities();
		for (int i = 0; i < cities.size(); i++) {
			citybox.addItem(cities.get(i));
		}
		
		final JPanel panel = new JPanel();
		panel.setBounds(162, 72, 327, 245);
		frame.getContentPane().add(panel);
		
		
		String titles[] = {"Name", "City", "Address", "Stars"};
		String info[][] = getMatrix();
		table = new JTable(info, titles);
		panel.add(table);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
//		DefaultTableModel model;
//		model = new DefaultTableModel();
//		table = new JTable();
//		table.setModel(model);
//		model.addColumn("Name");
//		model.addColumn("City");
//		model.addColumn("Address");
//		model.addColumn("Stars");
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.getTableHeader().setReorderingAllowed(false);
//		
//		HotelDAO dao = new HotelDAO();
//		dao.getHotels();
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCity.setBounds(22, 60, 95, 23);
		frame.getContentPane().add(lblCity);
		
		JLabel lblFirstDay = new JLabel("Arrival Day");
		lblFirstDay.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstDay.setBounds(22, 122, 113, 23);
		frame.getContentPane().add(lblFirstDay);
		
		JLabel lblNewLabel = new JLabel("Departure Day");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(22, 189, 130, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox arrivalbox = new JComboBox();
		arrivalbox.setBounds(22, 158, 113, 20);
		frame.getContentPane().add(arrivalbox);
		
		JComboBox departurebox = new JComboBox();
		departurebox.setBounds(22, 227, 113, 20);
		frame.getContentPane().add(departurebox);
		
		JButton bsearch = new JButton("Search");
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String city = String.valueOf(citybox.getSelectedItem());System.out.println(city);
				String x[][] = getMatrixByCity(city); 
				String titles[] = {"Name", "City", "Address", "Stars"};
				retable = new JTable(x, titles);
				panel.add(retable);
				retable.setEnabled(false);
				retable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			}
		});
		bsearch.setBounds(34, 294, 89, 23);
		frame.getContentPane().add(bsearch);
		frame.setVisible(true);
	}
	
	private String[][] getMatrix(){
		
		HotelDAO dao = new HotelDAO();
		
		ArrayList<Hotel> hotels = dao.getHotels();
		
		
		String info[][] = new String[hotels.size()][4];
		
		for (int i = 0; i < info.length; i++) {
			info[i][0] = hotels.get(i).getName();
			info[i][1] = hotels.get(i).getCity();
			info[i][2] = hotels.get(i).getDir();
			info[i][3] = String.valueOf(hotels.get(i).getStars());
		}
		
		return info;
	}
	
private String[][] getMatrixByCity(String city){
		
		HotelDAO dao = new HotelDAO();
		
		ArrayList<Hotel> hotels = dao.getHotelsByCity(city);
		
		
		String info[][] = new String[hotels.size()][4];
		
		for (int i = 0; i < info.length; i++) {
			info[i][0] = hotels.get(i).getName();
			info[i][1] = hotels.get(i).getCity();
			info[i][2] = hotels.get(i).getDir();
			info[i][3] = String.valueOf(hotels.get(i).getStars());
		}
		
		return info;
	}
}
