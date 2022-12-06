package GUI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

import database.Flights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnection {
	
	public Connection databaseLink;
	
	public Connection getConnection () {
		
		try {
			databaseLink = DriverManager.getConnection("jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return databaseLink;
		//another
		
	}
	
	public static ObservableList<FlightData> getDataFlights() {
		
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectionDB = connectNow.getConnection();
		
		ObservableList<FlightData> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = connectionDB.prepareStatement("SELECT * from [dbo].[Flights]");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new FlightData((rs.getInt("flightID")), rs.getString("FromCity"), rs.getString("FlightDate"), rs.getString("ToCity"), rs.getInt("numPass")));
				
			}
			
		}catch (Exception e) { e.printStackTrace();}
		return list;
	}
	
	
	public static String username = "";
	
	public static ObservableList<UserFlightData> getUserFlights() {
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectionDB = connectNow.getConnection();
		
		String user = storeData.username;
		
		ObservableList<UserFlightData> list = FXCollections.observableArrayList();
		try {
			PreparedStatement ps = connectionDB.prepareStatement("SELECT * FROM " + user);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(new UserFlightData((rs.getInt("flightID")), rs.getString("FromCity"), rs.getString("FlightDate"), rs.getString("ToCity"), rs.getInt("numPass")));
			}
			
		}catch (Exception e) { e.printStackTrace();}
		return list;
		
	}
	
		
		

		
	}


