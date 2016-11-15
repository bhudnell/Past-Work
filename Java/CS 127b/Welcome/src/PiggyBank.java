//This program acts as a piggy bank, keeping track of the number of pennies, nickels,
//and dimes added to it as well as the value of the change inside.
//Programmer: Brendon Hudnell
public class PiggyBank {

	private int dimes;
	private int nickels;
	private int pennies;

	// Construct an empty PiggyBank that can store pennies, nickels,
	// and/or dimes (no quarters, halves or dollar coins to save time)
	public PiggyBank() {
		dimes = 0;
		nickels = 0;
		pennies = 0;
	}

	// A getTotalNumberOfCoins() message should return the number of
	// coins currently in the bank. The coins can be of any type.
	public int getTotalNumberOfCoins() {
		return dimes + nickels + pennies;
	}

	// A getTotalCashInBank() message should return the total amount of
	// cash in the bank. Pennies are $0.01, nickels are $0.05, and dimes
	// are $0.10 (no quarters, halves, or dollar coins to save time).
	public double getTotalCashInBank() {
		return dimes * 0.10 + nickels * 0.05 + pennies * 0.01;
	}

	// An addPennies message adds the given number of pennies to this PiggyBank
	public void addPennies(int penniesEntered) {
		pennies += penniesEntered;
	}

	// An addPennies message adds the given number of nickels to this PiggyBank
	public void addNickels(int numberOfNickels) {
		nickels += numberOfNickels;
	}

	// An addPennies message adds the given number of dimes to this PiggyBank
	public void addDimes(int numberOfDimes) {
		dimes += numberOfDimes;
	}

	// A drainTheBank message takes all of the money out of the bank
	public void drainTheBank() {
		dimes = 0;
		nickels = 0;
		pennies = 0;
	}
}
