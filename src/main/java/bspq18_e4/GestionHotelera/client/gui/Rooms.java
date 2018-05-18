package bspq18_e4.GestionHotelera.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bspq18_e4.GestionHotelera.client.controller.Controller;
import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Rooms extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JTable table;
	private Controller ctrl;
	private UserDTO userDTO;
	private Hotel hotel;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	public Rooms(Controller ctrl, UserDTO userDTO, Hotel hotel) {
		this.ctrl = ctrl;
		this.userDTO = userDTO;
		this.hotel = hotel;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("Hotel name " + hotel.getName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 200);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setDefaultEditor(Object.class, null);

		JButton bbook = new JButton("Book");
		bbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelDAO dao = new HotelDAO();
				Assemble ass = new Assemble();
				int[] rowSelected = table.getSelectedRows();
				int[] number = new int[table.getSelectedRowCount()];
				String[] type = new String[table.getSelectedRowCount()];
				int[] capacity = new int[table.getSelectedRowCount()];
				double[] price = new double[table.getSelectedRowCount()];

				for (int i = 0; i < rowSelected.length; i++) {
					number[i] = (int) table.getValueAt(rowSelected[i], 0);
					type[i] = (String) table.getValueAt(rowSelected[i], 1);
					capacity[i] = (int) table.getValueAt(rowSelected[i], 2);
					price[i] = (double) table.getValueAt(rowSelected[i], 3);
				}

				Reservation reservation = new Reservation(0, null, null, ass.userDTO(userDTO), hotel);

				ArrayList<Room> rooms = new ArrayList<Room>();
				rooms = dao.getRooms(hotel);
				ArrayList<Room> roomSelected = new ArrayList<Room>();

				for (Room room : rooms) {
					for (int i = 0; i < rowSelected.length; i++) {
						if (room.getNum() == number[i] && room.getType().equals(type[i])
								&& room.getCapacity() == capacity[i] && room.getPrice() == price[i]) {
							roomSelected.add(room);
						}
					}
				}
				try {
					ctrl.book(reservation, roomSelected);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
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

		addData(hotel);

		frame.setVisible(true);
	}

	private void addData(Hotel hotel) {

		model = new DefaultTableModel();

		model.addColumn("Number");
		model.addColumn("Type");
		model.addColumn("Capacity");
		model.addColumn("Price");

		HotelDAO dao = new HotelDAO();

		List<Room> rooms = new ArrayList<Room>();
		rooms = dao.getRooms(hotel);

		for (Room room : rooms) {

			model.addRow(new Object[] { room.getNum(), room.getType(), room.getCapacity(), room.getPrice() });
		}
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setVisible(true);
	}

}
