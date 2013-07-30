package nl.tudelft.jpacman.model;

/**
 * A pellet on the board, waiting for a Pac-Man to eat it.
 * 
 * @author Jeroen Roosen
 */
public class Pellet {
	
	/**
	 * The value of this Pellet (amount of points received when Pac-Man eats it.)
	 */
	private final int value;

	/**
	 * Creates a new Pellet worth a number of points.
	 * @param points The points this Pellet is worth.
	 */
	public Pellet(int points) {
		this.value = points;
	}

	/**
	 * @return The amount of points this pellet is worth.
	 */
	public int getValue() {
		return value;
	}
}
