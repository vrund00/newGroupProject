package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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

public class AddAndDeleteController  implements Initializable{
	
    @FXML
    private Button addFlight;

    @FXML
    private Label addStatus;

    @FXML
    private TableColumn<FlightData, Integer> colFlightDate;

    @FXML
    private TableColumn<FlightData, Integer> colFlightID;

    @FXML
    private TableColumn<FlightData, String> colFromCity;
 
    @FXML
    private TableColumn<FlightData, Integer> colNumPass;

    @FXML
    private TableColumn<FlightData, String> colToCity;

    @FXML
    private Button deleteFlight;

    @FXML
    private TextField deleteFlightTF;

    @FXML
    private Label deleteStatus;

    @FXML
    private TextField flightDateTF;

    @FXML
    private TextField flightIDTF;

    @FXML
    private TableView<FlightData> flightsTb;

    @FXML
    private TextField fromCityTF;

    @FXML
    private TextField numPassTF;

    @FXML
    private TextField toCityTF;

    @FXML
    private Button updateFromCity;

    @FXML
    private Button updateFlightDate;
    @FXML
    private Button updateToCity;
    @FXML
    private Button updateNumPass;
    
    @FXML
    private Label fromCityStatus;
    
    @FXML
    private Label flightDateStatus;

    @FXML
    private Label toCityStatus;

    @FXML
    private Label numPassStatus;

    @FXML
    private Button mainMenuButton;
    
    @FXML
    private Button refreshTable; 
    
    @FXML
    private Label addRefreshStatus;
	    
	    public static Connection getConnection() throws SQLException{
			
			String connectionString = "jdbc:sqlserver://flightapp.database.windows.net:1433;database=CIS Application Project;user=vrund00@flightapp;password={Nehalp1974*};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
					
					Connection connection = DriverManager.getConnection(connectionString);
					System.out.println("Connection Established");
					return connection;

	    }
	    ObservableList<FlightData> listM;
	    int index = -1;
	    ResultSet rs = null;
		PreparedStatement ps = null;
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			colFlightID.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("FlightID"));
			colFromCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("FromCity"));
			colFlightDate.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("FlightDate"));
			colToCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("ToCity"));
			colNumPass.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("numPass"));
			
			
			
			listM = DatabaseConnection.getDataFlights();
			flightsTb.setItems(listM); 
			
			
		}
	    
		public void backToMainMenu() throws Exception{
			Parent root = FXMLLoader.load(getClass().getResource("MainFlightScene.fxml"));
			Stage window = (Stage)mainMenuButton.getScene().getWindow();
			window.setScene(new Scene(root, 600, 600));
		}
		
		public void addFlight()throws Exception{
			try {
				
				Connection con = getConnection();
				
				PreparedStatement ps = con.prepareStatement("insert into Flights(flightID, FromCity, FlightDate, ToCity, numPass) values (?,?,?,?,?)");
				
				ps.setString(1, flightIDTF.getText());
				ps.setString(2, fromCityTF.getText());
				ps.setString(3, flightDateTF.getText());
				ps.setString(4, toCityTF.getText());
				ps.setString(5, numPassTF.getText());
				
				ps.executeUpdate();
				
				addRefreshStatus.setText("Flight Created");
				
				con.close();
			}catch(Exception e) {System.out.println(e);}
			finally {System.out.println("Function completed");};
			
			
		}
		//change to refresh table method
		public void updateFromCity()throws Exception{
			
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connectionDB = connectNow.getConnection();
			
			String verifyFlightID = ("SELECT count(1) from [dbo].[Flights] where flightID ='" + flightIDTF.getText() + "'");
		
			try {
				Statement statement = connectionDB.createStatement();
				ResultSet queryResult = statement.executeQuery(verifyFlightID);
				
				
				while(queryResult.next()) {
					if(queryResult.getInt(1)==1) {
						
						PreparedStatement ps2 = connectionDB.prepareStatement("UPDATE Flights SET FromCity = ? WHERE flightID =?"); 
				
						ps2.setString(1, fromCityTF.getText());
						ps2.setString(2, flightIDTF.getText());
						
						ps2.executeUpdate();
						
						fromCityStatus.setText("From Updated");
						
					}else {
						fromCityStatus.setText("FlightID not found");
					}
					connectionDB.close();
				}
				
			}catch(Exception e) {System.out.println(e);}
			finally {System.out.println("Function completed");};
		}
		
		public void updateFlightDate()throws Exception{
					
					DatabaseConnection connectNow2 = new DatabaseConnection();
					Connection connectionDB2 = connectNow2.getConnection();
					
					String verifyFlightID2 = ("SELECT count(1) from [dbo].[Flights] where flightID ='" + flightIDTF.getText() + "'");
				
					try {
						Statement statement2 = connectionDB2.createStatement();
						ResultSet queryResult2 = statement2.executeQuery(verifyFlightID2);
						
						while(queryResult2.next()) {
							if(queryResult2.getInt(1)==1) {
								
								PreparedStatement ps3 = connectionDB2.prepareStatement("UPDATE Flights SET FlightDate = ? WHERE flightID =?"); 
						
								ps3.setString(1, flightDateTF.getText());
								ps3.setString(2, flightIDTF.getText());
								
								ps3.executeUpdate();
								
								flightDateStatus.setText("Date Updated");
								
							}else {
								flightDateStatus.setText("FlightID not found");
							}
							connectionDB2.close();
						}
						
					}catch(Exception e) {System.out.println(e);}
					finally {System.out.println("Function completed");};
				}
		
		public void updateToCity()throws Exception{
			
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connectionDB = connectNow.getConnection();
			
			String verifyFlightID = ("SELECT count(1) from [dbo].[Flights] where flightID ='" + flightIDTF.getText() + "'");
		
			try {
				Statement statement = connectionDB.createStatement();
				ResultSet queryResult = statement.executeQuery(verifyFlightID);
				
				while(queryResult.next()) {
					if(queryResult.getInt(1)==1) {
						
						PreparedStatement ps4 = connectionDB.prepareStatement("UPDATE Flights SET ToCity = ? WHERE flightID =?"); 
				
						ps4.setString(1, toCityTF.getText());
						ps4.setString(2, flightIDTF.getText());
						
						ps4.executeUpdate();
						
						toCityStatus.setText("To Updated");
						
					}else {
						toCityStatus.setText("FlightID not found");
					}
					connectionDB.close();
				}
				
			}catch(Exception e) {System.out.println(e);}
			finally {System.out.println("Function completed");};
		}
		
		
		public void updateNumPass()throws Exception{
					
					DatabaseConnection connectNow = new DatabaseConnection();
					Connection connectionDB = connectNow.getConnection();
					
					String verifyFlightID = ("SELECT count(1) from [dbo].[Flights] where flightID ='" + flightIDTF.getText() + "'");
				
					try {
						Statement statement = connectionDB.createStatement();
						ResultSet queryResult = statement.executeQuery(verifyFlightID);
						
						while(queryResult.next()) {
							if(queryResult.getInt(1)==1) {
								
								PreparedStatement ps5 = connectionDB.prepareStatement("UPDATE Flights SET numPass = ? WHERE flightID =?"); 
						
								ps5.setString(1, numPassTF.getText());
								ps5.setString(2, flightIDTF.getText());
								
								ps5.executeUpdate();
								
								numPassStatus.setText("#Pass Updated");
								
							}else {
								numPassStatus.setText("FlightID not found");
							}
							connectionDB.close();
						}
						
					}catch(Exception e) {System.out.println(e);}
					finally {System.out.println("Function completed");};
				}		

		public void deleteFlight()throws Exception{
			
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connectionDB = connectNow.getConnection();
			
			String verifyFlightID = ("SELECT count(1) from [dbo].[Flights] where flightID ='" + flightIDTF.getText() + "'");
		
			try {
				Statement statement = connectionDB.createStatement();
				ResultSet queryResult = statement.executeQuery(verifyFlightID);
				
				while(queryResult.next()) {
					if(queryResult.getInt(1)==1) {
						
						PreparedStatement ps5 = connectionDB.prepareStatement("DELETE FROM Flights WHERE flightID =?"); 
				
						ps5.setString(1, deleteFlightTF.getText());
						
						ps5.executeUpdate();
						
						deleteStatus.setText("Flight Deleted");
						
					}else {
						deleteStatus.setText("FlightID not found");
					}
					connectionDB.close();
				}
				
			}catch(Exception e) {System.out.println(e);}
			finally {System.out.println("Function completed");};
		}		
		
		public void refreshTable() throws Exception{
			
			colFlightID.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("FlightID"));
			colFromCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("FromCity"));
			colFlightDate.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("FlightDate"));
			colToCity.setCellValueFactory(new PropertyValueFactory<FlightData, String>("ToCity"));
			colNumPass.setCellValueFactory(new PropertyValueFactory<FlightData, Integer>("numPass"));
			
			
			
			listM = DatabaseConnection.getDataFlights();
			flightsTb.setItems(listM); 
		}
		
}
