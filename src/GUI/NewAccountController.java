package GUI;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.event.ActionEvent;

public class NewAccountController {
	
    @FXML
    private TextField adminIDTF;

    @FXML
    private TextField adminTF;

    @FXML
    private TextField answer1TF;

    @FXML
    private TextField answer2TF;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cityTF;

    @FXML
    private Button createNewUser;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField firstNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField ssnTF;

    @FXML
    private TextField stateTF;

    @FXML
    private TextField streetTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private TextField zipcodeTF;
    
    @FXML
    private Label status;
	
public static Connection getConnection() throws SQLException{
		
		String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
				
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection Established");
				return connection;

}    
	
	public void cancelButton() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Stage window = (Stage)cancelButton.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	
	// need to add update sql database with info line not only go back to login
	/*public void createNewUser() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Stage window = (Stage)createNewUser.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	*/
	
	public void insertUserInfo()throws Exception {
		try {
			Connection con = getConnection();
			
			PreparedStatement ps= con.prepareStatement("insert into UserInfo(firstName, lastName, street, city, state, zipcode,email, ssn, username, password, answer1, answer2, admin,adminID) "
					+ "											values(?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?)");
			
			ps.setString(1, firstNameTF.getText());
			ps.setString(2, lastNameTF.getText());
			ps.setString(3, streetTF.getText());
			ps.setString(4, cityTF.getText());
			ps.setString(5, stateTF.getText());
			ps.setString(6, zipcodeTF.getText());
			ps.setString(7, emailTF.getText());
			ps.setString(8, ssnTF.getText());
			ps.setString(9, usernameTF.getText());
			ps.setString(10, passwordTF.getText());
			ps.setString(11, answer1TF.getText());
			ps.setString(12, answer2TF.getText());
			ps.setString(13, adminTF.getText());
			ps.setString(14, adminIDTF.getText());
			ps.executeUpdate();
			
			String username = usernameTF.getText();
			PreparedStatement create = con.prepareStatement("CREATE TABLE " + username  +" (ssn char(9), flightID varchar(5), "
					+ "																FromCity varchar(10),"
					+ "																FlightDate varchar(10), "
					+ "																ToCity varchar(10), "
					+ "																numPass varchar(30), "
					+ "																primary key (ssn, flightID))");
			 
			create.executeUpdate();
			
		}catch(Exception e) {System.out.println(e);}
		finally {System.out.println("Function completed");};
		
		status.setText("New User Created -> Exit to login");
	}
	
	
		
}



