/*
 * EE422C Project 2 (Mastermind) submission by 
 * Chris Karouta
 * cmk2972
 * Slip days used: <0>
 * Fall 2020
 */

// Computer asks the user for their guess when called by Game, and it checks the validity 
// of their input, returning a string array of their guess if valid. 

package assignment2;

import java.util.Scanner;

public class Computer {
	private Scanner kb;
	private GameConfiguration config; 
	private String secretCode; 
	
	public Computer(Scanner kb, GameConfiguration config, String secretCode) {
		this.kb = kb;  
		this.config = config; 
		this.secretCode = secretCode; 
	}
	
	//asks the user and returns a string array of users input or "H" in [0] if asking for history
	public String[] getUser(int numGuessed) {
		// read in guess 
		String guess = askUser(numGuessed);
		boolean valid = false;
		boolean history = false; 
		//checks if we have a valid input or need to reask
		while(!valid) { 
			int n = 0; 
			//check if asking for history
			if(guess.equals("HISTORY")) {
				history = true; 
			}
			//checks if input is a letter in color array
			boolean finalValid = true; 
			while(n < config.pegNumber && finalValid && !history && guess.length() == secretCode.length()) {
				boolean inColorArray = false; 
				char chose = guess.charAt(n); 
				int j = 0; 
				while(j < config.colors.length) {
					char colorsArray = config.colors[j].charAt(0); 
					if(chose == colorsArray && !inColorArray) {
						inColorArray = true; 
					}
					j++;
				}
				n++; 
				finalValid = inColorArray; 
			}
			// if the input is not the correct length, and they're not asking for history then invalid
			if(guess.length() != secretCode.length() && !history) {
				finalValid = false; 
			}
			// if invalid input, ask again and rerun 
			if(!finalValid) {
				System.out.println("INVALID_GUESS"); 
				System.out.println(); 
				guess = askUser(numGuessed); 
			}else {
				valid = true; 
			}
			
		}
		// fill in string array 
		String realGuess[] = new String[config.pegNumber];
		if(history) {
			realGuess[0] = "H";
		}else { 
			int n = 0; 
			while(n < config.pegNumber) {
				char chose = guess.charAt(n);
				realGuess[n] = Character.toString(chose); 
				n++; 
			}
		}
		return realGuess; 
	}
	
	//asks user for their input and returns a string
	public String askUser(int numGuessed) {
		int numGuesses = config.guessNumber - numGuessed; 
		System.out.println("You have " + numGuesses + " guess(es) left.");
		System.out.println("Enter guess: ");
		return kb.next();
	}
}
