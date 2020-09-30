/*
 * EE422C Project 2 (Mastermind) submission by 
 * Chris Karouta
 * cmk2972
 * Slip days used: <0>
 * Fall 2020
 */

// Driver starts the game creates a new one if the user asks

package assignment2;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
    	boolean test = true; 
    	String[] colors =  {"R", "O", "Y", "G", "B", "P"}; 
    	GameConfiguration config = new GameConfiguration(6, colors, 4);  
    	SecretCodeGenerator generator = new SecretCodeGenerator(config);
    	start(test, config, generator); 
    }

    // calls a new Game if user asks 
    public static void start(Boolean isTesting, GameConfiguration config, SecretCodeGenerator generator) {
    	System.out.println("Welcome to Mastermind.");
    	Scanner kb = new Scanner(System.in);
    	boolean playAgain = true; 
    	while(playAgain) {    		
	    	System.out.println("Do you want to play a new game? (Y/N):");
	    	char input = kb.next().charAt(0); 
	    	if(input == 'Y') {
	    		playAgain = true; 
	    		Game newGame = new Game(isTesting, kb, config, generator); 
	    		newGame.runGame(); 
	    	} else {
	    		playAgain = false; 
	    	}
    	}
    }
}