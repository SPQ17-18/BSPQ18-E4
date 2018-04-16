package bspq18_e4.GestionHotelera.client.remote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import bspq18_e4.GestionHotelera.server.remote.IService;

public class ServiceLocator {

	private IService facade;

	public ServiceLocator() {
		
	}
	
public void setService(String ip, String port, String serviceName) {
    	
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
    	
    	String url = "//" + ip + ":" + port + "/" + serviceName;
		
    	System.out.println("Client looking for service : //" + ip + ":" + port + "/" + serviceName);
		try {
			facade = (IService)Naming.lookup(url);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		} catch (NotBoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("- Connected to server: " + ip + ":" + port + "/" + serviceName);
    }
    public IService getService() {
    		return facade;
    }

}
