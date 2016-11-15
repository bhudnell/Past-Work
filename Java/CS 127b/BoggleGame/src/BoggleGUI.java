
// GUI components and interfaces you will need
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * An event driven graphical user interface for the Boggle game using the design
 * of BoggleTray and Boggle classes during Rick Mercer's CSc classes at the UofA
 */
@SuppressWarnings("serial")
public class BoggleGUI extends JFrame {

	public static void main(String[] args) {
		BoggleGUI theView = new BoggleGUI();
		theView.setVisible(true);
	}

	// Need the well test model in several methods of this GUI
	private Boggle game;

	private JTextArea diceTrayArea;
	private JButton newGameButton = new JButton("New Game");
	private JButton endButton = new JButton("End game");
	private JTextArea userInputArea = new JTextArea();

	public BoggleGUI() {
		setUpModel();
		layoutWindow();
		setupListeners();
		startNewGame();
	}

	private class newGameButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			startNewGame();
		}

	}

	private class endButtonListener implements ActionListener {

		private String report;
		private Scanner wordsIn;

		public void actionPerformed(ActionEvent e) {
			wordsIn = new Scanner(userInputArea.getText());
			while (wordsIn.hasNext())
				game.addGuess(wordsIn.next());

			report = "Your score: " + game.getScore() + "\n\n";
			report += "Words you found: \n\n";
			for (int i = 1; i <= game.getWordsFound().size(); i++) {
				report += game.getWordsFound().get(i - 1);
				if (i % 10 == 0 && i != game.getWordsFound().size())
					report += "\n";
				else
					report += " ";
			}
			report += "\n\nIncorrect words: \n\n";
			for (int i = 1; i <= game.getWordsIncorrect().size(); i++) {
				report += game.getWordsIncorrect().get(i - 1);
				if (i % 10 == 0 && i != game.getWordsIncorrect().size())
					report += "\n";
				else
					report += " ";
			}
			report += "\n\nYou could have found " + game.getWordsNotGuessed().size() + " more words.\n";
			report += "The computer found all of your words plus these you could have:\n\n\n";
			for (int i = 1; i <= game.getWordsNotGuessed().size(); i++) {
				report += game.getWordsNotGuessed().get(i - 1);
				if (i % 10 == 0 && i != game.getWordsNotGuessed().size())
					report += "\n";
				else
					report += " ";
			}

			JOptionPane.showMessageDialog(null, report);
			startNewGame();
		}

	}

	private void setUpModel() {
		game = new Boggle();
	}

	// Add five GUI components to this JFrame
	private void layoutWindow() {
		// Set up the JFrame
		this.setSize(500, 270);
		this.setResizable(false);
		setLocation(10, 10);
		setTitle("Boggle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		// Layout the dice tray area as a JTextArea
		diceTrayArea = new JTextArea();
		diceTrayArea.setEditable(false);
		diceTrayArea.setBackground(Color.cyan);
		diceTrayArea.setFont(new Font("Courier", Font.BOLD, 20));
		diceTrayArea.setSize(210, 220);
		diceTrayArea.setLocation(10, 10);
		add(diceTrayArea);

		// Declare and setSize and setLocation of userInputLabel
		JLabel userInputLabel = new JLabel("Enter your words below");
		userInputLabel.setSize(200, 20);
		userInputLabel.setLocation(260, 30);
		add(userInputLabel);

		// setSize and setLocation of userInputArea. Set line wrapping to true.
		userInputArea.setSize(200, 150);
		userInputArea.setLocation(260, 50);
		userInputArea.setLineWrap(true);
		userInputArea.setWrapStyleWord(true);
		add(userInputArea);

		// setSize and setLocation of newGameButton.
		newGameButton.setSize(95, 20);
		newGameButton.setLocation(260, 210);
		add(newGameButton);

		// setSize and setLocation of endButton.
		endButton.setSize(90, 20);
		endButton.setLocation(370, 210);
		add(endButton);
	}

	private void setupListeners() {
		newGameButtonListener newGameListener = new newGameButtonListener();
		endButtonListener endListener = new endButtonListener();
		newGameButton.addActionListener(newGameListener);
		endButton.addActionListener(endListener);
	}

	private void startNewGame() {
		game = new Boggle();
		diceTrayArea.setText(game.getBoggleTrayAsString());
		userInputArea.setText(null);
	}
}