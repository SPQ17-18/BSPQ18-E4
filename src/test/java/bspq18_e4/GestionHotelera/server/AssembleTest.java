package bspq18_e4.GestionHotelera.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class AssembleTest {

	private static Assemble ass;
	private static User user;
	private static UserDTO userDTO;
	private static Hotel hotel;
	private static HotelDTO hotelDTO;
	
	@Before
	public void setUp() {
		ass = new Assemble();
		user = new User("mail@gmail.com", "name", "pass", "1234");
		hotel = new Hotel(1, "Carton", "Bilbao", "Moyua 33", 5);
	}
	
	@Test
	public void testUser() {
		userDTO = ass.user(user);
		user = ass.userDTO(userDTO);
		assertEquals(user.getEmail(), userDTO.getEmail());
		assertEquals(user.getName(), userDTO.getName());
		assertEquals(user.getPass(), userDTO.getPass());
		assertEquals(user.getCc(), userDTO.getCc());
	}
	
	@Test
	public void testUserDTO() {
		userDTO = ass.user(user);
		assertEquals(userDTO.getEmail(), user.getEmail());
		assertNotEquals(userDTO.getEmail(), user.getName());
		assertEquals(userDTO.getName(), user.getName());
		assertEquals(userDTO.getPass(), user.getPass());
		assertEquals(userDTO.getCc(), user.getCc());
	}
	
	@Test
	public void testHotel() {
		hotelDTO = ass.hotel(hotel);
		hotel = ass.hotelDTO(hotelDTO);
		assertEquals(hotel.getName(), hotelDTO.getName());
		assertEquals(hotel.getDir(), hotelDTO.getDir());
		assertEquals(hotel.getCity(), hotelDTO.getCity());
		assertEquals(hotel.getStars(), hotelDTO.getStars());
	}
	
	@Test
	public void testHotelDTO() {
		hotelDTO = ass.hotel(hotel);
		assertEquals(hotelDTO.getName(), hotel.getName());
		assertEquals(hotelDTO.getDir(), hotel.getDir());
		assertEquals(hotelDTO.getCity(), hotel.getCity());
		assertEquals(hotelDTO.getStars(), hotel.getStars());
	}
}
