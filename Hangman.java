import java.util.Arrays;
import java.util.Scanner;

/**
 * A basic text based command-line Hangman game where the user inputs characters and tries to guess a word.
 * The user gets 7 incorrect guesses. 
 * @author martin
 *
 */
public class Hangman {

	static final String WORD = "REQUIREMENTS"; // the word being guessed
	static final int GUESSES = 7; 			   // the number of guesses the user gets
	static String guessesSoFar = "";           // the users guesses
	
	
	/**
	 * Main routine
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		char guess; 		  // the users guess
		int count = GUESSES;  // counts the number of incorrect guesses.
		
		/**
		 * Prompt the user, telling them what they're playing and explain the game.
		 */
		System.out.println("Let's play Hangman!");
		System.out.println("You have 7 wrong guesses before you lose!\n");
		System.out.println("The word has " + WORD.length() + " letters.");
		System.out.println(underScoreWordConverter(WORD));
		
		
		
		while(true) {
			
			if(count == 0) {
				System.out.println("You lose! You ran out of guesses!");
				break;
			}
			else if( checkIfWon(WORD, guessesSoFar) ) {
				System.out.println("You WON! Congratulations!");
				break;
			}
			
			
				
			System.out.print("What is your guess?\n");
			String input = stdin.next();
			input = input.toUpperCase();
			guess = input.charAt(0);
			
			// if -1 it hasn't been guessed 
			if( (guessesSoFar.indexOf(guess) == -1) ) {
				guessesSoFar += guess;
				
				if((WORD.indexOf(guess) != -1)) {
					System.out.println("Yes, the word contains an " + guess + ".");
				}
				else {
					System.out.println("No, the word DOES NOT contain a " + guess + ".");
					count--; // wrong guess
					System.out.println("You have " + count + " guesses remaining.");
				}
					
			}
			else {
				System.out.println("You've already guessed " + guess + "!\n");
			}
			
			System.out.println(underScoreStringBuilder(guessesSoFar, WORD));
			
			// Sorts guesses by alphabetical order
			System.out.println("Guesses so far: " + sortString(guessesSoFar) + "\n");
			
		}
		
		//System.out.println(underScoreWordConverter(WORD));
		
	} // end main()
	
	
	/**
	 * Takes a word and converts it to underscores
	 * @param word the word to be converted.
	 * @return the underscores.
	 */
	public static String underScoreWordConverter(String word) {
		
		String underScores = "";
		
		for(int i = 0; i < word.length(); i++) {
			underScores += " " + "_";
		}
		
		return underScores;	
	} // end underScoreConverter()
	
	
	/**
	 * Takes in a string where each character is a guess and returns a word
	 * displaying the guesses, and the unguessed characters as underscores.
	 * 
	 * @param guesses the characters the users have guessed, in the form of a string.
	 * @param word the word the user is trying to guess.
	 * @return the string, "_" underscores represent characters not guessed, normal characters represent correct guesses.
	 */
	public static String underScoreStringBuilder(String guesses, String word) {
		
		String newWord = "";

		for(int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			String string = Character.toString(ch);
			
			if( guesses.contains(string) ) {
				newWord += " " + ch;
			}
			else {
				newWord += " _";
			}		
		}
		return newWord;	
	} // end underScoreStringBuilder()
	
	
	/**
	 * Takes in two strings. Checks to see if all the characters in the first are contained in the second.
	 * It returns true if they are, false otherwise.
	 * @param word The word that the user is trying to guess. 
	 * @param guesses The characters the user has guessed so far.
	 * @return haveWon If the user has guessed the word correctly.
	 */
	public static boolean checkIfWon(String word, String guesses) {
		boolean haveWon = true; // true if guesses contains all the characters in word

		for(int i = 0; i < word.length(); i++) {
			
			char ch = word.charAt(i);
			if( guesses.indexOf(ch) == -1 ) {
				haveWon = false;
				break;
			}
			
		}
		return haveWon;
	} // end checkIfWon()

	
	/**
	 * Alphabetically sorts characters in a given string.
	 * @param word the word to be sorted.
	 * @return the sorted string.
	 */
	public static String sortString(String word) {
		char tempArray[] = guessesSoFar.toCharArray();
		Arrays.sort(tempArray);
		String sortedWord = new String(tempArray);
		return sortedWord;
	} // end sortString()
	
	
}
