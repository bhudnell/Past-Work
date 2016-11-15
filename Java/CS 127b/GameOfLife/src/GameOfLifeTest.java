
/**
 * A unit test for the GameOfLife class.
 * 
 * @author Brendon Hudnell
 * 
 * Tests the constructor, gettors, cellAt, growCellAt, toString, 
 * neighborCount, and update methods. Including tests for corner
 * and edge wraparound. 
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {

	@Test
	public void testConstructorAndGetters() {
		GameOfLife society = new GameOfLife(5, 8);

		assertEquals(5, society.numberOfRows());
		assertEquals(8, society.numberOfColumns());

		for (int r = 0; r < society.numberOfRows(); r++)
			for (int c = 0; c < society.numberOfColumns(); c++)
				assertFalse(society.cellAt(r, c));
	}

	@Test
	public void testPrint() {
		GameOfLife society = new GameOfLife(4, 4);
		society.growCellAt(1, 1);
		society.growCellAt(1, 2);

		assertEquals("....\n.OO.\n....\n....\n", society.toString());
	}

	@Test
	public void testGrowCellAtAndCellAt() {
		GameOfLife society = new GameOfLife(4, 4);
		society.growCellAt(1, 1);
		society.growCellAt(2, 2);
		society.growCellAt(3, 3);

		assertTrue(society.cellAt(1, 1));
		assertTrue(society.cellAt(2, 2));
		assertTrue(society.cellAt(3, 3));
	}

	@Test
	public void testNeighborsNoWrapping() {
		GameOfLife society = new GameOfLife(10, 16);
		society.growCellAt(3, 3);
		society.growCellAt(3, 4);
		society.growCellAt(3, 5);

		assertEquals(0, society.neighborCount(2, 1));
		assertEquals(1, society.neighborCount(2, 2));
		assertEquals(2, society.neighborCount(2, 3));
		assertEquals(3, society.neighborCount(2, 4));
		assertEquals(0, society.neighborCount(4, 1));
		assertEquals(1, society.neighborCount(4, 2));
		assertEquals(2, society.neighborCount(4, 3));
		assertEquals(3, society.neighborCount(4, 4));
		assertEquals(2, society.neighborCount(3, 4));
	}

	@Test
	public void testNeighborsEdgeWrapping() {
		GameOfLife society = new GameOfLife(6, 5);
		society.growCellAt(0, 3);
		society.growCellAt(5, 3);
		society.growCellAt(3, 0);
		society.growCellAt(3, 4);

		assertEquals(2, society.neighborCount(2, 0));
		assertEquals(2, society.neighborCount(2, 4));
		assertEquals(2, society.neighborCount(0, 2));
		assertEquals(2, society.neighborCount(5, 2));
	}

	@Test
	public void testNeighborsCornerWrapping() {
		GameOfLife society = new GameOfLife(5, 5);
		society.growCellAt(0, 0);
		society.growCellAt(1, 0);
		society.growCellAt(4, 0);
		society.growCellAt(0, 1);
		society.growCellAt(4, 1);
		society.growCellAt(0, 4);
		society.growCellAt(1, 4);
		society.growCellAt(4, 4);

		assertEquals(7, society.neighborCount(0, 0));
		assertEquals(5, society.neighborCount(4, 0));
		assertEquals(5, society.neighborCount(0, 4));
		assertEquals(3, society.neighborCount(4, 4));
	}

	@Test
	public void testUpdate() {
		GameOfLife failedSociety = new GameOfLife(5, 7);
		failedSociety.growCellAt(1, 2);
		failedSociety.growCellAt(1, 4);
		failedSociety.growCellAt(2, 2);
		failedSociety.growCellAt(2, 3);
		failedSociety.growCellAt(2, 4);

		GameOfLife successfulSociety = new GameOfLife(5, 7);
		successfulSociety.growCellAt(2, 2);
		successfulSociety.growCellAt(2, 3);
		successfulSociety.growCellAt(2, 4);

		failedSociety.update();
		assertTrue(failedSociety.cellAt(1, 2));
		assertTrue(failedSociety.cellAt(1, 4));
		assertTrue(failedSociety.cellAt(2, 2));
		assertTrue(failedSociety.cellAt(2, 4));
		assertTrue(failedSociety.cellAt(3, 3));

		failedSociety.update();
		assertTrue(failedSociety.cellAt(2, 2));
		assertTrue(failedSociety.cellAt(2, 4));
		assertTrue(failedSociety.cellAt(3, 3));

		failedSociety.update();
		assertTrue(failedSociety.cellAt(2, 3));
		assertTrue(failedSociety.cellAt(3, 3));

		successfulSociety.update();
		assertTrue(successfulSociety.cellAt(1, 3));
		assertTrue(successfulSociety.cellAt(2, 3));
		assertTrue(successfulSociety.cellAt(3, 3));

		successfulSociety.update();
		assertTrue(successfulSociety.cellAt(2, 2));
		assertTrue(successfulSociety.cellAt(2, 3));
		assertTrue(successfulSociety.cellAt(2, 4));
	}
}