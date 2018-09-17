import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Hangman {
	String mysteryWord;
	StringBuilder currentGuess;
	ArrayList<Character> previuosGuesses = new ArrayList<>();
	
	int maxTries = 10;
	int currentTry= 0;
	static int wins = 0;
	static int losses = 0;
	
	
	ArrayList<String> dictionary = new ArrayList<>();
	private static FileReader fileReader;
	private static BufferedReader bufferedFileReader;
	
	private static void lose(){
		losses++;
	}
	
	private static void win(){
		wins++;
	}
	
	public Hangman() throws IOException {
		initializeStreams();
		mysteryWord = pickWord();
		currentGuess = initializeCurrentGuess();
		
	}
	
	public void initializeStreams() throws IOException {
		try {
			File inFile = new File("dictionary.txt");
			fileReader = new FileReader(inFile);
			bufferedFileReader = new BufferedReader(fileReader);
			String currentLine = bufferedFileReader.readLine();
			while (currentLine != null){
				dictionary.add(currentLine);
				currentLine = bufferedFileReader.readLine();
			}
			bufferedFileReader.close();
			fileReader.close();
		} catch(IOException e) {
			System.out.println("Could not init system!");
		}
	}
	 
	
	public String pickWord() {
		Random rand = new Random();
		int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
		return dictionary.get(wordIndex);
	}
	
	public StringBuilder initializeCurrentGuess() {
		StringBuilder current = new StringBuilder();
		for(int i =0; i< mysteryWord.length() * 2; i++){
			if( i % 2 == 0) {
				current.append("_");
			}else {
				current.append(" ");
			}
		}
		return current;
	}
	public String getFormalCurrentGuess(){
		return "Current Guess:" + currentGuess.toString();
	}
	
	public boolean gameOver() {
		if (didWeWin()) {
			System.out.println("Congrats, you won! \n");
			win();
			System.out.println("Wins: "+ wins + "  Losses :"+ losses);
			return true;
		}else if (didWeLose()) {
			System.out.println("Sorry, you lost! \n" + "The word was : " + mysteryWord + ".");
			lose();
			System.out.println("Wins: "+ wins + "  Losses :"+ losses);
			return true;
		}
		return false;
	}
	public boolean didWeWin() {
		String guess = getCondensedCurrentGuess();
		return guess.equals(mysteryWord);
	}
	
	public boolean didWeLose() {
		return currentTry >= maxTries;
	}
	
	public String getCondensedCurrentGuess(){
		String guess = currentGuess.toString();
		return  guess.replace(" ", "");
	}
	
	public boolean isGuessedAlready(char guess) {
		return previuosGuesses.contains(guess);
	}
	
	public boolean playGuess(char guess) {
		boolean isItAGoodGuess = false;
		previuosGuesses.add(guess);
		for (int i = 0; i < mysteryWord.length() ; i++){
			if (mysteryWord.charAt(i) == guess){
				currentGuess.setCharAt(i*2, guess);
				isItAGoodGuess = true;
				
			}
		}
		
		if(!isItAGoodGuess) {
			currentTry++;
		}
		System.out.println(currentTry);
		return isItAGoodGuess;
	}

	
	
	
	
	
}