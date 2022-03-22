package model;


public class gameRules {
	public char letter;
	

	public char randomLetter() {
		char letter = (char)(Math.random()*26 + 'A');
		return letter;
	}
	
	
	
}
