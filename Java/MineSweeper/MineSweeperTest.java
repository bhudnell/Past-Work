
/**
 * A unit test for MineSweeper.  
 * 
 * @author Brendon Hudnell
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class MineSweeperTest {

	@Test
	public void testGetAdjacentMinesWithAGivenTwodArrayOfBooleans() {

		boolean[][] b1 = { { false, false, false, false, false }, { false, false, true, true, false },
				{ false, false, false, true, false }, };

		// Use the non-random constructor when testing to avoid random mine
		// placement.
		MineSweeper ms = new MineSweeper(b1);

		// Check adjacent mines around every possible GameSquare
		assertEquals(0, ms.getAdjacentMines(0, 0));
		assertEquals(1, ms.getAdjacentMines(0, 1));
		assertEquals(2, ms.getAdjacentMines(0, 2));
		assertEquals(2, ms.getAdjacentMines(0, 3));
		assertEquals(1, ms.getAdjacentMines(0, 4));

		assertEquals(0, ms.getAdjacentMines(1, 0));
		assertEquals(1, ms.getAdjacentMines(1, 1));
		assertEquals(2, ms.getAdjacentMines(1, 2));
		assertEquals(2, ms.getAdjacentMines(1, 3));
		assertEquals(2, ms.getAdjacentMines(1, 4));

		assertEquals(0, ms.getAdjacentMines(2, 0));
		assertEquals(1, ms.getAdjacentMines(2, 1));
		assertEquals(3, ms.getAdjacentMines(2, 2));
		assertEquals(2, ms.getAdjacentMines(2, 3));
		assertEquals(2, ms.getAdjacentMines(2, 4));
	}

	@Test
	public void testRandomConstructor() {
		MineSweeper ms = new MineSweeper(6, 6, 12);
		assertEquals(12, ms.getTotalMineCount());
	}

	@Test
	public void testIsFlaggedAndToggleFlagged() {
		boolean[][] b1 = { { false, false, false, false, false }, { false, false, true, true, false },
				{ false, false, false, true, false }, };

		MineSweeper ms = new MineSweeper(b1);

		assertFalse(ms.isFlagged(0, 0));
		ms.toggleFlagged(0, 0);
		assertTrue(ms.isFlagged(0, 0));
		ms.toggleFlagged(0, 0);
		assertFalse(ms.isFlagged(0, 0));
	}

	@Test
	public void testIsMine() {
		boolean[][] b1 = { { false, false, false, false, false }, { false, false, true, true, false },
				{ false, false, false, true, false }, };

		MineSweeper ms = new MineSweeper(b1);

		assertTrue(ms.isMine(2, 3));
		assertFalse(ms.isMine(0, 0));
	}

	@Test
	public void testClick() {
		boolean[][] b1 = { { false, false, false, false, false }, { false, true, true, false, true },
				{ false, false, true, false, true }, { false, false, true, false, true },
				{ false, false, true, false, true }, { false, false, true, false, false }, };

		MineSweeper ms = new MineSweeper(b1);

		ms.click(5, 0);
		assertTrue(ms.isVisible(5, 0));
		assertTrue(ms.isVisible(4, 0));
		assertTrue(ms.isVisible(3, 0));
		assertTrue(ms.isVisible(2, 0));
		assertTrue(ms.isVisible(5, 1));
		assertTrue(ms.isVisible(4, 1));
		assertTrue(ms.isVisible(3, 1));
		assertTrue(ms.isVisible(2, 1));
		assertFalse(ms.lost());

		ms.click(2, 1);
		ms.toggleFlagged(1, 1);
		ms.click(1, 1);

		ms.click(5, 4);
		assertTrue(ms.isVisible(5, 4));

		ms.click(1, 4);
		assertTrue(ms.isVisible(1, 4));
		assertTrue(ms.lost());

		ms.toString();
	}

	@Test
	public void testWon() {
		boolean[][] b1 = { { false, false, false, false, false }, { false, false, true, false, false }, };

		MineSweeper ms = new MineSweeper(b1);

		ms.click(1, 0);
		ms.click(1, 4);
		assertFalse(ms.won());
		ms.click(0, 2);
		assertTrue(ms.won());
	}
}