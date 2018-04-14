package main.java.bspq18_e4.GestionHotelera.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.java.bspq18_e4.GestionHotelera.server.assembler.Assemble;
import main.java.bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import main.java.bspq18_e4.GestionHotelera.server.dao.IHotelDAO;
import main.java.bspq18_e4.GestionHotelera.server.data.Hotel;
import main.java.bspq18_e4.GestionHotelera.server.data.User;
import main.java.bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class Service extends UnicastRemoteObject implements IService {

	private static final long serialVersionUID = 1L;
	private IHotelDAO dao;
	private Assemble ass;

	public Service() throws RemoteException {
		dao = new HotelDAO();
		ass = new Assemble();
	}
	
	public UserDTO SignIn(String email, String pass) throws RemoteException{
		User user = dao.getUser(email, pass);
		if(user == null) {
			return null;
		} else {
			return ass.user(user);
		}
	}
	
	public void register(UserDTO userDTO) throws RemoteException {
		dao.register(ass.userDTO(userDTO));
	}
	
	
}
