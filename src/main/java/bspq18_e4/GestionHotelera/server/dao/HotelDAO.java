package main.java.bspq18_e4.GestionHotelera.server.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import main.java.bspq18_e4.GestionHotelera.server.data.User;

public class HotelDAO implements IHotelDAO{

	private PersistenceManagerFactory pmf;
	
	public HotelDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public void store(Object objeto) {
		// Persistence Manager
		PersistenceManager pm = pmf.getPersistenceManager();
		// Transaccion para agrupar las operaciones con la bd
		Transaction tx = pm.currentTransaction();

		try {
			// Empezamos la transaccion
			tx.begin();
			System.out.println("Introduciendo el contenido en la base de datos...");
			pm.makePersistent(objeto);
			// Fin de la transccion
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error al almacenar los objetos: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public void register(User user) {
		store(user);
		
	}
	
}
