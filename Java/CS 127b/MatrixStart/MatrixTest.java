
/**
 * A type that represent a Matrix of integers.
 * 
 * Methods include: 
 * 
 * 1) Matrix(int[][]) 
 * 2) int get(int, int) 
 * 3) int rows()
 * 4) int columns()
 * 5) void scalarMultiply(int inc) 
 * 6) Matrix add(Matrix)
 * 7) void transpose()  
 */
//Programmer: Brendon Hudnell

import static org.junit.Assert.*;
import org.junit.Test;

public class MatrixTest {

	@Test
	public void FailedRowsOrColumns() {
		int[][] a1 = { { 1, 2, 3 }, { -1, -2, -3 } }; // two rows, three columns
		int[][] a2 = { { 4, 5 }, { 6, 7 }, { 8, 9 } }; // three rows, two
														// columns

		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(a2);

		assertEquals(2, m1.rows());
		assertEquals(3, m1.columns());
		assertEquals(3, m2.rows());
		assertEquals(2, m2.columns());
	}

	@Test
	public void testGet() {
		int[][] a1 = { { 1, 2, 3 }, { -1, -2, -3 } };
		int[][] a2 = { { 4, 5 }, { 6, 7 }, { 8, 9 } };

		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(a2);

		assertEquals(1, m1.get(0, 0));
		assertEquals(3, m1.get(0, 2));
		assertEquals(-1, m1.get(1, 0));
		assertEquals(-3, m1.get(1, 2));
		assertEquals(4, m2.get(0, 0));
		assertEquals(7, m2.get(1, 1));
		assertEquals(9, m2.get(2, 1));
	}

	@Test
	public void testScalarMultiply() {
		int[][] a1 = { { 1, 2, 3 }, { -1, -2, -3 } };
		int[][] a2 = { { 4, 5 }, { 6, 7 }, { 8, 9 } };

		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(a2);
		m1.scalarMultiply(-3);
		m2.scalarMultiply(10);

		assertEquals(-3, m1.get(0, 0));
		assertEquals(-9, m1.get(0, 2));
		assertEquals(3, m1.get(1, 0));
		assertEquals(9, m1.get(1, 2));
		assertEquals(40, m2.get(0, 0));
		assertEquals(70, m2.get(1, 1));
		assertEquals(90, m2.get(2, 1));
	}

	@Test
	public void testSumOfAllElements() {
		int[][] a1 = { { 1, 2, 3 }, { -1, -2, -3 } };
		int[][] a2 = { { 4, 5 }, { 6, 7 }, { 8, 9 } };

		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(a2);

		assertEquals(0, m1.sumOfAllElements());
		assertEquals(39, m2.sumOfAllElements());
	}

	@Test
	public void testTranspose() {
		int[][] a1 = { { 1, 2, 3 }, { -1, -2, -3 } };
		int[][] a2 = { { 4, 5 }, { 6, 7 }, { 8, 9 } };

		Matrix m1 = new Matrix(a1);
		Matrix m2 = new Matrix(a2);
		m1.transpose();
		m2.transpose();

		assertEquals(1, m1.get(0, 0));
		assertEquals(-2, m1.get(1, 1));
		assertEquals(-3, m1.get(2, 1));
		assertEquals(4, m2.get(0, 0));
		assertEquals(8, m2.get(0, 2));
		assertEquals(5, m2.get(1, 0));
		assertEquals(9, m2.get(1, 2));
	}
}
