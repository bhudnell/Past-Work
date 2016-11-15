/*
 * This class can be used as the first Critter in the Critter Simulation
 */
class LandMine implements Critter {
	public char getChar() {
		return 'L';
	}

	public Move getMove(Neighbor front, Neighbor back, Neighbor right, Neighbor left) {
		if (front == Neighbor.WALL)
			return Move.LEFT;
		else
			return Move.INFECT;
	}
}