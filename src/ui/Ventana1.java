package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.gameRules;

public class Ventana1 implements Initializable {

	public gameRules game = new gameRules();
	
	@FXML
	private AnchorPane anchorPane1;
	 
	@FXML
    private Label title;

    @FXML
    private Button stopBtn;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField objectAnswer;
    
    @FXML
    public void stopAction(ActionEvent event)  {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("Ventana2.fxml"));
		
		Parent p;
		try {
			p = (Parent) loader.load();
			
			Scene scene = new Scene(p);
			
			Stage stage = (Stage) anchorPane1.getScene().getWindow();

			
			stage.setScene(scene);
			stage.show();
			
			//Stage stage1 = (Stage) anchorPane1.getScene().getWindow();
	    	//stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		title.setText("Letra "+game.randomLetter());
		
	}
}
