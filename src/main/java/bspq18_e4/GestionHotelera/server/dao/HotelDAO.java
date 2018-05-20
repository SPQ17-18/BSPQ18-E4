package bspq18_e4.GestionHotelera.server.dao;

import java.sql.Array;
import java.util.ArrayList;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;

import bspq18_e4.GestionHotelera.server.assembler.Assemble;
import bspq18_e4.GestionHotelera.server.data.Hotel;
import bspq18_e4.GestionHotelera.server.data.Reservation;
import bspq18_e4.GestionHotelera.server.data.ReservationRooms;
import bspq18_e4.GestionHotelera.server.data.Room;
import bspq18_e4.GestionHotelera.server.data.User;
import bspq18_e4.GestionHotelera.server.dto.HotelDTO;
import bspq18_e4.GestionHotelera.server.logger.ErrorLogger;

public class HotelDAO implements IHotelDAO {

	private PersistenceManagerFactory pmf;
	private static ErrorLogger logger;
	
	public HotelDAO(ErrorLogger logger) {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.logger = logger;
	}

	public void store(Object objeto) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			pm.makePersistent(objeto);
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error storing object: " + ex.getMessage());
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
		Assemble ass = new Assemble();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
		User userSel = null;

		try {
			tx.begin();
			Extent<User> ext = pm.getExtent(User.class, true);
			for (User user : ext) {
				if (user.getEmail().equals(email) && user.getPass().equals(pass)) {
					if (user.getReservations().isEmpty()) {
						//logger.getLogger().info("# Error storing object: " );
						userSel = user;
						break;
					} else {
						userSel = new User(user.getEmail(), user.getName(), user.getPass(), user.getCc());
					}
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting user: " + ex.getMessage());
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
		Hotel hotelSel = null;

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for (Hotel hotel : ext) {
				if (hotel.getId() == id) {
					hotelSel = hotel;
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting hotelbyid: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return hotelSel;
	}

	public void book(Reservation reservation, ArrayList<Room> rooms) {
		PersistenceManager pm = pmf.getPersistenceManager();
		// pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();

			Extent<User> extentUser = pm.getExtent(User.class, true);
			Extent<Room> extentRoom = pm.getExtent(Room.class, true);

			for (User user : extentUser) {
				for (Room room : extentRoom) {
					if (reservation.getUser().getEmail().equals(user.getEmail())) {
						reservation.setUser(user);
						for (int i = 0; i < rooms.size(); i++) {
							pm.makePersistent(new ReservationRooms(rooms.get(i), reservation));
						}
						pm.makePersistent(user);
						break;
					}
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error booking: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	public ArrayList<Hotel> getHotels() {
		ArrayList<Hotel> hotels = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for (Hotel hotel : ext) {
				hotels.add(hotel);
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error gtting hotels: " + ex.getMessage());
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
			if (city.equals("All")) {
				for (Hotel hotel : ext) {
					hotels.add(hotel);
				}
			} else {
				for (Hotel hotel : ext) {
					if (hotel.getCity().equals(city)) {
						hotels.add(hotel);
					}
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting hotelsbycity: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return hotels;
	}

	public ArrayList<Reservation> getReservationsByUser(User user) {
		ArrayList<Reservation> reservations = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(-1);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Reservation> ext = pm.getExtent(Reservation.class, true);
			for (Reservation reservation : ext) {
				if (reservation.getUser().getEmail().equals(user.getEmail())) {
					reservations.add(new Reservation(0, reservation.getArrival(), reservation.getDeparture(), reservation.getUser(), reservation.getHotel()));
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting reservationsbyuser: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return reservations;
	}

	public ArrayList<String> getCities() {
		ArrayList<String> cities = new ArrayList<>();
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			Extent<Hotel> ext = pm.getExtent(Hotel.class, true);
			for (Hotel hotel : ext) {
				if (!cities.contains(hotel.getCity())) {
					cities.add(hotel.getCity());
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting cities: " + ex.getMessage());
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
			for (Room room : ext) {
				if (room.getHotel().getId() == hotel.getId()) {
					rooms.add(room);
				}
			}
			tx.commit();
		} catch (Exception ex) {
			//logger.getLogger().error("# Error getting rooms: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return rooms;
	}

	public static void main(String[] args) {
		IHotelDAO dao = new HotelDAO(logger);
		User user1 = new User("aa", "bb", "1234", "123456789");
		User user2 = new User("cc", "dd", "1234", "987654321");

		// dao.register(user1);
		// dao.register(user2);
	}

}
