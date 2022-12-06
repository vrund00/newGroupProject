package GUI;
import java.io.FileInputStream;
import java.io.IOException;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordRecoveryController {
	 
	 
	@FXML
	Button btnEnter;
	
	@FXML
	Button btnCancel;
	
	@FXML
    private TextField answer;
	
	@FXML
    private TextField securityQ;
	
public static Connection getConnection() throws SQLException{
		
		String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
				
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection Established");
				return connection;
}
	
	public void handlebtnEnter() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MainFlightScene.fxml"));
		Stage window = (Stage)btnEnter.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	public void handlebtnCancel() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Stage window = (Stage)btnCancel.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	
	public void cancel() throws Exception {
		
	}
		
	 
	public void secruityquestion()throws Exception {
		try {
			Connection con = getConnection();
			
			PreparedStatement ps= con.prepareStatement("What is the name of your first dog?(answer) "
					+ "											values(?,?)");
			
	
			ps.setString(1, securityQ.getText());
			ps.setString(2, answer.getText());
			ps.executeUpdate();
			
			
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function completed");};
	}
	
	
		
}  



