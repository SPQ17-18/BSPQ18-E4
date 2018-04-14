package main.java.bspq18_e4.GestionHotelera.server.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String pass;
	private String cc;
	private ArrayList<ReservationDTO> reservations;

	public UserDTO(String email, String name, String pass, String cc) {
		this.email = email;
		this.name = name;
		this.pass = pass;
		this.cc = cc;
		reservations = new ArrayList<ReservationDTO>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public ArrayList<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<ReservationDTO> reservations) {
		this.reservations = reservations;
	}

	public void addReservation(ReservationDTO reservation) {
		reservations.add(reservation);
	}
	public void removeReservation(ReservationDTO reservation) {
		reservations.remove(reservation);
	}

}
