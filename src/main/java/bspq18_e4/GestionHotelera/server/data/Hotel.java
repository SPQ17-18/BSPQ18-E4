package bspq18_e4.GestionHotelera.server.data;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private int id;
	private String name;
	private String city;
	private String dir;
	private int stars;
	
	public Hotel(int id, String name, String city, String dir, int stars) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.dir = dir;
		this.stars = stars;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", city=" + city + ", dir=" + dir + ", stars=" + stars + "]";
	}
	
}
