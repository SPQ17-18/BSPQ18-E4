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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

public class Rooms extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JTable table;
	private Controller ctrl;
	private UserDTO userDTO;
	private Hotel hotel;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private Date a;
	private JDateChooser darrival;
	private JDateChooser ddeparture;

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
		scrollPane.setBounds(10, 11, 414, 174);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setDefaultEditor(Object.class, null);

		JButton bbook = new JButton("Book");
		bbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelDAO dao = new HotelDAO();
				Assemble ass = new Assemble();
				// DateFormat df = new SimpleDateFormat("yy-MM-dd");
				// System.out.println(df.format(darrival.getDate()));
				int[] rowSelected = table.getSelectedRows();
				int[] number = new int[table.getSelectedRowCount()];
				String[] type = new String[table.getSelectedRowCount()];
				int[] capacity = new int[table.getSelectedRowCount()];
				double[] price = new double[table.getSelectedRowCount()];
				if (table.getSelectedRowCount() > 0) {
					if (!(darrival.getDate()==null) && !(ddeparture.getDate()==null)) {
						if (darrival.getDate().before(ddeparture.getDate())) {
							for (int i = 0; i < rowSelected.length; i++) {
								number[i] = (int) table.getValueAt(rowSelected[i], 0);
								type[i] = (String) table.getValueAt(rowSelected[i], 1);
								capacity[i] = (int) table.getValueAt(rowSelected[i], 2);
								price[i] = (double) table.getValueAt(rowSelected[i], 3);
							}

							Reservation reservation = new Reservation(0, darrival.getDate(), ddeparture.getDate(),
									ass.userDTO(userDTO), hotel);

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
						} else {
							JOptionPane.showMessageDialog(frame, "Departure date must be after arrival!", "Error 102",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Select arrival and departure dates!", "Error 325",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Select at least one room!", "Error 234",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bbook.setBounds(335, 198, 89, 23);
		frame.getContentPane().add(bbook);

		JButton bcancel = new JButton("Cancel");
		bcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		bcancel.setBounds(335, 228, 89, 23);
		frame.getContentPane().add(bcancel);

		JLabel labelarrival = new JLabel("Arrival Day");
		labelarrival.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelarrival.setBounds(20, 196, 113, 23);
		frame.getContentPane().add(labelarrival);

		JLabel labeldeparture = new JLabel("Departure Day");
		labeldeparture.setFont(new Font("Tahoma", Font.BOLD, 16));
		labeldeparture.setBounds(168, 193, 130, 27);
		frame.getContentPane().add(labeldeparture);

		darrival = new JDateChooser();
		darrival.setBounds(20, 230, 113, 20);
		frame.getContentPane().add(darrival);

		ddeparture = new JDateChooser();
		ddeparture.setBounds(168, 231, 113, 20);
		frame.getContentPane().add(ddeparture);

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
