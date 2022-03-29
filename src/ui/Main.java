package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import action.OnMessageListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//This a Client..
public class Main extends Application{

	private BufferedWriter bw;
	private BufferedReader br;
	private OnMessageListener message;
	private Ventana1 controller1;
	private Ventana2 controller2;
	private Inicio inicio;
	
	public OnMessageListener getMessage() {
		return message;
	}
	
	public void setMessage(OnMessageListener m) {
		this.message = m;
	}
	
	public Main() {
		inicio = new Inicio();
		controller2 = new Ventana2();
		controller1 = new Ventana1(controller2);
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("Ventana1.fxml"));
		
		Parent p = (Parent) loader.load();
		
		Scene scene = new Scene(p);
		
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
		
		
	}*/
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Socket socket = new Socket("127.0.0.1", 6000);

			inicio.welcome(primaryStage);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			controller1.setStage(primaryStage);
			controller1.setWriter(bw);
			controller1.setReader(br);
			controller1.readMessage();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(() -> {
			
		}).start();	
	
	}
	
}