/*
 * This class can be used as a Critter in the Critter Simulation
 * 
 * Author: Brendon Hudnell
 */
class MyCritter implements Critter {
	public char getChar() {
		return 'M';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		if (front == Neighbor.OTHER)
			return Move.INFECT;
		else if (front == Neighbor.EMPTY) {
			if (Math.random() > 0.1)
				return Move.HOP;
			else {
				if (Math.random() > 0.5)
					return Move.LEFT;
				else
					return Move.RIGHT;
			}
		} else {
			if (Math.random() > 0.5)
				return Move.LEFT;
			else
				return Move.RIGHT;
		}

	}
}