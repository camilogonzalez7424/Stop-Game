package model;

import java.util.Random;

import com.google.gson.Gson;

import TCPJson.Session;
import action.OnMessageListener;
import action.OnMessageSend;

public class GameRules {
	
	private Session player1;
	private Session player2;
	private OnMessageSend message1;
	private OnMessageSend message2;
	private OnMessageListener text1;
	private OnMessageListener text2;
	private Thread ventana1;
	private Thread ventana2;
	
	
	public GameRules(Session player1, Session player2) {
		this.player1 	= player1;
		this.player2	= player2;
		this.message1	= player1;
		this.text1 		= player1;
		this.message2	= player2;
		this.text2		= player2;
		sendLetter();
	}

	public void sendLetter() {

	        Random random = new Random();
	        char randomChar = (char) (random.nextInt(26) + 'A');
	        Gson gson = new Gson();
	        Letter letter = new Letter() ;
	        letter.setLetter(Character.toString(randomChar));
	        String j = gson.toJson(letter);
	        message1.messageSend(j);
	        message2.messageSend(j);
			playGame();
	    }
	
	public void Score() {
		
		//Word igual 50, Vacio 0 y Diferente 100
		
		
	}
	
	public void playGame(){
		ventana1 = new Thread() {
            public void run() {
                synchronized(this) {
                    String a = text1.messageListener();
                    if(a.contains("Result")) {
                    	ventana2.interrupt();
                    }
                    
                    message2.messageSend(a);
                    String b = text2.messageListener();
                    message1.messageSend(b);
                }
            }
        };


        ventana2 = new Thread() {
            public void run() {
                synchronized(this) {
                    String a = text2.messageListener();
                    if(a.contains("Result")) {
                    	ventana1.interrupt();
                    }
                    
                    message1.messageSend(a);
                    String b = text1.messageListener();
                    message2.messageSend(b);
                }
            }
        };
        ventana1.start();
        ventana2.start();
    }
	
	
}
