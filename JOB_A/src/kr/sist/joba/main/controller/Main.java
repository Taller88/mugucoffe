package kr.sist.joba.main.controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/sist/joba/main/view/Start.fxml"));
		Parent root = loader.load(); 
		Scene scene = new Scene(root); 
		
		primaryStage.setTitle("MUGU COFFEE"); 
		primaryStage.setScene(scene);
		primaryStage.setResizable(true); //화면 크기 고정 true
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}