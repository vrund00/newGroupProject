package application;

	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.CheckBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.StackPane;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.sql.ResultSet;
	import java.sql.Statement;

	public class TestCode extends Application {
		
		Stage window;
		
		Scene scene;
		
		Button button;
		
		Button button1;


	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		window.setTitle("Checkbox Example");
		
		 CheckBox box1 = new CheckBox("Bacon");
		 CheckBox box2 = new CheckBox("Tuna");
		 
		button = new Button("ORder now!");
		
		VBox layout = new VBox(10);
		
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(box1, box2, button);
		
		scene = new Scene(layout, 300, 300);
		
		window.setScene(scene);
		
		window.show();
		
	}

}
