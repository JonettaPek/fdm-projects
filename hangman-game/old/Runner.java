import java.util.ArrayList;
import java.util.Scanner;

public class Runner {


	
	public static void main(String[] args) {
		
		// Main program
		
		System.out.println("Welcome to Hangman!");

//		System.out.println(HangMan.getRandomWord());
		
		String randomWord = HangMan.getRandomWord();
		
		String message = "The word now looks like this: ";
		
		String  hiddenWord = HangMan.generateHiddenWord(randomWord);
		
		
		while ( HangMan.getNumberOfGuessesLeft() <= HangMan.getMaximumNumberOfGuesses() ) {
			
			if ( HangMan.getNumberOfGuessesLeft() != 0 ) {
				
				System.out.println(message + hiddenWord);
				
				HangMan.displayNumberOfGuessesLeft(HangMan.getNumberOfGuessesLeft());
				
				Scanner scanner = new Scanner(System.in);
				
				String input = scanner.nextLine();
				
				HangMan.displayGuess(input);
				
				Character inputChar = HangMan.convertToUpperCase(input);
				
				HangMan.addGuess(inputChar);
			
				HangMan.checkGuess(inputChar);
				
				hiddenWord = HangMan.handleHiddenWord(HangMan.getRandomWord(), HangMan.getInputLetters());
			
			} 
			
			if ( HangMan.getNumberOfGuessesLeft() == 0 && hiddenWord.contains(HangMan.getRandomWord()) ) {
				
				HangMan.displayWin(hiddenWord);
				
				break;
				
			} else if ( HangMan.getNumberOfGuessesLeft() != 0 && hiddenWord.contains(HangMan.getRandomWord())) {
				
				HangMan.displayWin(hiddenWord);
				
				break;
				
			}  else if ( HangMan.getNumberOfGuessesLeft() == 0 && hiddenWord.contains(HangMan.getRandomWord()) == false ) {
				
				HangMan.displayNumberOfGuessesLeft(HangMan.getNumberOfGuessesLeft());
				
				HangMan.displayLost();
				
				break;
			}
		} 

	}

}
