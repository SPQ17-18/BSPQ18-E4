package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Rooms extends JFrame implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JTable table;
	private Controller ctrl;
	private UserDTO userDTO;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	public Rooms(Controller ctrl, UserDTO userDTO) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 216, 434, -215);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JButton bbook = new JButton("Book");
		bbook.setBounds(86, 228, 89, 23);
		frame.getContentPane().add(bbook);
		
		JButton bcancel = new JButton("Cancel");
		bcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		bcancel.setBounds(243, 228, 89, 23);
		frame.getContentPane().add(bcancel);
		frame.setVisible(true);
		addData();
	}
	
private void addData() {
		
		model = new DefaultTableModel();

		model.addColumn("Number");
		model.addColumn("Type");
		model.addColumn("Capacity");
		model.addColumn("Price");

		HotelDAO dao = new HotelDAO();

		List<Room> rooms = new ArrayList<Room>();
		rooms = dao.getRooms();

		for (Room room : rooms) {

			model.addRow(new Object[] { room.getNum(), room.getType(), room.getCapacity(), room.getPrice() });
			System.out.println(room.getNum()+ room.getType()+ room.getCapacity()+ room.getPrice());
		}

		table.setModel(model);
		scrollPane.setViewportView(table);
	}

}
