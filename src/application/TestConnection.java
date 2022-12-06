package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
	public static void main(String[]args) throws Exception {
		
	//creatTable();
	}
	
	/*public static void creatTable() throws Exception {
		try {
			Connection con;
			
			con = getConnection();
			PreparedStatement create =  con.prepareStatement(" create table TestTable(courseID char(5), subjectId char(4) not null)");
			create.executeUpdate();
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function complete");}
	}*/
	
	
	
	
	public static Connection getConnection() throws SQLException{
		
String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		
		Connection connection = DriverManager.getConnection(connectionString);
		System.out.println("Connection Established");
		return connection;
				
		
	

}
}
