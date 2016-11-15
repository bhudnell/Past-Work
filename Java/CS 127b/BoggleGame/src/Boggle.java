import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A model of the game Boggle. A Boggle game can be constructed with a pre-made
 * BoggleTray for testing by calling SetBoggleTray(BoggleTray tray). Otherwise
 * the tray is randomized using the real Boggle dice.
 * 
 * @author Brendon Hudnell
 */

public class Boggle {

	private BoggleTray boggleTray;
	private List<String> userGuesses;
	private List<String> boggleWords;
	private List<String> foundWords;
	private List<String> incorrectWords;
	private List<String> missedWords;

	// Initializes a game with 16 dice to simulate the real game with the real
	// Boggle dice.
	public Boggle() {
		boggleTray = new BoggleTray();
		userGuesses = new LinkedList<>();
		boggleWords = new ArrayList<>();
		foundWords = new LinkedList<>();
		incorrectWords = new LinkedList<>();
		missedWords = new LinkedList<>();

		try {
			Scanner inFile = new Scanner(new File("BoggleWords"));

			while (inFile.hasNextLine())
				boggleWords.add(inFile.next().toLowerCase().trim());

			inFile.close();

		} catch (FileNotFoundException e) {
			// System.out.println("\"BoggleWords.txt\" was not found.");
		}
	}

	// Allow testers to set the BoggleTray to a known one (not random).
	public void setBoggleTray(BoggleTray dt) {
		this.boggleTray = dt;
	}

	// Return the BoggleTray object as a textual representation as a String
	public String getBoggleTrayAsString() {
		return boggleTray.toString();
	}

	// Record one word (a string with no whitespace) as one of the users'
	// guesses.
	public void addGuess(String oneGuess) {
		if (!userGuesses.contains(oneGuess.toLowerCase().trim()))
			userGuesses.add(oneGuess.toLowerCase().trim());
	}

	// Return a list of all words the user entered that count for the score. The
	// found words must be in their natural ordering. This method should return
	// an empty list until addGuess(String) is called at least once. The
	// returned List<E> could also be empty if no attempts actually counted for
	// anything. There must be no duplicates!
	public List<String> getWordsFound() {
		for (int i = 0; i < userGuesses.size(); i++) {
			String guess = userGuesses.get(i);
			if (boggleTray.foundInBoggleTray(guess) && Collections.binarySearch(boggleWords, guess) >= 0
					&& !foundWords.contains(guess))
				foundWords.add(guess);
		}
		Collections.sort(foundWords);
		return foundWords;
	}

	// Return a list of all words the user entered that do not count for the
	// score in their natural order. This list may be empty with no words
	// guessed or all guessed words actually count for points. There must be no
	// duplicates!
	public List<String> getWordsIncorrect() {
		for (int i = 0; i < userGuesses.size(); i++) {
			String guess = userGuesses.get(i);
			if ((!boggleTray.foundInBoggleTray(guess) || Collections.binarySearch(boggleWords, guess) < 0)
					&& !incorrectWords.contains(guess))
				incorrectWords.add(guess);
		}
		Collections.sort(incorrectWords);
		return incorrectWords;
	}

	// All words the user could have guessed but didn't in their natural order.
	// This list could also be empty at first or if the user guessed ALL words
	// in the board and in the list of 80K words (unlikely). There must be no
	// duplicates!
	public List<String> getWordsNotGuessed() {
		for (int i = 0; i < boggleWords.size(); i++) {
			String word = boggleWords.get(i);
			if (!userGuesses.contains(word) && boggleTray.foundInBoggleTray(word) && !missedWords.contains(word))
				missedWords.add(word);
		}
		return missedWords;
	}

	// Return the correct score.
	public int getScore() {
		int score = 0;
		getWordsFound();
		for (int i = 0; i < foundWords.size(); i++) {
			String word = foundWords.get(i);
			if (word.length() == 3 || word.length() == 4)
				score += 1;
			else if (word.length() == 5)
				score += 2;
			else if (word.length() == 6)
				score += 3;
			else if (word.length() == 7)
				score += 5;
			else if (word.length() > 7)
				score += 11;
		}
		return score;
	}
}