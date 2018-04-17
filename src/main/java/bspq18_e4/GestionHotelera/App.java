package bspq18_e4.GestionHotelera;

import bspq18_e4.GestionHotelera.server.remote.IService;

public class App 
{
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IService service = (IService) java.rmi.Naming.lookup(name);
			// Register to be allowed to send messages
			System.out.println("Register a user for the first time: dipina");
			objHello.registerUser("dipina", "dipina");
			System.out.println("Change the password as the user is already registered: cortazar");
			objHello.registerUser("dipina", "cortazar");
			System.out.println("* Message coming from the server: '" + objHello.sayMessage("dipina", "cortazar", "This is test 1!") + "'");
			System.out.println("* Message coming from the server: '" + objHello.sayMessage("dipina", "cortazar", "This is test 2!") + "'");
			User u = objHello.getUserMessages("dipina");
			for (Message m: u.getMessages()) {
				
				System.out.println(m);
				
			}
			
		} catch (Exception e) {
			System.err.println("RMI Example exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
