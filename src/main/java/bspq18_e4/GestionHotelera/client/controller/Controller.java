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
		sl.setService("127.0.0.1", "1099", "Service");
	}
	
	public UserDTO signIn(String email, String pass) throws RemoteException {
		return sl.getService().signIn(email, pass);
	}
	
	public void register(UserDTO userDTO) throws RemoteException {
		sl.getService().register(userDTO);
	}

	
	
	public static void main(String[] args)  throws RemoteException {
		Controller ctrl = new Controller(args);

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		
		try {
			Login login = new Login(ctrl);
			login.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
