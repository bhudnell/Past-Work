import static org.junit.Assert.*;

import org.junit.Test;

public class BoggleTest {

	@Test
	public void testGetWordsFoundAfterPrepareResultsCalledWithSetBoggleTray() {
		char[][] tray = { { 'E', 'R', 'H', 'I' }, { 'T', 'C', 'O', 'Z' }, { 'I', 'E', 'S', 'E' },
				{ 'V', 'E', 'V', 'W' } };
		BoggleTray dt = new BoggleTray(tray);
		Boggle game = new Boggle();
		game.setBoggleTray(dt);
		game.addGuess("see");
		game.addGuess("see");
		game.addGuess("tEeS");
		game.addGuess("Receive");
		game.addGuess("Sort");
		game.addGuess("cites");
		game.addGuess("seCreTive");
		game.addGuess("NotHere");
		game.addGuess("NotHere");
		game.addGuess("sew");
		game.addGuess("erhi");
		game.addGuess("secret");
		game.addGuess("abacus");
		assertEquals(25, game.getScore());
		assertEquals("[cites, receive, secret, secretive, see, sew, sort, tees]", game.getWordsFound().toString());
		assertEquals("[abacus, erhi, nothere]", game.getWordsIncorrect().toString());
		assertTrue(game.getWordsNotGuessed().contains("recite"));
	}

	@Test
	public void testGetWordsFoundAndGetWordsIncorrectWhenEmpty() {
		char[][] tray = { { 'E', 'R', 'H', 'I' }, { 'T', 'C', 'O', 'Z' }, { 'I', 'E', 'S', 'E' },
				{ 'V', 'E', 'V', 'W' } };
		BoggleTray dt = new BoggleTray(tray);
		Boggle game = new Boggle();
		game.setBoggleTray(dt);
		assertEquals(0, game.getScore());
		assertEquals("[]", game.getWordsFound().toString());
		assertEquals("[]", game.getWordsIncorrect().toString());
	}

	@Test
	public void testAnotherTray() {
		char[][] tray = { { 'H', 'A', 'H', 'I' }, 
						  { 'V', 'W', 'O', 'S' }, 
						  { 'E', 'E', 'S', 'E' },
						  { 'T', 'I', 'P', 'W' } };
		BoggleTray dt = new BoggleTray(tray);
		Boggle game = new Boggle();
		game.setBoggleTray(dt);
		game.addGuess("tees");
		game.addGuess("sweep");
		game.addGuess("waves");
		game.addGuess("hippy");
		assertEquals(5, game.getScore());
		assertEquals("[sweep, tees, waves]", game.getWordsFound().toString());
		assertEquals("[hippy]", game.getWordsIncorrect().toString());
		assertTrue(game.getWordsNotGuessed().contains("tip"));
	}
}
