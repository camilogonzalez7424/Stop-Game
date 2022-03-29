package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
/*import java.net.URL;
import java.util.ResourceBundle;*/

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Answer;
//import model.GameRules;
import model.Generic;
import model.Letter;

public class Ventana1 /*implements Initializable*/ {

	private BufferedWriter bw;
	private BufferedReader br;
	private Answer ownAnswer;
	private Answer opponentAnswer;
	
	//public GameRules game = new GameRules();
	
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
    
    private Ventana2 result;
    
    private Stage primaryStage;
    
    public Ventana1(Ventana2 result) {
    	this.result = result;
    }
    
    public void setStage(Stage s) {
    	primaryStage = s;
    }
    
    public BufferedWriter getWriter() {
		return bw;
	}

	public void setWriter(BufferedWriter writer) {
		this.bw = writer;
	}

	public BufferedReader getReader() {
		return br;
	}

	public void setReader(BufferedReader reader) {
		this.br = reader;
	}
    
    /*@FXML
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
		
    }*/
    
  /*  public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		title.setText("Letra "+game.randomLetter());
		
	}*/
    
    public void stopAction(ActionEvent event)  {
    	if(nameAnswer.getText().isEmpty() ||animalAnswer.getText().isEmpty() || locationAnswer.getText().isEmpty() || objectAnswer.getText().isEmpty()) {
    		
    		//Aletar que es nulo o no completo las palabras XD
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("Look, No text");
    		alert.setContentText("Fill in all the options!");

    		alert.showAndWait();
    	}else {
    		Gson gson = new Gson();
    		Answer ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
    		String word = gson.toJson(ownAnswer);
    		sendMessage(word);
    		
    	}
    }
    
    public void sendMessage(String message) {
    	new Thread(() -> {
			try {
				bw.write(message + "\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
    }
    
    public void readMessage() {
		new Thread(() -> {
			String save;
				try {
					save = br.readLine();
					toDo(save);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
	}
    
    public void toDo(String line) {
		Gson gson = new Gson();
		Generic generic = gson.fromJson(line, Generic.class);
		switch(generic.type) {
		case "Letter": Letter letter = gson.fromJson(line, Letter.class);
		
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
			loader.setController(this);
			Parent p;
			try {
				p = (Parent) loader.load();
				Scene scene = new Scene(p);
				Stage stage = new Stage();
				primaryStage = stage;
				primaryStage.setScene(scene);
				title.setText("Letra " +letter.getLetter());
				stage.show();
				stage.setResizable(false);
				readMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});	
		break;
		case "Answer" : Answer opponentAnswer = gson.fromJson(line, Answer.class);
		if(ownAnswer==null) {
			Answer ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
			String word = gson.toJson(ownAnswer);
			sendMessage(word);
			readMessage();
		}
			Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana2.fxml"));
			loader.setController(result);
			Parent p;
			try {
				p = (Parent) loader.load();
				Scene scene = new Scene(p);
				/*Stage stage = (Stage) anchorPane1.getScene().getWindow();
				stage.close();*/
				primaryStage.setScene(scene);
				result.getOpponentAnimalResult().setText(opponentAnswer.getAnimal());
				result.getOpponentLocationResult().setText(opponentAnswer.getLocation());
				result.getOpponentNameResult().setText(opponentAnswer.getName());
				result.getOpponentObjectResult().setText(opponentAnswer.getObject());
				result.getOwnAnimalResult().setText(ownAnswer.getAnimal());
				result.getOwnLocationResult().setText(ownAnswer.getLocation());
				result.getOwnNameResult().setText(ownAnswer.getName());
				result.getOwnObjectResult().setText(ownAnswer.getObject());
				primaryStage.show();
				primaryStage.setResizable(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});	
			break;
			
		
		}
	}
}
