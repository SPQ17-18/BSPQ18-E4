package bspq18_e4.GestionHotelera.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.dao.IHotelDAO;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class Service extends UnicastRemoteObject implements IService {

	private static final long serialVersionUID = 1L;
	private IHotelDAO dao;
	private Assemble ass;

	public Service() throws RemoteException {
		dao = new HotelDAO();
		ass = new Assemble();
	}
	
	public UserDTO signIn(String email, String pass) throws RemoteException{
		User user = dao.getUser(email, pass);
		if(user == null) {
			return null;
		} else {
			return ass.user(user);
		}
	}
	
	public void register(UserDTO userDto) throws RemoteException {
		dao.register(ass.userDTO(userDto));
	}
	
	public boolean isRegistered(UserDTO userDto) throws RemoteException {
		User user = null;
		try {
			user = dao.getUser(userDto.getEmail(), userDto.getPass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (user!=null) {
			return true;
		} else {
			user = new User(userDto.getEmail(), userDto.getName(), userDto.getPass(), userDto.getCc());
			return false;
		}
	}
	
	public void book(Reservation reservation, Room room) throws RemoteException {
		dao.book(reservation, room);
	}
}
