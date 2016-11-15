import java.io.*;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 * 
 * @author Brendon Hudnell
 */
public class GameTree {

	private class TreeNode {
		private String data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(String data) {
			this.data = data;
			left = null;
			right = null;
		}

		public TreeNode(TreeNode leftTree, String data, TreeNode rightTree) {
			left = leftTree;
			this.data = data;
			right = rightTree;
		}
	}

	private TreeNode root;
	private Scanner inFile;
	private String currentFileName;
	private TreeNode currentNode;

	/**
	 * Constructor needed to create the game.
	 * 
	 * @param name
	 *            this is the name of the file we need to import the game
	 *            questions and answers from.
	 */
	public GameTree(String name) {
		currentFileName = name;
		try {
			inFile = new Scanner(new File(currentFileName));
		} catch (FileNotFoundException e) {
			// System.out.println(currentFileName + " was not found.");
		}
		root = build();
		inFile.close();
		currentNode = root;
	}

	/**
	 * Private helper method that builds the binary tree from an input file.
	 * 
	 * @return a completed binary tree
	 */
	private TreeNode build() {
		if (!inFile.hasNextLine())
			return null;

		String lineString = inFile.nextLine().trim();
		if (isAnswer(lineString))
			return new TreeNode(lineString);
		else
			return new TreeNode(build(), lineString, build());
	}

	/**
	 * Determines if a string is an answer or a question
	 * 
	 * @param str
	 *            string to determine if answer or question
	 * 
	 * @return true if the string is an answer, false if it is a question
	 */
	private boolean isAnswer(String str) {
		return str.charAt(str.length() - 1) != '?';
	}

	/**
	 * Add a new question and answer to the currentNode.
	 * 
	 * @param newQuestion
	 *            The question to add where the old answer was.
	 * @param newAnswer
	 *            The new Yes answer for the new question.
	 */
	public void add(String newQuestion, String newAnswer) {
		String temp = currentNode.data;
		currentNode.data = newQuestion;
		currentNode.right = new TreeNode(currentNode.left, temp, currentNode.right);
		currentNode.left = new TreeNode(newAnswer);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 * 
	 * @return False if the current node is an internal node rather than an
	 *         answer at a leaf.
	 */
	public boolean foundAnswer() {
		if (isAnswer(currentNode.data))
			return true;
		return false;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.
	 * 
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return currentNode.data;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 * 
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
		if (yesOrNo == Choice.Yes)
			currentNode = currentNode.left;
		else
			currentNode = currentNode.right;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the
	 * question at the root of this GameTree.
	 */
	public void reStart() {
		currentNode = root;
	}

	/**
	 * Return a textual version of this object
	 */
	@Override
	public String toString() {
		return toString(root, 0).trim();
	}

	/**
	 * Private helper method for the toString() class. Prints the tree elements
	 * with reverse inorder traversal
	 * 
	 * @param node
	 *            the current node
	 * @param depth
	 *            the depth of the current node
	 * 
	 * @return a string representation of this object
	 */
	private String toString(TreeNode node, int depth) {
		String result = "";
		if (node == null)
			return "";

		result += toString(node.right, depth + 1);
		for (int i = 1; i <= depth; i++)
			result += "- ";
		result += node.data + "\n";
		result += toString(node.left, depth + 1);
		return result;
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 * 
	 */
	public void saveGame() {
		FileWriter charToBytesWriter = null;
		try {
			charToBytesWriter = new FileWriter(currentFileName);
		} catch (IOException ioe) {
			// System.out.println(currentFileName + "could not be created.");
		}
		PrintWriter diskFile = new PrintWriter(charToBytesWriter);

		diskFile.print(saveGameString(root));
		diskFile.close();
	}

	/**
	 * A private helper method for the saveGame() method. Creates a string
	 * representation of this object in the required output format.
	 * 
	 * @param node
	 *            the current node
	 * 
	 * @return a string in the required output format
	 */
	private String saveGameString(TreeNode node) {
		String result = "";
		if (node != null) {
			result += node.data + "\n";
			result += saveGameString(node.left);
			result += saveGameString(node.right);
		}
		return result;
	}
}