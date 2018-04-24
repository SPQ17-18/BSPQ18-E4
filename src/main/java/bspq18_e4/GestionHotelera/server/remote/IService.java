package bspq18_e4.GestionHotelera.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import bspq18_e4.GestionHotelera.server.dto.UserDTO;

public interface IService extends Remote {

	public UserDTO signIn(String email, String pass) throws RemoteException;
	public void register(UserDTO userDto) throws RemoteException;
	public boolean isRegistered(UserDTO userDto) throws RemoteException;
}
