package bspq18_e4.GestionHotelera.client.controller;

import java.rmi.RemoteException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import bspq18_e4.GestionHotelera.client.gui.Login;
import bspq18_e4.GestionHotelera.client.remote.ServiceLocator;
import bspq18_e4.GestionHotelera.server.dao.HotelDAO;
import bspq18_e4.GestionHotelera.server.dao.IHotelDAO;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public class Controller {

	private ServiceLocator sl;
	
	public Controller(String[] args) throws RemoteException {
		sl = new ServiceLocator();
		sl.setService("127.0.0.1", "1099", "OrderService");
		IHotelDAO dao = new HotelDAO();
		User user1 = new User("aa", "bb", "1234", "123456789");
		User user2 = new User("cc", "dd", "1234", "987654321");
//		dao.register(user1);
//		dao.register(user2);
		new Login(this);
	}
	
	public UserDTO signIn(String email, String pass) throws RemoteException {
		return sl.getService().signIn(email, pass);
	}
	
	public void register(UserDTO userDto) throws RemoteException {
		sl.getService().register(userDto);
	}
	
	public boolean isRegistered(UserDTO userDto) throws RemoteException{
		return sl.getService().isRegistered(userDto);
	}

	public static void main(String[] args) throws RemoteException {
		new Controller(args);
	}
}
