package main.java.bspq18_e4.GestionHotelera.server.dto;

import java.io.Serializable;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int num;
	private String type;
	private int capacity;
	private double price;
	private HotelDTO hotel;

	public RoomDTO(int num, String type, int capacity, double price, HotelDTO hotel) {
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

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

}
