package bspq18_e4.GestionHotelera.client.controller;

import java.rmi.RemoteException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import bspq18_e4.GestionHotelera.client.gui.Login;
import bspq18_e4.GestionHotelera.client.remote.ServiceLocator;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class Controller {

	private ServiceLocator sl;
	
	public Controller(String[] args) throws RemoteException {
		sl = new ServiceLocator();
		sl.setService("127.0.0.1", "1099", "OrderService");
		new Login(this);
	}
	
	public UserDTO signIn(String email, String pass) throws RemoteException {
		return sl.getService().signIn(email, pass);
	}
	
	public void register(String email, String name, String pass, String cc) throws RemoteException {
		sl.getService().register(email, name, pass, cc);
	}

	public static void main(String[] args) throws RemoteException {
		new Controller(args);
	}
}
