/*
 * EE422C Project 2 (Mastermind) submission by
 * Chris Karouta
 * cmk2972
 * Slip days used: <0>
 * Fall 2020
 */

// Game runs the entire game when called by Driver. It keeps history of the outputs 
// and maintains the number of guesses until the user wins or loses.

package assignment2;

import java.util.Scanner;

public class Game {
	private boolean test; 
	private Scanner kb;
	private GameConfiguration config; 
	private SecretCodeGenerator generator;
	
	public Game(boolean val, Scanner kb, GameConfiguration config, SecretCodeGenerator generator) {
		test = val;  
		this.kb = kb; 
		this.config = config; 
		this.generator = generator; 
	}
	
	// Runs the entire game by calling other classes when needed
	public void runGame(){
		String secretCode = generator.getNewSecretCode(); 
		boolean stillGoing = true; 
		// print secret code if a test
		if(test) {
			System.out.println("Secret Code: " + secretCode); 
			System.out.println();
		}
		// start game 
		Computer computerPlayer = new Computer(kb, config, secretCode); 
		GameBoard gameBoard = new GameBoard(secretCode, config.pegNumber); 
		int numGuessed = 0; 
		String history[] = new String[config.guessNumber]; 
		while(stillGoing) {
			//ask user and return string array of user's response
			if(numGuessed < config.guessNumber) {
				String[] response = computerPlayer.getUser(numGuessed); 
				//asking for history
				if(response[0].equals("H")) {
					printHistory(history, numGuessed); 
				} else {
					//call function to analyze board
					int[] pegResult = gameBoard.checkBoard(response); 
					if(pegResult[0] == config.pegNumber) {
						System.out.println(secretCode + " -> " + config.pegNumber + "b_0w"); 
						System.out.println("You win!");
						System.out.println();
						stillGoing = false; 
					} else {
						//print out guess
						int n = 0; 
						history[numGuessed] = ""; 
						while(n < config.pegNumber) {
							System.out.print(response[n]); 
							history[numGuessed] += response[n]; 
							n++;
						}
						//print out results
						System.out.print(" -> ");
						history[numGuessed] += " -> "; 		
						System.out.println(pegResult[0] + "b_" + pegResult[1] + "w");
						history[numGuessed] += pegResult[0] + "b_" + pegResult[1] + "w"; 
						numGuessed++;
						if(numGuessed < config.guessNumber) {
							System.out.println();
						}
					}
				} 
			}else { // you lost
				System.out.println("You lose! The pattern was " + secretCode);
				System.out.println(); 
				stillGoing = false; 
			}
			
		}
	}
	
	// prints out the stored history array if user asks
	public void printHistory(String[] history, int numGuessed) {
		int n = 0; 
		while(n < numGuessed) {
			System.out.println(history[n]); 
			n++; 
		}
		System.out.println();
	}
	
	
}
