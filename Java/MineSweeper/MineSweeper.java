import java.util.Random;
import java.util.Stack;

/**
 * This class represents the model for a game of MineSweeper. It has a
 * constructor that takes a preset boolean 2D array where true means there is a
 * mine. This first constructor (you'll need 2) is for testing the methods of
 * this class.
 * 
 * The second constructor that takes the number of rows, the number of columns,
 * and the number of mines to be set randomly in that sized mine field. Do this
 * last.
 * 
 * @author Brendon Hudnell
 */
public class MineSweeper implements MineSweeperModel {

	private class GameSquare {

		private boolean isMine;
		private int row;
		private int col;
		private boolean isVisible;
		private boolean isFlagged;
		private int mineNeighbors;

		// Construct a GameSquare object with all values initialized except
		// mineNeighbors, which is an instance variables that can only be set
		// after
		// all
		// GameSquare objects have been constructed in the 2D array.
		public GameSquare(boolean isMine, int row, int col) {
			this.isMine = isMine;
			this.row = row;
			this.col = col;
			isVisible = false; // Default until someone starts clicking
			isFlagged = false; // Default until someone starts clicking
			// call setAdjacentMines() from both constructors
			// to set this for each new GameSquare.
			mineNeighbors = 0;
		}
	}

	// The instance variable represents all GameSquare objects where each knows
	// its row,
	// column, number of mines around it, if it is a mine, flagged, or visible
	private GameSquare[][] board;
	private boolean lost = false;

	/**
	 * Construct a MineSweeper object using a given mine field represented by an
	 * array of boolean values: true means there is mine, false means there is
	 * not a mine at that location.
	 * 
	 * @param mines
	 *            A 2D array to represent a mine field so all methods can be
	 *            tested with no random placements.
	 */
	public MineSweeper(boolean[][] mines) {
		board = new GameSquare[mines.length][mines[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)
				board[i][j] = new GameSquare(mines[i][j], i, j);
		}
		setAdjacentMines();
	}

	/**
	 * Use the almost initialized 2D array of GameSquare objects to set the
	 * instance variable mineNeighbors for every 2D array element (even if that
	 * one GameSquare has a mine). This is similar to GameOfLife neighborCount.
	 */
	private void setAdjacentMines() {
		int rows = board.length;
		int cols = board[0].length;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int mineCount = 0;
				for (int i = r - 1; i <= r + 1; i++) {
					for (int j = c - 1; j <= c + 1; j++) {
						if (!(i == r && j == c) && i > -1 && i < rows && j > -1 && j < cols && board[i][j].isMine)
							mineCount++;
					}
				}
				board[r][c].mineNeighbors = mineCount;
			}
		}
	}

	/**
	 * This method returns the number of mines surrounding the requested
	 * GameSquare (the mineNeighbors value of the square). A square with a mine
	 * may return the number of surrounding mines, even though it will never
	 * display that information.
	 * 
	 * @param row
	 *            - An int value representing the row in board.
	 * @param column
	 *            - An int value representing the column in board.
	 * @return The number of mines surrounding to this GameSquare
	 *         (mineNeighbors)
	 * 
	 *         Must run O(1)
	 */
	public int getAdjacentMines(int row, int column) {
		return board[row][column].mineNeighbors;
	}

	/**
	 * Construct a MineSweeper of any size that has numberOfMines randomly set
	 * so we get different games.
	 * 
	 * @param rows
	 *            Height of the board
	 * @param columns
	 *            Width of the board
	 * @param numberOfMines
	 *            How m any mines are to randomly placed
	 */
	public MineSweeper(int rows, int columns, int numberOfMines) {
		board = new GameSquare[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				board[i][j] = new GameSquare(false, i, j);
		}

		Random generator = new Random();
		int count = 0;
		while (count < numberOfMines) {
			int r = generator.nextInt(rows);
			int c = generator.nextInt(columns);
			if (board[r][c].isMine == false) {
				board[r][c].isMine = true;
				count++;
			}
		}
		setAdjacentMines();
	}

	/**
	 * This method returns the number of mines found in the game board.
	 * 
	 * @return The number of mines.
	 */
	public int getTotalMineCount() {
		int totalMines = 0;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c].isMine)
					totalMines++;
			}
		}
		return totalMines;
	}

	/**
	 * This method returned whether or not the square has been flagged by the
	 * user. Flags are a tool used by players to quickly tell which squares they
	 * think contain mines as well as prevent accidental clicking on those
	 * squares.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean value representing the flagged state of this location.
	 */
	public boolean isFlagged(int row, int column) {
		return board[row][column].isFlagged;
	}

	public void toggleFlagged(int row, int column) {
		if (board[row][column].isFlagged)
			board[row][column].isFlagged = false;
		else
			board[row][column].isFlagged = true;
	}

	/**
	 * This method determines if the square in question is a mine.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing the mine status of the square.
	 */
	public boolean isMine(int row, int column) {
		return board[row][column].isMine;
	}

	/**
	 * This method gets the visibility of the square in question. Visibilty is
	 * initially defined for all squares to be false and uncovered when the
	 * click method checks the square.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 * @return A boolean representing whether or not the square is set to be
	 *         visible.
	 */
	public boolean isVisible(int row, int column) {
		return board[row][column].isVisible;
	}

	/**
	 * This method determines if the player has lost on the current board. A
	 * player loses if and only if they have clicked on a mine.
	 * 
	 * @return A boolean representing player failure.
	 */
	public boolean lost() {
		return lost;
	}

	/**
	 * Returns a textual representation of the GameBoard. Squares will be
	 * represented by one character followed by a space, except the last
	 * character which will have no following space. Not visible squares will be
	 * an 'X' character, visible squares will either be the number of mines next
	 * to the square, a blank space if no mines are adjacent, or a '*' character
	 * for a mine. Newlines will separate each row of the game board.
	 * 
	 * @return A String representation of the game board data.
	 */
	public String toString() {
		String gameBoard = "";
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (c != 0)
					gameBoard += " ";
				if (!board[r][c].isVisible)
					gameBoard += "X";
				else if (board[r][c].isMine)
					gameBoard += "*";
				else
					gameBoard += "" + board[r][c].mineNeighbors;
			}
			gameBoard += "\n";
		}
		return gameBoard;
	}

	/**
	 * This method determines if a player has won the game. Winning means all
	 * non-mine squares are visible and no mines have been detonated.
	 * 
	 * @return A boolean representing whether or not the player has won.
	 */
	public boolean won() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (!board[r][c].isVisible && !board[r][c].isMine)
					return false;
			}
		}
		return true;
	}

	/**
	 * This method alerts the Game Board the user has clicked on the square at
	 * the given row/column. There are five possibilities for updating the board
	 * during the click messages to your MineSweeper. The GameSquare object
	 * stored at the just clicked row and column
	 * 
	 * 1. is flagged (do nothing)
	 * 
	 * 2. is a mine (player looses)
	 * 
	 * 3. is visible already (do nothing)
	 * 
	 * 4. has mineNeighbors >- 1 (simply mark that visible)
	 * 
	 * 5. is not adjacent to any mines with mineNeighbors == 0 (mark many
	 * visible)
	 * 
	 * Because MineSweeper automatically clears all squares adjacent to any
	 * blank square connected to the square clicked, a special algorithm is
	 * needed to set the proper part of the board visible. This pseudo-code
	 * shows the suggested algorithm.
	 * 
	 * @param row
	 *            - An int value representing the row (x) value in the game
	 *            board.
	 * @param column
	 *            - An int value representing the column (y) value in the game
	 *            board.
	 */
	public void click(int row, int column) {
		if (board[row][column].isFlagged)
			return;
		else if (board[row][column].isVisible)
			return;
		else if (board[row][column].isMine) {
			board[row][column].isVisible = true;
			lost = true;
			return;
		} else if (board[row][column].mineNeighbors >= 1) {
			board[row][column].isVisible = true;
			return;
		} else {
			board[row][column].isVisible = true;
			Stack<GameSquare> s = new Stack<GameSquare>();
			GameSquare currentSquare = board[row][column];
			s.push(currentSquare);
			while (!s.isEmpty()) {
				currentSquare = s.pop();
				if (currentSquare.mineNeighbors == 0) {
					for (int i = currentSquare.row - 1; i <= currentSquare.row + 1; i++) {
						for (int j = currentSquare.col - 1; j <= currentSquare.col + 1; j++) {
							if (!(i == currentSquare.row && j == currentSquare.col) && i > -1 && i < board.length
									&& j > -1 && j < board[0].length) {
								if (!board[i][j].isVisible && !board[i][j].isFlagged) {
									s.push(board[i][j]);
									board[i][j].isVisible = true;
								}
							}
						}
					}
				}
			}
		}
	}
}