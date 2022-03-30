package model;

import java.util.Random;

import com.google.gson.Gson;

import TCPJson.Session;
import action.OnMessageListener;
import action.OnMessageSend;

public class GameRules {
	
	private final static int NO_SAME_ANSWER	= 100;
	private final static int SAME_ANSWER 	= 50;
	private final static int BLANK_ANSWERED	= 0;
	
	private Session player1;
	private Session player2;
	private OnMessageSend message1;
	private OnMessageSend message2;
	private OnMessageListener text1;
	private OnMessageListener text2;
	private Thread ventana1;
	private Thread ventana2;
	
	public GameRules() {
		
	}
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
	
	public void score(Answer answer1 , Answer answer2) {
		
		//Word igual 50, Vacio 0 y Diferente 100
		
		//Name
        int[] word = score(answer1.getName(),answer2.getName());
        answer1.setNameScore(word[0]);
        answer2.setNameScore(word[1]);

        
        //Animal
        word	= score(answer1.getAnimal(),answer2.getAnimal());
        answer1.setAnimalScore(word[0]);
        answer2.setAnimalScore(word[1]);

        
        
        //Location
        word	= score(answer1.getLocation(),answer2.getLocation());
        answer1.setLocationScore(word[0]);
        answer2.setLocationScore(word[1]);
        
        
        //object
        word	= score(answer1.getObject(),answer2.getObject());
        answer1.setObjectScore(word[0]);
        answer2.setObjectScore(word[1]);
		
        answer1.setTotalScore();
        answer2.setTotalScore();
		
	}
	
	private int[] score(String answer1, String answer2) {
		int[] word = new int[2];
        if((answer1.equalsIgnoreCase("") && answer2.equalsIgnoreCase("")) || (answer1.equalsIgnoreCase(" ") && answer2.equalsIgnoreCase(" ")) ) {
        	word[0] = BLANK_ANSWERED;
        	word[1] = BLANK_ANSWERED;
        }else if(answer1.equalsIgnoreCase("") || answer1.equalsIgnoreCase(" ")) {
        	word[0] = BLANK_ANSWERED;
        	word[1] = NO_SAME_ANSWER;
        }else if(answer2.equalsIgnoreCase("") || answer2.equalsIgnoreCase(" ")){
        	word[0] = NO_SAME_ANSWER;
        	word[1] = BLANK_ANSWERED;
        }else if(answer1.equalsIgnoreCase(answer2)){
        	word[0] = SAME_ANSWER;
        	word[1] = SAME_ANSWER;
        }else{
        	word[0] = NO_SAME_ANSWER;
        	word[1] = NO_SAME_ANSWER;
        }
        return word;
		
		
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
