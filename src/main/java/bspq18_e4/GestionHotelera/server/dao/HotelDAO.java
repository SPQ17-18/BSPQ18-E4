package main.java.bspq18_e4.GestionHotelera.server.dao;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;

import main.java.bspq18_e4.GestionHotelera.server.data.Hotel;
import main.java.bspq18_e4.GestionHotelera.server.data.Reservation;
import main.java.bspq18_e4.GestionHotelera.server.data.User;

public class HotelDAO implements IHotelDAO {

	private PersistenceManagerFactory pmf;

	public HotelDAO() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	public void store(Object objeto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			pm.makePersistent(objeto);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error storing: " + ex.getMessage());
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

	public void book(Reservation reservation) {
		store(reservation);
	}
	
	public ArrayList<Hotel> getHotels() {
		ArrayList<Hotel> hotels = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for(Hotel hotel : ext){
				hotels.add(hotel);
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error storing: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return hotels;
	}

}
