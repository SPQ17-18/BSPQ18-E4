package bspq18_e4.GestionHotelera.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;

public class DataTest {

	private static User user;
	private static Reservation reservation;
	private static Hotel hotel;
	private static Room room;
	
	@Before
	public void setUp() { 
		hotel = new Hotel(1, "Melia", null, "Deusto 33", 4);
		user = new User("example@mail.com", "example", "1234", "12345");
		Date arrival = new Date(1234);
		Date departure = new Date(4321);
		reservation = new Reservation(1, arrival, departure, user, hotel);
		room = new Room(1, "Familiar", 5, 80, hotel, reservation);
	}
	
	@Test
	public void hotelTest() {
		String hotelCity = "Bilbao";
		int hotelStars = 5;
		String hotelDir = "Deusto 55";
		hotel.setCity(hotelCity);
		assertEquals(hotelCity, hotel.getCity());
		assertEquals("Melia", hotel.getName());
		assertEquals(1, hotel.getId());
		assertEquals("Deusto 33", hotel.getDir());
		hotel.setDir(hotelDir);
		assertEquals("Deusto 55", hotel.getDir());
		assertEquals(4, hotel.getStars());
		hotel.setStars(hotelStars);
		assertNotEquals(4, hotel.getStars());
	}
	
}
