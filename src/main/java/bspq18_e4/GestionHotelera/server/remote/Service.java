package main.java.bspq18_e4.GestionHotelera.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import main.java.bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import main.java.bspq18_e4.GestionHotelera.server.dao.IHotelDAO;

public class Service extends UnicastRemoteObject implements IService {

	private static final long serialVersionUID = 1L;
	private IHotelDAO dao;

	public Service() throws RemoteException {
		dao = new HotelDAO();
	}

}
