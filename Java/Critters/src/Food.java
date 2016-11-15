/*
 * This class can be used as the first Critter in the Critter Simulation
 */
class Food implements Critter {
	public char getChar() {
		return 'F';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		return Move.LEFT;
	}
}