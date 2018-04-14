package main.java.bspq18_e4.GestionHotelera.server.dao;

import java.util.ArrayList;

import main.java.bspq18_e4.GestionHotelera.server.data.Hotel;
import main.java.bspq18_e4.GestionHotelera.server.data.Reservation;
import main.java.bspq18_e4.GestionHotelera.server.data.User;

public interface IHotelDAO {

	public void store(Object objeto);
	public void register(User user);
	public User getUser(String email, String pass);
	public void book(Reservation reservation);
	public ArrayList<Hotel> getHotels();
}
