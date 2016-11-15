import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Grader tests for BoggleTray. Just tests word searches.
 * 
 * @author michaels, mercer, and in 2009, Hannah Soreng
 * @version 1.0
 */
 
public class BoggleTrayTest {

  @Before
  public void setup() {
    randBrd1 = new BoggleTray(randChars);
    lngWrdBrd = new BoggleTray(longWord);
    repeatsBrd = new BoggleTray(repeats);
  }

  private char[][] randChars = {

  { 'R', 'E', 'D', 'M' },

  { 'B', 'A', 'N', 'O' },

  { 'T', 'Q', 'D', 'F' },

  { 'L', 'O', 'E', 'V' } };

  private char[][] longWord = {

  { 'A', 'B', 'S', 'E' },

  { 'I', 'M', 'T', 'N' },

  { 'N', 'D', 'E', 'D' },

  { 'S', 'S', 'E', 'N' } };

  private char[][] repeats = {

  { 'M', 'O', 'S', 'E' },

  { 'D', 'A', 'L', 'N' },

  { 'T', 'O', 'P', 'D' },

  { 'S', 'S', 'E', 'N' } };

  private BoggleTray randBrd1, lngWrdBrd, repeatsBrd;

  public static String newline = System.getProperty("line.separator");

  private String randBrd1String = newline + "REDM" + newline + "BANO" + newline
      + "TQDF" + newline + "LOEV";

  private String lngWrdBrdString = newline + "ABSE" + newline + "IMTN"
      + newline + "NDED" + newline + "SSEN";

  private String repeatsBrdString = newline + "MOSE" + newline + "DALN"
      + newline + "TOPD" + newline + "SSEN";

  private boolean searchBoard(BoggleTray tray, String s) {
    return tray.foundInBoggleTray(s);
  }

  @Test
  public void simpleWordSearch() {
    assertTrue("Attempting to find 'RED' in" + randBrd1String,
        searchBoard(randBrd1, "RED"));
    assertTrue("Attempting to find 'BAN' in" + randBrd1String,
        searchBoard(randBrd1, "BAN"));
    assertTrue("Attempting to find 'red' in" + randBrd1String,
        searchBoard(randBrd1, "red"));
    assertTrue("Attempting to find 'ban' in" + randBrd1String,
        searchBoard(randBrd1, "ban"));
  }

  @Test
  public void simpleWordSearch2() {
    assertTrue("Could not find 'TOP' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "TOP"));
    assertTrue("Could not find 'LAD' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "LAD"));
    assertTrue("Could not find 'END' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "END"));
  }

  @Test
  public void simpleWordSearch2LowerCase() {
    assertTrue("Could not find 'top' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "top"));
    assertTrue("Could not find 'lad' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "lad"));
    assertTrue("Could not find 'end' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "end"));
  }

  @Test
  public void simpleDiagonalWordSearch() {
    assertTrue(searchBoard(repeatsBrd, "SOLE"));
    assertTrue("Could not find 'TAD' in" + randBrd1String,
        searchBoard(randBrd1, "TAD"));
  }

  @Test
  public void simpleDiagonalWordSearch2() {
    assertTrue("Could not find 'MAP' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "MAP"));
    assertTrue("Could not find 'RAD' in" + randBrd1String,
        searchBoard(randBrd1, "RAD"));
  }

  @Test
  public void threeLetterWords() {
    assertTrue("Could not find 'BAD' in" + randBrd1String,
        searchBoard(randBrd1, "BAD"));
    assertTrue("Could not find 'TOE' in" + randBrd1String,
        searchBoard(randBrd1, "TOE"));
    assertTrue("Could not find 'LOT' in" + randBrd1String,
        searchBoard(randBrd1, "LOT"));
    assertTrue("Could not find 'ADO' in" + randBrd1String,
        searchBoard(randBrd1, "ADO"));
    assertTrue("Could not find 'NAB' in" + randBrd1String,
        searchBoard(randBrd1, "NAB"));
  }

  @Test
  public void fourLetterWords() {
    // assertTrue("Could not find 'DEBT' in" + randBrd1String, searchBoard(
    // randBrd1, "DEBT"));
    assertTrue("Could not find 'LODE' in" + randBrd1String,
        searchBoard(randBrd1, "LODE"));
  }

  @Test
  public void fourLetterWordsMixedCase() {
    assertTrue("Could not find 'BaDe' in" + randBrd1String,
        searchBoard(randBrd1, "BaDe"));
    assertTrue("Could not find 'BEaD' in" + randBrd1String,
        searchBoard(randBrd1, "BEaD"));
    assertTrue("Could not find 'LoDe' in" + randBrd1String,
        searchBoard(randBrd1, "LoDe"));
    assertTrue("Could not find 'dODo' in" + randBrd1String,
        searchBoard(randBrd1, "dODo"));
  }

  @Test
  public void reusedWordInnerWordsGrowing() {
    assertTrue("Searching for 'ABS' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABS"));
    assertTrue("Could not find 'ABSENT' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENT"));
    assertTrue("Could not find 'ABSENTMIND' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMIND"));
    assertTrue("Could not find 'ABSENTMINDED' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMINDED"));
  }

  @Test
  public void reusedWordInnerWordsShrinking() {
    assertTrue("Could not find 'ABSENTMIND' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMIND"));
    assertTrue("Could not find 'ABSENT' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENT"));
    assertTrue("Could not find 'ABS' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABS"));
  }

  @Test
  public void tricky() {
    assertTrue("Could not find 'BEAR' in" + randBrd1String,
        searchBoard(randBrd1, "BEAR"));
    assertTrue("Could not find 'DARE' in" + randBrd1String,
        searchBoard(randBrd1, "DARE"));
    assertTrue("Could not find 'BRAND' in" + randBrd1String,
        searchBoard(randBrd1, "BRAND"));
  }

  @Test
  public void longTricky() {
    assertTrue("Could not find 'RANDOM' in" + randBrd1String,
        searchBoard(randBrd1, "RANDOM"));
    assertTrue("Could not find 'DANDER' in" + randBrd1String,
        searchBoard(randBrd1, "DANDER"));
    assertTrue("Could not find 'FOND' in" + randBrd1String,
        searchBoard(randBrd1, "FOND"));
  }

  @Test
  public void longWord() {
    assertTrue("Could not find 'ABSENTMINDED' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMINDED"));
    assertTrue("Could not find 'ABSENTMINDEDNESS' in" + lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMINDEDNESS"));
  }

  @Test
  public void testForUsingLetterTwice() {
    assertFalse("Should not be able to find 'DAD' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "DAD"));
    assertFalse("Should not be able to find 'MOM' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "MOM"));
    assertFalse("Should not be able to find 'POP' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "POP"));
    assertFalse("Should not be able to find 'TOT' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "TOT"));
    assertFalse("Should not be able to find 'MOSS' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "MOSS"));
  }

  @Test
  public void testBlatantlyNotThere() {
    assertFalse("Should not be able to find 'PIZZA' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "PIZZA"));
    assertFalse("Should not be able to find 'PUDDING' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "PUDDING"));
    assertFalse("Should not be able to find 'MACE' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "MACE"));
  }

  @Test
  public void notExistAndNoFirstLetterInBoard() {
    assertFalse("Should not be able to find 'ZEBRA' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "ZEBRA"));
    assertFalse("Should not be able to find 'BANANA' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "BANANA"));
    assertFalse("Should not be able to find 'CAT' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "CAT"));
    assertFalse("Should not be able to find 'BAT' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "BAT"));
    assertFalse("Should not be able to find 'BAD' in" + repeatsBrdString,
        searchBoard(repeatsBrd, "BAD"));
  }

  @Test
  public void testWordLongerThanBoard() {
    assertFalse(
        "Should not be able to find 'ABSENTMINDEDNESSES' as it is too long in"
            + this.lngWrdBrdString,
        searchBoard(lngWrdBrd, "ABSENTMINDEDNESSES"));
  }

  private char[][] c = { 
      
      { 'A', 'B', 'C', 'D' }, 
      
      { 'E', 'F', 'G', 'H' },
      
      { 'I', 'J', 'K', 'L' }, 
      
      { 'M', 'N', 'O', 'P' } 
      
  };

  private String cString = "" + newline + "ABCD" + newline + "EFGH" + newline
      + "IJKL" + newline + "MNOP";

  @Test
  public void testStringFindWhenThereStartingInUpperLeftCorner1() {
    BoggleTray b = new BoggleTray(c);
    assertTrue("Could not find 'ABC' in" + cString, b.foundInBoggleTray("ABC"));
    assertTrue("Could not find 'ABF' in" + cString, b.foundInBoggleTray("ABF"));
    assertTrue("Could not find 'ABCD' in" + cString,
        b.foundInBoggleTray("ABCD"));

  }

  @Test
  public void testStringFindWhenThereStartingInUpperLeftCorner2() {
    BoggleTray b = new BoggleTray(c);

    assertTrue("Could not find 'AEI' in" + cString, b.foundInBoggleTray("AEI"));
    assertTrue("Could not find 'AEIM' in" + cString,
        b.foundInBoggleTray("AEIM"));
    assertTrue("Could not find 'AFK' in" + cString, b.foundInBoggleTray("AFK"));
    assertTrue("Could not find 'AFKP' in" + cString,
        b.foundInBoggleTray("AFKP"));
  }

  @Test
  public void testStringFindWhenThereStartingInUpperLeftCorner3() {
    BoggleTray b = new BoggleTray(c);

    assertTrue("Could not find 'AFK' in" + cString, b.foundInBoggleTray("AFK"));
    assertTrue("Could not find 'AFPK' in" + cString,
        b.foundInBoggleTray("AFKP"));

    assertTrue("Could not find 'ABFEJINM' in" + cString,
        b.foundInBoggleTray("ABFEJINM"));
    assertTrue("Could not find 'ABEFIJMN' in" + cString,
        b.foundInBoggleTray("ABEFIJMN"));
  }

  @Test
  public void testStringFindWhenNotThere() {
    BoggleTray b = new BoggleTray(c);
    assertFalse("Should not be able to find 'ACB' in" + cString,
        b.foundInBoggleTray("ACB"));
    assertFalse("Should not be able to find 'AIE' in" + cString,
        b.foundInBoggleTray("AIE"));
    assertFalse("Should not be able to find 'AKF' in" + cString,
        b.foundInBoggleTray("AKF"));
    assertFalse("Should not be able to find 'AFGHP' in" + cString,
        b.foundInBoggleTray("AFGHP"));
  }

  @Test
  public void testStringFindWhenAttemptIsMadeToUseTheSameLetterTwice() {
    BoggleTray b = new BoggleTray(c);
    assertFalse("Should not be able to find 'ACA' in" + cString,
        b.foundInBoggleTray("ACA"));
    assertFalse("Should not be able to find 'AEFBA' in" + cString,
        b.foundInBoggleTray("AEFBA"));
    assertFalse("Should not be able to find 'POP' in" + cString,
        b.foundInBoggleTray("POP"));
    assertFalse("Should not be able to find 'LOL' in" + cString,
        b.foundInBoggleTray("LOL"));
  }

  @Test
  public void testStringFindWhenAttemptIsMadeToUseTheSameLetterTwice2() {
    BoggleTray b = new BoggleTray(c);
    assertFalse("Should not be able to find 'KOK' in" + cString,
        b.foundInBoggleTray("KOK"));
    assertFalse("Should not be able to find 'FGF' in" + cString,
        b.foundInBoggleTray("FGF"));
    assertFalse("Should not be able to find 'ABA' in" + cString,
        b.foundInBoggleTray("ABA"));
    assertFalse("Should not be able to find 'NON' in" + cString,
        b.foundInBoggleTray("NON"));
  }

  @Test
  public void testFailedToFindWordsWithQInUpperAndLowerCase() {

    char[][] board = { { 'N', 'A', 'T', 'R' }, { 'E', 'E', 'E', 'E' },
        { 'E', 'R', 'I', 'T' }, { 'S', 'Q', 'E', 'N' } };

    BoggleTray test = new BoggleTray(board);

    String message = "Could not find 'QUEEN or queen' in   N A T R    E E E E    E R I T  S Q E N";
    assertTrue(message, test.foundInBoggleTray("QUEEN"));
    assertTrue(message, test.foundInBoggleTray("QueeN"));
    assertTrue(message, test.foundInBoggleTray("qUeEN"));
    assertTrue(message, test.foundInBoggleTray("queEN"));
    message = "Could not find 'QUIT or quit' in   N A T R    E E E E    E R I T    S Q E N";
    assertTrue(message, test.foundInBoggleTray("quit"));
    assertTrue(message, test.foundInBoggleTray("QUit"));
    assertTrue(message, test.foundInBoggleTray("qUit"));
    assertTrue(message, test.foundInBoggleTray("Quit"));
  }
}