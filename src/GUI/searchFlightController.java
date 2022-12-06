package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.List;


public class searchFlightController implements Initializable {
	
	@FXML 
	private TableColumn<FlightData,Integer> FlightDate;
	
	@FXML
	private TableColumn<FlightData,String> FromCity;
	
	@FXML
	private TableColumn<FlightData,String> ToCity;
	
	@FXML
	private TableColumn<FlightData,Integer> flightID;
	
	@FXML
	private TableColumn<FlightData,Integer> numPass;
	
	@FXML
	private TextField toCityBox;
	
	@FXML
	private Button mainMenu;
	
	@FXML
	private TextField fromCityBox;
	
	@FXML
	private DatePicker dateBox;
	
	@FXML 
	Button book;
	
	@FXML 
	Button bookButton;
	
	@FXML
	
	TextField bookBox;
	
	@FXML 
	Label bookSuccess;
	
	
	@FXML
	private TableView <FlightData> flightsTable;
	
	ObservableList<FlightData> listM;
	
	int index = -1;
	
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	
	
	
	public void menu() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("MainFlightScene.fxml"));
		Stage window = (Stage)mainMenu.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	
	public static Connection getConnection() throws SQLException{
		
		String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
				
				Connection connection = DriverManager.getConnection(connectionString);
				System.out.println("Connection Established");
				return connection;

}

	//@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		FlightDate.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("FlightDate"));
		FromCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("FromCity"));
		ToCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("ToCity"));
		flightID.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("flightID"));
		numPass.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("numPass"));
		
		listM = DatabaseConnection.getDataFlights();
		flightsTable.setItems(listM); 
				
	}
	
	public String getUsername(String username) {
		return username;
	}
	

	public void bookFlight() {
		
		
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectionDB = connectNow.getConnection();
		
		
		
		String sql2 = ("SELECT count(1) FROM [dbo].[Flights] WHERE flightID ='" + bookBox.getText() + "'" );
		try {
			
			PreparedStatement ps = connectionDB.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			
			String user = storeData.username;
			
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					bookSuccess.setText("Flight Booked");
					
					PreparedStatement ps1 = connectionDB.prepareStatement("INSERT INTO " + user + " SELECT * FROM [dbo].[Flights] WHERE flightID ='" + bookBox.getText() + "'" );
					ps1.execute();
					
				}
				
				else {
					bookSuccess.setText("Error no flights found");
				}
			}
			
			
		}catch (Exception e) {e.printStackTrace();}
		
	}
	
	
}
	




