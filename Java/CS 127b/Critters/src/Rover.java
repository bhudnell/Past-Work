/*
 * This class can be used as the first Critter in the Critter Simulation
 */
class Rover implements Critter {
	public char getChar() {
		return 'R';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		if (front == Neighbor.EMPTY)
			return Move.HOP;
		else if (front == Neighbor.OTHER)
			return Move.INFECT;
		else {
			if (Math.random() > 0.5)
				return Move.LEFT;
			else
				return Move.RIGHT;
		}
	}
}