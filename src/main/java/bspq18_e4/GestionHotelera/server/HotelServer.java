package bspq18_e4.GestionHotelera.server;

import java.rmi.Naming;

import bspq18_e4.GestionHotelera.server.remote.IService;
import bspq18_e4.GestionHotelera.server.remote.Service;

public class HotelServer {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IService hotelServer = new Service();
			Naming.rebind(name, hotelServer);

			System.out.println("Server ready and waiting...");

			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
			java.io.BufferedReader stdin = new java.io.BufferedReader(inputStreamReader);
			String line = stdin.readLine();
			
		} catch (Exception e) {
			System.err.println("$ Hotel exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
