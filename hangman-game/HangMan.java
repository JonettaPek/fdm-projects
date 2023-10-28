import java.util.*;

public class HangMan {
	
	// Variables
	
	private static final int MAXIMUM_NUMBER_OF_GUESSES = 8; // Number of guesses
	
	private static int numberOfGuesses = 0; // Counter for attempts made
	
	private static int numberOfGuessesLeft = 8; // Counter for remaining number of guesses
	
	private static ArrayList<Character> inputLetters = new ArrayList<Character>(); // List of input letters guessed previously
	
	private static String[] words = {"COMPUTER", "FUZZY"}; // Pre-populated list of words
	
	private static final String RANDOM_WORD = words[HangMan.generateIndex(words.length)];

	
	//Getter methods
	
	public static int getMaximumNumberOfGuesses() {
		return MAXIMUM_NUMBER_OF_GUESSES;
	}
	
	public static int getNumberOfGuesses() {
		return numberOfGuesses;
	}
	
	public static int getNumberOfGuessesLeft() {
		return numberOfGuessesLeft;
	}
	
	public static ArrayList<Character> getInputLetters() {
		return inputLetters;
	}
	
	public static String[] getWords() {
		
		return words;
	}
	
	public static String getRandomWord() {
		
		return RANDOM_WORD;
	}
	
	// Generate random number
	
	static int generateIndex(int arrayLength) {
		
		Random randomGenerator = new Random();
		
		int index = randomGenerator.nextInt(arrayLength); // exclusive
		
		return index;
	}	

	// Generate string of dashes
	
	static String generateHiddenWord(String randomWord) {
		
		char[] hiddenWord = new char[randomWord.length()];
		
		Arrays.fill(hiddenWord, '-');
		
		return String.valueOf(hiddenWord);
		
	}

	// Display number of guesses left
	
	public static void displayNumberOfGuessesLeft(int numberOfGuessesLeft) {

		if ( numberOfGuessesLeft == 1 ) {
			
			System.out.println("You have only one guess left.");
			
		} else if ( numberOfGuessesLeft == 0 ) {
			
			System.out.println("You're completely hung.");
			
		} else {
			
			System.out.println("You have " + numberOfGuessesLeft + " guesses left.");
			
		}
		
	}
	
	// Display guess
	
	public static void displayGuess(String input) {
		
		System.out.println("Your guess: " + input);
	}
	
	// Convert guess to upper case for consistency 
	
	public static Character convertToUpperCase(String input) {
		
		Character inputChar = input.toUpperCase().charAt(0);
		
		return inputChar;
	}
	
	// Add guess to list of previous guesses
	
	public static void addGuess(Character inputChar) {
		
		if ( inputLetters.contains(inputChar) && ! RANDOM_WORD.contains(Character.toString(inputChar))) {
			
			System.out.println("You have entered " + inputChar + " before. Please try again.");
			
			numberOfGuessesLeft++;
			
		} else if ( inputLetters.contains(inputChar) && RANDOM_WORD.contains(Character.toString(inputChar)) ) {
			
			System.out.println("You have entered " + inputChar + " before. Please try again.");
			
		} else {
		
			
			inputLetters.add(inputChar);
		}
	}
	
	// Check guess in random word
	
	public static void checkGuess(Character inputChar) {
		
		if ( RANDOM_WORD.contains(Character.toString(inputChar)) ) {
			
			System.out.println("That guess is correct.");
			
			numberOfGuesses++;
			
		} else {
			
			numberOfGuesses++;
			
			numberOfGuessesLeft--;
			
			System.out.println("There are no " + inputChar + "'s in the word.");
			
		}
	}
	
	// Handle hidden word
	
	static String handleHiddenWord(String randomWord, ArrayList<Character> inputLetters) {
		
		char[] hiddenWord = new char[randomWord.length()];
		
		Arrays.fill(hiddenWord, '-');
		
		if ( numberOfGuessesLeft >= 1 && numberOfGuessesLeft <= MAXIMUM_NUMBER_OF_GUESSES ) {
				
			outer: for ( int i = 0; i < randomWord.length(); i++ ) {
				
				for ( int j = 0; j < inputLetters.size(); j++ ) {
					
					if ( randomWord.charAt(i) == inputLetters.get(j) ) {
						
						hiddenWord[i] = randomWord.charAt(i);
						
						continue outer;
						
					}
				}	
			}
		}
		
		return String.valueOf(hiddenWord);
	}
	
	// Display win status
	
	static void displayWin(String hiddenWord) {
		
		System.out.println("You guessed the word: " + hiddenWord);
		
		System.out.println("You win.");
	}
	
	// Display lost status
	
	static void displayLost() {
		
		System.out.println("The word was: " + HangMan.getRandomWord());
	
		System.out.println("You lose.");
		
	}
	
}