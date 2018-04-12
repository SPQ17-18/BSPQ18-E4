package bspq18_e4.GestionHotelera.server.data;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String pass;
	private String cc;
	private ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	
	public User(String email, String name, String pass, String cc, ArrayList<Reservation> reservations) {
		this.email = email;
		this.name = name;
		this.pass = pass;
		this.cc = cc;
		this.reservations = reservations;
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

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", pass=" + pass + ", cc=" + cc + ", reservations="
				+ reservations + "]";
	}


}
