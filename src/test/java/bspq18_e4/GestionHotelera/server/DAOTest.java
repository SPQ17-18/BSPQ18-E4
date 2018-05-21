package bspq18_e4.GestionHotelera.server;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.databene.contiperf.PerfTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;

import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.dao.IHotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.logger.ErrorLogger;
import javafx.beans.binding.When;

//@RunWith(MockitoJUnitRunner.class)  
public class DAOTest {

	private static User user;
	private static Reservation reservation;
	private static Hotel hotel;
	private static Room room;
	private static HotelDAO dao;
	private ErrorLogger logger;
	
//	@Mock
	IHotelDAO hotelDao;
	
	@Before
	public void setUp() { 
		user = new User("mail@gmail.com", "name", "pass", "1234");
		hotel = new Hotel(1, "Carton", "Bilbao", "Moyua 33", 5);	
		room = new Room(201, "Familiar", 5, 80, hotel, reservation);
		Date arrival = new Date(1234);
		Date departure = new Date(4321);
		reservation = new Reservation(1, arrival, departure, user, hotel);
	}
	
	@Test
	public void testRegister() {
		dao = new HotelDAO(logger);
		dao.register(user);
		User myUser = dao.getUser(user.getEmail(), user.getPass());
		//assertEquals("mail@gmail.com", myUser.getEmail());
	}
	
	@Test
	@PerfTest(invocations = 20)
	public void testHotel() {
		dao = new HotelDAO(logger);
		dao.store(hotel);
		Hotel myHotel = dao.geHotelById(hotel.getId());
		assertEquals("Bilbao", myHotel.getCity());
		assertEquals("Bilbao", reservation.getHotel().getCity());
	}
	
	@Test
	@PerfTest(duration=3000)
	public void testRoom() {
		dao = new HotelDAO(logger);
		room.setHotel(hotel);
		assertEquals(hotel.getName(), room.getHotel().getName());
	}
	
	
	@Test
	public void testReservation() {
		dao = new HotelDAO(logger);
		assertEquals(1, reservation.getId());
		assertEquals("mail@gmail.com", reservation.getUser().getEmail());
		assertEquals(user.getEmail(), reservation.getUser().getEmail());
		assertEquals("Carton", reservation.getHotel().getName());
	}
	
}
