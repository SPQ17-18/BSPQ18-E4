package bspq18_e4.GestionHotelera.server.dao;

import java.util.ArrayList;

import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.User;

public interface IHotelDAO {

	public void store(Object objeto);
	public void register(User user);
	public User getUser(String email, String pass);
	public void book(Reservation reservation);
	public ArrayList<Hotel> getHotels();
	public ArrayList<String> getCities();
	public ArrayList<Reservation> getReservationsByUser(User user);
}
