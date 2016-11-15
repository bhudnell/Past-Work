//a class that represents a matrix of integers, with scalar multiplication,
//element sum, and transpose functionality
//Programmer: Brendon Hudnell
public class Matrix {

	// Instance variables
	private int[][] table = null;
	int rows, cols;

	// Construct a Matrix object that has the same elements as the argument
	public Matrix(int[][] table) {
		this.table = table;
		rows = table.length;
		cols = table[0].length;
	}

	// Return the number of rows
	public int rows() {
		return rows;
	}

	// Return the number of columns
	public int columns() {
		return cols;
	}

	// Return the integer at the give row and column
	public int get(int row, int column) {
		return table[row][column];
	}

	// Multiply every element in this matrix by factor
	// 1 2 3 scalarMultiply(2) -> 2 4 6
	// 4 5 6 -> 8 10 12
	public void scalarMultiply(int factor) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] *= factor;
			}
		}
	}

	// Return the sum of all integers in this Matrix
	// 1 2 3 sumOfAllElements() returns 1+2+3+4+5+6 = 21
	// 4 5 6
	public int sumOfAllElements() {
		int sum = 0;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				sum += table[i][j];
			}
		}
		return sum;
	}

	// Change this Matrix to its transpose. Explained at
	// https://en.wikipedia.org/wiki/Transpose
	// 1 2 3 transpose() -> 1 4
	// 4 5 6 -> 2 5
	// -> 3 6
	// You will need to declare a temporary array, as you will in GameOfLife
	// update
	// The rows become the columns.
	// Don't forget to swap rows and columns.
	public void transpose() {
		int[][] temp = new int[cols][rows];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = table[j][i];
			}
		}
		table = temp;
	}
}