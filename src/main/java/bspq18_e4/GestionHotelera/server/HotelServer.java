package main.java.bspq18_e4.GestionHotelera.server;

import java.rmi.Naming;

import main.java.bspq18_e4.GestionHotelera.server.remote.IService;
import main.java.bspq18_e4.GestionHotelera.server.remote.Service;

public class HotelServer {

	public static void main(String[] args) {


		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IService deustoBox = new Service();
			Naming.rebind(name, deustoBox);
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
			java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
			String line = stdin.readLine();
			
		} catch (Exception e) {
			System.err.println("$ DeustoBox exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
