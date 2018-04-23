package bspq18_e4.GestionHotelera.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private int id;
	private Date arrival;
	private Date departure;
	private User user;
	private Hotel hotel;
	private ArrayList<Room> rooms;

	public Reservation(int id, Date arrival, Date departure, User user, Hotel hotel) {
		this.id = id;
		this.arrival = arrival;
		this.departure = departure;
		this.user = user;
		this.hotel = hotel;
		rooms = new ArrayList<Room>();
	}
	
	public Reservation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
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

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", arrival=" + arrival + ", departure=" + departure + ", user=" + user
				+ ", hotel=" + hotel + ", rooms=" + rooms + "]";
	}

}
