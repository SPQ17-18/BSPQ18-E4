package main.java.bspq18_e4.GestionHotelera.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.java.bspq18_e4.GestionHotelera.server.dto.UserDTO;

public interface IService extends Remote {

	public UserDTO signIn(String email, String pass) throws RemoteException;
	public void register(UserDTO userDTO) throws RemoteException;
	
}
