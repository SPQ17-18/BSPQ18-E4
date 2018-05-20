package bspq18_e4.GestionHotelera.server.data;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class ReservationRooms implements Serializable {

	private static final long serialVersionUID = 1L;
	private Room room;
	private Reservation reservation;
	
	public ReservationRooms(Room room, Reservation reservation) {
		this.room = room;
		this.reservation = reservation;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}	
}
