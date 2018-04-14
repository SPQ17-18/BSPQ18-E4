package main.java.bspq18_e4.GestionHotelera.client.remote;

import main.java.bspq18_e4.GestionHotelera.server.remote.IService;

public class ServiceLocator {

	private IService facade;
	
	public void setService(String [] args){

		
		
	String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

	System.out.println(name);
	try {
		this.facade =(IService) java.rmi.Naming.lookup(name);
	}
	catch (Exception ex) {
		
		System.out.println("An error has happened while setting the service");
	}
		
	}


	public IService getService(){
		return this.facade;
		
	}
	
}
