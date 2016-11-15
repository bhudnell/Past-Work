import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A model of the tray of dice in the game Boggle. A DiceTray can be constructed
 * with a 4x4 array of characters for testing. A 2nd constructor with no
 * arguments simulates the shaking of 16 dice and selection of one of the 6
 * sides.
 * 
 * @author Brendon Hudnell and Rick Mercer
 */

public class BoggleTray {

	private char[][] path;
	private char[][] board;
	public static final char TRIED = '@';
	public static final char PART_OF_WORD = '!';
	public static final int SIZE = 4;
	public static final int NUMBER_SIDES = 6;
	private List<char[]> diceArray = new ArrayList<>();
	private char[][] theDice = { { 'L', 'R', 'Y', 'T', 'T', 'E' }, { 'V', 'T', 'H', 'R', 'W', 'E' },
			{ 'E', 'G', 'H', 'W', 'N', 'E' }, { 'S', 'E', 'O', 'T', 'I', 'S' }, { 'A', 'N', 'A', 'E', 'E', 'G' },
			{ 'I', 'D', 'S', 'Y', 'T', 'T' }, { 'O', 'A', 'T', 'T', 'O', 'W' }, { 'M', 'T', 'O', 'I', 'C', 'U' },
			{ 'A', 'F', 'P', 'K', 'F', 'S' }, { 'X', 'L', 'D', 'E', 'R', 'I' }, { 'H', 'C', 'P', 'O', 'A', 'S' },
			{ 'E', 'N', 'S', 'I', 'E', 'U' }, { 'Y', 'L', 'D', 'E', 'V', 'R' }, { 'Z', 'N', 'R', 'N', 'H', 'L' },
			{ 'N', 'M', 'I', 'H', 'U', 'Q' }, { 'O', 'B', 'B', 'A', 'O', 'J' } };

	/**
	 * Construct a tray of dice using a hard coded 2D array of chars. Use this
	 * for testing
	 * 
	 * @param newBoard
	 *            The 2D array of characters used in testing
	 */
	public BoggleTray(char[][] newBoard) {
		board = newBoard;
	}

	/**
	 * Constructor for actual game use. Simulates shaking 16 dice and selection
	 * of one of the 6 sides.
	 */
	public BoggleTray() {
		board = new char[4][4];
		buildDiceArray();
		Collections.shuffle(diceArray);
		Random letter = new Random();
		int die = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				char[] temp = diceArray.get(die);
				board[i][j] = temp[letter.nextInt(NUMBER_SIDES)];
				die++;
			}
		}
	}

	/**
	 * A private helper method that sets the ArrayList dice to hold a
	 * representation of the 16 actual dice used in Boggle.
	 */
	private void buildDiceArray() {
		for (int i = 0; i < 16; i++)
			diceArray.add(theDice[i]);
	}

	/**
	 * Provide a textual version of this BoggleTray
	 */
	@Override
	public String toString() {
		String result = "\n";
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] == 'Q')
					result += " Qu";
				else
					result += "  " + board[r][c];
			}
			result += " \n\n";
		}
		return result;
	}

	/**
	 * Return true if search is word that can found on the board following the
	 * rules of Boggle
	 * 
	 * @param str
	 *            A word that may be in the board by connecting consecutive
	 *            letters
	 * 
	 * @return True if str is found in the tray according to the rules of Boggle
	 */
	public boolean foundInBoggleTray(String str) {
		if (str.length() < 3)
			return false;
		String attempt = str.toUpperCase().trim();
		boolean found = false;
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++)
				if (board[r][c] == attempt.charAt(0)) {
					// Set up another 2d array to mark tried spots
					initializeTriedLocations();
					// Use recursive backtracking to find word in the tray
					found = recursiveSearch(r, c, attempt);
					// Make sure you don't return false because the word may
					// begin in a later spot
					// In other words, do not return false if a first search
					// fails
					if (found) {
						return true;
					}
				}
		}
		return false;
	}

	// Keep a 2nd 2D array to remember the characters that have been tried
	private void initializeTriedLocations() {
		path = new char[SIZE][SIZE];
		for (int r = 0; r < SIZE; r++)
			for (int c = 0; c < SIZE; c++)
				path[r][c] = '.';
	}

	// This is like the Obstacle course escape algorithm. Now you need to
	// checking eight possible directions instead of only four.
	//
	// WrapAround is not required.
	//
	// Hint: Treat 'Qu' as the char 'Q' somehow.
	//
	private boolean recursiveSearch(int row, int column, String attempt) {
		boolean found = false;
		if (attempt.length() == 0)
			return true;

		char looking = attempt.charAt(0);

		if (path[row][column] == '.' && board[row][column] == looking) {
			path[row][column] = TRIED;

			if (looking == 'Q')
				attempt = attempt.substring(2);
			else
				attempt = attempt.substring(1);

			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (row + i >= 0 && row + i < SIZE && column + j >= 0 && column + j < SIZE && (i != 0 || j != 0)
							&& !found) {
						found = recursiveSearch(row + i, column + j, attempt);
					}
				}
			}

			if (found)
				path[row][column] = PART_OF_WORD;
		}
		return found;
	}

}