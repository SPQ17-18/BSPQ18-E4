package bspq18_e4.GestionHotelera.server.data;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	private int num; // 101 -> fllor 1, 325 -> floor 3
	private String type;
	private int capacity;
	private double price;
	private Hotel hotel;

	public Room(int num, String type, int capacity, double price, Hotel hotel) {
		this.num = num;
		this.type = type;
		this.capacity = capacity;
		this.price = price;
		this.hotel = hotel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		return "Room [num=" + num + ", type=" + type + ", capacity=" + capacity + ", price=" + price + ", hotel="
				+ hotel + "]";
	}

}
