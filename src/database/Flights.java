package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Flights {
	
	public static Connection getConnection() throws SQLException{
		
		String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
				
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection Established");
				return connection;

}
	public static void main(String[] args) throws Exception {
		creatFlight();
		
	}
	
	public static void creatFlight() throws Exception {
		
		try {
			Connection con = getConnection();
			//UPDATE [dbo].[Flights] set FlightDate = '11/3/2022' WHERE FlightID=1004
			//PreparedStatement create = con.prepareStatement("Create table Flights(flightID Inte(5) PRIMARY KEY , FromCity varchar(50), FlightDate varchar(20), ToCity varchar(50), numPass varchar(10))");
			//PreparedStatement create = con.prepareStatement("insert into Flights(flightID, FromCity, FlightDate, ToCity, numPass) values ('1002','NYC','12-02-2022','ATL','20')");
			//PreparedStatement create = con.prepareStatement("ALTER TABLE Flights ADD FlightDate TEXT");
			//create.executeUpdate();
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function completed");};
	}






}