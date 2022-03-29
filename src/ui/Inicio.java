package ui;

import java.io.IOException;

import javax.tools.JavaFileManager.Location;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Inicio{
	
     
	public void welcome(Stage primaryStage) {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
		//loader.setController(this);
		Parent p;

		try {

			p = (Parent) loader.load();
			Scene scene = new Scene(p);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);

			//primaryStage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}



			
	}
    

