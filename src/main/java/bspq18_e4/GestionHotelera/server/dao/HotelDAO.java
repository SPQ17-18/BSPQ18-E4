package bspq18_e4.GestionHotelera.server.dao;

import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;

import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;

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
	
	public Hotel geHotelById(int id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
		Hotel hotelSel=null;
		
		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for(Hotel hotel : ext){
					if (hotel.getId()==id) {
						hotelSel = hotel;
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
		
		return hotelSel;
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
	
	public ArrayList<Hotel> getHotelsByCity(String city) {
		ArrayList<Hotel> hotels = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			if(city.equals("All")) {
				for(Hotel hotel : ext){
					hotels.add(hotel);
				}
			}
			else {
				for(Hotel hotel : ext){
					if(hotel.getCity().equals(city)) {
						hotels.add(hotel);
					}
				}
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
	
	public ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for(Hotel hotel : ext){
				if(!cities.contains(hotel.getCity())) {
					cities.add(hotel.getCity());
				}
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
		
		return cities;
	}
	
	public ArrayList<Room> getRooms(Hotel hotel) {
		ArrayList<Room> rooms = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Room> ext = pm.getExtent(Room.class, true);
			for(Room room : ext){
				if(room.getHotel().getId() == hotel.getId()) {
					System.out.println(room);
					rooms.add(room);
				}
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
		
		return rooms;
	}
	
//	public ArrayList<Room> getRoomss(int id) {
//		ArrayList<Room> rooms = new ArrayList<>();
//		PersistenceManager pm = pmf.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(3);
//		Transaction tx = pm.currentTransaction();
//
//		try {
//			tx.begin();
//			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
//			Extent<Room> ext2 = pm.getExtent(Room.class, true);
//			System.out.println("idddddd "+id);
//				for (Room room : ext2) {
//					for(Hotel hotel : ext){		
//					if (hotel.getId()==id) {
//						rooms.add(room);
//						System.out.println(room.getPrice());
//					}
//					}
//				}
//			
//			tx.commit();
//		} catch (Exception ex) {
//			System.out.println("# Error storing: " + ex.getMessage());
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//			pm.close();
//		}
//		
//		return rooms;
//	}
	
//	public ArrayList<Room> getRooms(int id) {
//		ArrayList<Hotel> hotels = new ArrayList<>();
//		ArrayList<Room> rooms = new ArrayList<>();
//		int selId=0;
//		PersistenceManager pm = pmf.getPersistenceManager();
//		pm.getFetchPlan().setMaxFetchDepth(3);
//		Transaction tx = pm.currentTransaction();
//
//		try {
//			tx.begin();
//			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
//			for(Hotel hotel : ext){
//				if (hotel.getId()==id) {
//					hotels.add(hotel);
//				}
//				for (int i = 0; i < hotels.size(); i++) {
//					if (hotels.get(i).getId()==id) {
//						selId = hotels.get(i).getId();
//					}
//				}
//			}			
//			try {
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			tx.commit();
//		} catch (Exception ex) {
//			System.out.println("# Error storing: " + ex.getMessage());
//		} finally {
//			if (tx != null && tx.isActive()) {
//				tx.rollback();
//			}
//			pm.close();
//		}
//		
//		return rooms;
//	}
	
	public static void main(String[] args) {
		IHotelDAO dao = new HotelDAO();
		User user1 = new User("aa", "bb", "1234", "123456789");
		User user2 = new User("cc", "dd", "1234", "987654321");

		dao.register(user1);
		dao.register(user2);
	}
	
}
