import java.io.IOException;
import java.util.Scanner;

public class HangmanApp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to hangman!");
		System.out.println();
		System.out.println();
		//Allows for multiple games
		boolean doYouWantToPlay = true;
		while(doYouWantToPlay) {
			//keep playing
			Hangman game = new Hangman();
			do {
				System.out.println();
				System.out.println(game.getFormalCurrentGuess());
				//Get guess
				System.out.println("Enter a character: ");
				char guess = (sc.next().toLowerCase().charAt(0));
				if ( guess >='a' && guess <='z') {
				} else {
					System.out.println("You have to enter character from alphabet.");
				}
				//check guess is not repeated
				while (game.isGuessedAlready(guess)) {
					System.out.println("Try again! You've already guessed that character.");
					guess = (sc.next().toLowerCase().charAt(0));
				}
				// play guess
				if (game.playGuess(guess)) {
					System.out.println("Great guess!");
				}else {
					System.out.println("Wrong!");
				}
			}
			while (!game.gameOver()); // keep playing
				
			
			//Play again or no?
			System.out.println();
			System.out.println("Do you want to play another game? Enter Y if you do.");
			Character response = (sc.next().toUpperCase().charAt(0));
			doYouWantToPlay = (response == 'Y');
		}

	}

}
