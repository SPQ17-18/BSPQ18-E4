package bspq18_e4.GestionHotelera.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	private Connection connect = null;
    private Statement stmt = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement?" + "user=hotel&password=pass");
        } catch (SQLException e) {
            throw e;
        }

    }
    
    public void getHotels(){
		try {
			stmt = connect.createStatement();
	    	ResultSet rs = stmt.executeQuery("SELECT Lname FROM Customers WHERE Snum = 2001");

		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    public void close(){
    	try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	
}
