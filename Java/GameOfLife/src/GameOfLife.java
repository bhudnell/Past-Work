/**
 * The model for John Conway's Game of Life.
 *
 * @author Brendon Hudnell
 * 
 *         Contains methods to place a cell at a location, check a location for
 *         a cell, check a location for neighboring cells, and update the
 *         gamestate.
 * 
 */
public class GameOfLife {

	private boolean[][] grid;
	private int rows, cols;

	/**
	 * Takes two integer arguments to represent the number of rows and columns
	 * in the game of life. The constructor creates a society with no cells but
	 * space to store rows*cols cells.
	 *
	 * @param rows
	 *            The height of the grid that shows the cells.
	 * @param cols
	 *            The width of the grid that shows the cells.
	 */
	public GameOfLife(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new boolean[rows][cols];
	}

	/**
	 * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfRows() {
		return rows;
	}

	/**
	 * The number of columns, which can be indexed from 0..numberOfColumns()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfColumns() {
		return cols;
	}

	/**
	 * Place a new cell in the society. Precondition: row and col are in range.
	 *
	 * @param row
	 *            The row to grow the cell.
	 * @param col
	 *            The column to grow the cell.
	 */
	public void growCellAt(int row, int col) {
		grid[row][col] = true;
	}

	/**
	 * Return true if there is a cell at the given row and column. Return false
	 * if there is none at the specified location.
	 *
	 * @param row
	 *            The row to check.
	 * @param col
	 *            The column to check.
	 * @return True if there is a cell at the given row or false if none
	 */
	public boolean cellAt(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Return one big string of cells to represent the current state of the
	 * society of cells (see output below where '.' represents an empty space
	 * and 'O' is a live cell. There is no need to test toString. Simply use it
	 * to visually inspect if needed. Here is one sample output from toString:
	 *
	 * GameOfLife society = new GameOfLife(4, 14);
	 * society.growCellAt(1, 2);
	 * society.growCellAt(2, 3);
	 * society.growCellAt(3, 4);
	 * System.out.println(society.toString());
	 *
	 * Output:
	 * ..............
	 * ..O...........
	 * ...O..........
	 * ....O.........
	 *
	 * @return A textual representation of this society of cells.
	 */
	@Override
	public String toString() {
		String output = new String();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j])
					output += "O";
				else
					output += ".";
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * Counts the neighbors around the given location using wraparound. A cell in
	 * row 0 has neighbors in the last row if a cell is in the same column, or
	 * the column to the left or right. In this example, cell 0,5 has two
	 * neighbors in the last row, cell 2,8 has four neighbors, cell 2,0 has four
	 * neighbors, cell 1,0 has three neighbors. The cell at 3,8 has 3 neighbors.
	 * The potential location for a cell at 4,8 would have three neighbors.
	 *
	 * .....O..O
	 * O........
	 * O.......O 
	 * O.......O 
	 * ....O.O..
	 *
	 * The return values should always be in the range of 0 through 8.
	 *
	 * @return The number of neighbors around any cell using wrap around.
	 */
	public int neighborCount(int row, int col) {
		int count = 0;

		if (grid[(row + 1 + rows) % rows][col])
			count++;
		if (grid[(row - 1 + rows) % rows][col])
			count++;
		if (grid[row][(col + 1 + cols) % cols])
			count++;
		if (grid[row][(col - 1 + cols) % cols])
			count++;
		if (grid[(row + 1 + rows) % rows][(col + 1 + cols) % cols])
			count++;
		if (grid[(row - 1 + rows) % rows][(col + 1 + cols) % cols])
			count++;
		if (grid[(row + 1 + rows) % rows][(col - 1 + cols) % cols])
			count++;
		if (grid[(row - 1 + rows) % rows][(col - 1 + cols) % cols])
			count++;

		return count;
	}

	/**
	 * Update the state to represent the next society. Typically, some cells
	 * will die off while others are born.
	 */
	public void update() {
		GameOfLife temp = new GameOfLife(rows, cols);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (neighborCount(i, j) == 3)
					temp.grid[i][j] = true;
				else if (grid[i][j] && neighborCount(i, j) == 2)
					temp.grid[i][j] = true;
				else if (neighborCount(i, j) < 2 || neighborCount(i, j) > 3)
					temp.grid[i][j] = false;
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = temp.grid[i][j];
			}
		}
	}
}