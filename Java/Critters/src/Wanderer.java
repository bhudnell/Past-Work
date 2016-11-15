/*
 * This class can be used as the first Critter in the Critter Simulation
 */
class Wanderer implements Critter {
	public char getChar() {
		return 'W';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		if (front == Neighbor.OTHER)
			return Move.INFECT;
		else if (front == Neighbor.SAME) {
			if (Math.random() > 0.5)
				return Move.LEFT;
			else
				return Move.RIGHT;
		} else {
			if (Math.random() > 0.25)
				return Move.HOP;
			else {
				if (Math.random() > 0.5)
					return Move.LEFT;
				else
					return Move.RIGHT;
			}
		}
	}
}