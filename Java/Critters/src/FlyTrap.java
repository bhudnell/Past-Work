/*
 * This class can be used as the first Critter in the Critter Simulation
 */
class FlyTrap implements Critter {
	public char getChar() {
		return 'T';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		if (front == Neighbor.OTHER)
			return Move.INFECT;
		else
			return Move.LEFT;
	}
}