import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * A probable text generator that takes text from a file and uses a specified
 * n-Gram length to produce the predicted text
 * 
 * @author Brendon Hudnell
 *
 */
public class ProbTextWithMap {
	public static void main(String[] args) {
		ProbTextWithMap rw = new ProbTextWithMap("Alice", 6);
		System.out.println("Print 400 probable characters using HashMap<K, V>\n");
		rw.printRandom(400);
	}

	private StringBuilder theText;
	private HashMap<String, ArrayList<Character>> all;
	private ArrayList<Character> charList;
	private String nGram;
	private int nGramLength;

	public ProbTextWithMap(String fileName, int nGramLength) {
		all = new HashMap<>();
		this.nGramLength = nGramLength;
		theText = new StringBuilder();
		try {
			Scanner inFile = new Scanner(new File(fileName));
			while (inFile.hasNext()) {
				theText.append(inFile.next());
				theText.append(' ');
			}
			inFile.close();
			generateHashMap(theText, nGramLength);

		} catch (FileNotFoundException e) {
			// System.out.println(fileName + " was not found.");
		}
	}

	private void generateHashMap(StringBuilder text, int nGramLength) {
		for (int i = 0; i < text.length() - nGramLength - 1; i++) {
			nGram = text.substring(i, i + nGramLength);
			if (!all.containsKey(nGram)) {
				charList = new ArrayList<>();
				charList.add(text.charAt(i + nGramLength));
				all.put(nGram, charList);
			} else {
				all.get(nGram).add(text.charAt(i + nGramLength));
			}
		}
	}

	public void printRandom(int numberOfCharacters) {
		Random num = new Random();
		int start = num.nextInt(theText.length() - 2 * nGramLength);
		nGram = theText.substring(start, start + nGramLength);

		String str = "";
		for (int i = 0; i < numberOfCharacters; i++) {
			char ch = all.get(nGram).get(num.nextInt(all.get(nGram).size()));

			if (ch == ' ' && str.length() >= 60) {
				System.out.println(str);
				str = "";
			} else
				str += "" + ch;

			nGram = nGram.substring(1) + ch;
		}
		System.out.println(str);
	}
}