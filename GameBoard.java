/*
 * EE422C Project 2 (Mastermind) submission by
 * Chris Karouta
 * cmk2972
 * Slip days used: <0>
 * Fall 2020
 */

// GameBoard receives the string input from the user, and compares that to the actual board.
// It returns a 2 int array to represent how many black and white pegs. 

package assignment2;

public class GameBoard {
	private String secretCode; 
	private int numPegs; 
	
	
	public GameBoard(String secretCode, int numPegs) {
		this.secretCode = secretCode; 
		this.numPegs = numPegs;  
	}
	
	//compares user's input to actual board, returns size 2 int array [0, 1] for 0 black, 1 white
	public int[] checkBoard(String[] input) {
		//turn string of secret codes into array of one char strings
		int n = 0; 
		String[] colors = new String[secretCode.length()]; 
		while(n < secretCode.length()) {
			char chose = secretCode.charAt(n);
			colors[n] = Character.toString(chose); 
			n++; 
		}
		int[] pegResult = new int[2]; 
		//check for black pegs
		boolean alreadyFoundBlack[] = new boolean[numPegs];  
		for(int i = 0; i < input.length; i++) {
			if(input[i].equals(colors[i])) {
				pegResult[0]++; 
				alreadyFoundBlack[i] = true;
			}
		}
		//check for white pegs
		boolean alreadyFoundWhiteInput[] = new boolean[numPegs];
		boolean alreadyFoundWhiteSecret[] = new boolean[numPegs];
		for(int i = 0; i < input.length; i++) {  
			for(int j = 0; j < colors.length; j++) {
				if((input[i].equals(colors[j])) && (i != j) && !alreadyFoundWhiteSecret[j] && !alreadyFoundBlack[j] && !alreadyFoundWhiteInput[i] && !alreadyFoundBlack[i]){
					alreadyFoundWhiteSecret[j] = true; 
					alreadyFoundWhiteInput[i] = true; 
					pegResult[1]++; 
				}
			} 
		}
		return pegResult; 
	}
	
}
