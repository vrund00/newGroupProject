package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerInfo {
	
public static Connection getConnection() throws SQLException{
		
		String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
				
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection Established");
				return connection;

}

public static void creatCustomer() throws Exception {
	
	try {
		Connection con = getConnection();
		PreparedStatement create = con.prepareStatement(" create table UserTest(username varchar(30), password varchar(40))");
		//PreparedStatement create = con.prepareStatement("insert UserTest(username, password) values ('Vrund', 'pass')");
		create.executeUpdate();
		
	}catch(Exception e) {System.out.println(e);}
	finally {System.out.println("Function completed");};
}

public static void main (String[] args) throws Exception{
	creatCustomer();
	
}


}
