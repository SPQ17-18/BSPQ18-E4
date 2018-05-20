package bspq18_e4.GestionHotelera.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;
import bspq18_e4.GestionHotelera.server.dto.RoomDTO;
import bspq18_e4.GestionHotelera.server.dto.ReservationDTO;

public class AssembleTest {
	
	private static Assemble ass;
	private static User user;
	private static UserDTO userDTO;
	private static Hotel hotel;
	private static HotelDTO hotelDTO;
	private static Room room;
	private static RoomDTO roomDTO;
	private static Reservation reservation;
	private static ReservationDTO reservationDTO;	

	@Before
	public void setUp() {
		ass = new Assemble();
		user = new User("mail@gmail.com", "name", "pass", "1234");
		hotel = new Hotel(1, "Carton", "Bilbao", "Moyua 33", 5);
		Date arrival = new Date(1234);
		Date departure = new Date(4321);
		reservation = new Reservation(1, arrival, departure, user, hotel);
		room = new Room(1, "Familiar", 5, 80, hotel, reservation);
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
	@Test
	public void testRoom() {
		roomDTO = ass.room(room);
		room = ass.roomDTO(roomDTO);
		assertEquals(room.getCapacity(),roomDTO.getCapacity());
		assertEquals(room.getNum(),roomDTO.getNum());
		assertEquals(room.getType(), roomDTO.getType());
	}
	@Test
	public void testRoomDTO() {
		roomDTO = ass.room(room);
		assertEquals(roomDTO.getCapacity(),room.getCapacity());
		assertEquals(roomDTO.getNum(),room.getNum());
		assertEquals(roomDTO.getType(),room.getType());
	}
	@Test
	public void testReservation() {
		reservationDTO = ass.reservation(reservation);
		reservation = ass.reservationDTO(reservationDTO);
		assertEquals(reservation.getId(), reservationDTO.getId());
		assertEquals(reservation.getArrival(), reservationDTO.getArrival());
		assertEquals(reservation.getDeparture(), reservationDTO.getDeparture());
	}
	
	@Test
	public void testReservationDTO() {
		
		reservationDTO = ass.reservation(reservation);
		assertEquals(reservationDTO.getId(),reservation.getId());
		assertEquals(reservationDTO.getArrival(),reservation.getArrival() );
		assertEquals(reservationDTO.getDeparture(),reservation.getDeparture());
	}
	
}
