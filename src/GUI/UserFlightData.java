package GUI;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;


public class UserFlightData {
	
	public UserFlightData(int flightID, String fromCity, String flightDate, String toCity, int numPass) {
		
		this.flightID = flightID;
		this.FromCity = fromCity;
		this.FlightDate = flightDate;
		this.ToCity = toCity;
		this.numPass = numPass;
	}
	int flightID;
	String FromCity;
	String FlightDate;
	String ToCity;
	int numPass;
	public int getFlightID() {
		return flightID;
	}
	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}
	public String getFromCity() {
		return FromCity;
	}
	public void setFromCity(String fromCity) {
		FromCity = fromCity;
	}
	public String getFlightDate() {
		return FlightDate;
	}
	public void setFlightDate(String flightDate) {
		FlightDate = flightDate;
	}
	public String getToCity() {
		return ToCity;
	}
	public void setToCity(String toCity) {
		ToCity = toCity;
	}
	public int getNumPass() {
		return numPass;
	}
	public void setNumPass(int numPass) {
		this.numPass = numPass;
	}
	
	
	


}
