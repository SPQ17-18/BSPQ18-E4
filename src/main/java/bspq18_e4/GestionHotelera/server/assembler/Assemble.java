package bspq18_e4.GestionHotelera.server.assembler;

import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
import bspq18_e4.GestionHotelera.server.dto.ReservationDTO;
import bspq18_e4.GestionHotelera.server.dto.RoomDTO;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class Assemble {

	public UserDTO user(User user) {
		UserDTO userDTO = new UserDTO(user.getEmail(), user.getName(), user.getPass(), user.getCc());

		for (int i = 0; i < user.getReservations().size(); i++) {
			userDTO.addReservation(reservation(user.getReservations().get(i)));
		}
		return userDTO;
	}

	public User userDTO(UserDTO userDTO) {
		User user = new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPass(), userDTO.getCc());

		for (int i = 0; i < userDTO.getReservations().size(); i++) {
			user.addReservation(reservationDTO(userDTO.getReservations().get(i)));
		}
		return user;
	}

	public ReservationDTO reservation(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO(reservation.getId(), reservation.getArrival(),
				reservation.getDeparture(), user(reservation.getUser()), hotel(reservation.getHotel()));
		return reservationDTO;
	}

	public Reservation reservationDTO(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation(reservationDTO.getId(), reservationDTO.getArrival(),
				reservationDTO.getDeparture(), userDTO(reservationDTO.getUser()), hotelDTO(reservationDTO.getHotel()));
		return reservation;
	}

	public HotelDTO hotel(Hotel hotel) {
		HotelDTO hotelDTO = new HotelDTO(hotel.getId(), hotel.getName(), hotel.getCity(), hotel.getDir(), hotel.getStars());
		return hotelDTO;
	}
	
	public Hotel hotelDTO(HotelDTO hotelDTO) {
		Hotel hotel = new Hotel(hotelDTO.getId(), hotelDTO.getName(), hotelDTO.getCity(), hotelDTO.getDir(), hotelDTO.getStars());
		return hotel;
	}
	
	public RoomDTO room(Room room) {
		RoomDTO roomDTO = new RoomDTO(room.getNum(),room.getType(), room.getCapacity(), room.getPrice(), hotel(room.getHotel()), reservation(room.getReservation()));
		return roomDTO;
	}
	
	public Room roomDTO(RoomDTO roomDTO) {
		Room room = new Room(roomDTO.getNum(),roomDTO.getType(), roomDTO.getCapacity(), roomDTO.getPrice(), hotelDTO(roomDTO.getHotel()), reservationDTO(roomDTO.getReservation()));
		return room;
	}

}
