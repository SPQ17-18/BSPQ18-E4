package bspq18_e4.GestionHotelera.server.dao;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;

import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.User;

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

	public User getUser(String email, String pass) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
		User userSel=null;
		
		try {
			tx.begin();
			Extent<User> ext = pm.getExtent(User.class, true);
			for(User user : ext){
				if(user.getEmail().equals(email)&& user.getPass().equals(pass)) {
					userSel = user;
					break;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			System.out.println("# Error getting user: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		
		return userSel;
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

	public static void main(String[] args) {
		IHotelDAO dao = new HotelDAO();
		User user1 = new User("aa", "bb", "1234", "123456789");
		User user2 = new User("cc", "dd", "1234", "987654321");

		dao.register(user1);
		dao.register(user2);
		System.out.println("Ya");
	}
	
}
