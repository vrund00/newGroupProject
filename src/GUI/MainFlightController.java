package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainFlightController implements Initializable {
	
	@FXML
    private TableColumn<UserFlightData, String> FlightDate_Col;

    @FXML
    private TableColumn<UserFlightData, String> FromCity_Col;

    @FXML
    private TableColumn<UserFlightData, Integer> Passengers_Col;

    @FXML
    private TableColumn<UserFlightData, String> ToCity_Col;

    @FXML
    private TableColumn<UserFlightData, Integer> flightID_Col;

    @FXML
    private Button logout;

    @FXML
    private TableView<UserFlightData> myFlights_Table;

    @FXML
    private Button searchButton;

    @FXML
    private Label welcomeID;
    
    @FXML
    private Label cancel_label;
    
    @FXML
    private TextField flightID_Box;
    
    ObservableList<UserFlightData> listU;
    
    int index = -1;
	
	ResultSet rs = null;
	PreparedStatement ps = null;

	String user = storeData.username;
	
	
	
	
	
	
	public void logout() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Stage window = (Stage)logout.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
		
	}
	public void search() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("SearchFlightScene.fxml"));
		Stage window = (Stage)logout.getScene().getWindow();
		window.setScene(new Scene(root, 600, 600));
	}
	
	public void cancel() throws Exception {
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		FlightDate_Col.setCellValueFactory(new PropertyValueFactory<UserFlightData, String>("FlightDate"));
		FromCity_Col.setCellValueFactory(new PropertyValueFactory<UserFlightData, String>("FromCity"));
		ToCity_Col.setCellValueFactory(new PropertyValueFactory<UserFlightData, String>("ToCity"));
		flightID_Col.setCellValueFactory(new PropertyValueFactory<UserFlightData, Integer>("flightID"));
		Passengers_Col.setCellValueFactory(new PropertyValueFactory<UserFlightData, Integer>("numPass"));
		
		listU = DatabaseConnection.getUserFlights();
		myFlights_Table.setItems(listU);
		
	}
	
	public void cancelFlight() {
		
		
		String userInput = flightID_Box.getText();
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectionDB = connectNow.getConnection();
		
		String sql = ("SELECT count(1) from " + user + " where flightID = " + userInput);
		
		
		try {
			Statement statement = connectionDB.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				if (rs.getInt(1) == 1) {
					PreparedStatement ps = connectionDB.prepareStatement("DELETE FROM " + user + " WHERE " + userInput);
					ps.execute();
					cancel_label.setText("Flight Canceled");
				}
				else {
					cancel_label.setText("Flight not found");
				}
			}
			
		}catch(Exception e) {e.printStackTrace();}
	}

}
