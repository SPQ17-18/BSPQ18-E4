package main.java.bspq18_e4.GestionHotelera.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import main.java.bspq18_e4.GestionHotelera.server.assembler.Assemble;
import main.java.bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import main.java.bspq18_e4.GestionHotelera.server.dao.IHotelDAO;

public class Service extends UnicastRemoteObject implements IService {

	private static final long serialVersionUID = 1L;
	private IHotelDAO dao;
	private Assemble ass;

	public Service() throws RemoteException {
		dao = new HotelDAO();
		ass = new Assemble();
	}
	
//	public UserDTO SignIn(String email, String pass) throws RemoteException{
//		User
//	}
//	

}
