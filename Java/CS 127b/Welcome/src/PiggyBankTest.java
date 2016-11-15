
/**
 * A complete unit test for the PiggyBank class
 * Programmer: Brendon Hudnell
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class PiggyBankTest {

	@Test
	public void testGetTotalCashinBankJustAfterConstruction() {
		PiggyBank oinky = new PiggyBank();
		PiggyBank p = new PiggyBank();
		// New PiggyBank objects have $0.00 and 0 coins
		assertEquals(0.0, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(0, oinky.getTotalNumberOfCoins());
		assertEquals(0.0, p.getTotalCashInBank(), 1e-12);
		assertEquals(0, p.getTotalNumberOfCoins());
	}

	@Test
	public void testAddPennies() {
		PiggyBank oinky = new PiggyBank();
		oinky.addPennies(1);
		assertEquals(0.01, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(1, oinky.getTotalNumberOfCoins());
		oinky.addPennies(7);
		assertEquals(0.08, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(8, oinky.getTotalNumberOfCoins());
	}

	@Test
	public void testAddNickels() {
		PiggyBank oinky = new PiggyBank();
		oinky.addNickels(1);
		assertEquals(0.05, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(1, oinky.getTotalNumberOfCoins());
		oinky.addNickels(7);
		assertEquals(0.40, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(8, oinky.getTotalNumberOfCoins());
	}

	@Test
	public void testAddDimes() {
		PiggyBank oinky = new PiggyBank();
		oinky.addDimes(1);
		assertEquals(0.10, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(1, oinky.getTotalNumberOfCoins());
		oinky.addDimes(7);
		assertEquals(0.80, oinky.getTotalCashInBank(), 1e-12);
		assertEquals(8, oinky.getTotalNumberOfCoins());
	}

	@Test
	public void testDrainTheBank() {
		PiggyBank oinky = new PiggyBank();
		oinky.addDimes(1);
		oinky.addNickels(2);
		oinky.addPennies(7);
		assertEquals(10, oinky.getTotalNumberOfCoins());
		assertEquals(0.27, oinky.getTotalCashInBank(), 1e-12);
		oinky.drainTheBank();
		assertEquals(0, oinky.getTotalNumberOfCoins());
		assertEquals(0.0, oinky.getTotalCashInBank(), 1e-12);
	}
}