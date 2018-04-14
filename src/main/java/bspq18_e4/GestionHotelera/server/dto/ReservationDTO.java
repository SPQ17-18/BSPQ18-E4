package main.java.bspq18_e4.GestionHotelera.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Date arrival;
	private Date departure;
	private UserDTO user;
	private HotelDTO hotel;
	private ArrayList<RoomDTO> rooms;

	public ReservationDTO(int id, Date arrival, Date departure, UserDTO user, HotelDTO hotel) {
		this.id = id;
		this.arrival = arrival;
		this.departure = departure;
		this.user = user;
		this.hotel = hotel;
		rooms = new ArrayList<RoomDTO>();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public ArrayList<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<RoomDTO> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(RoomDTO room) {
		rooms.add(room);
	}
	public void removeReserva(RoomDTO room) {
		rooms.remove(room);
	}
	
}
