package nl.tudelft.jpacman.model;

/**
 * The "bad" guy of our game, the Ghost.
 * 
 * @author Jeroen Roosen
 */
public class Ghost extends Character {

	private final GhostColour colour;

	/**
	 * Creates a new Ghost.
	 * 
	 * @param direction
	 *            The direction this ghost is facing from the start.
	 */
	public Ghost(Direction direction, GhostColour colour) {
		super(direction);
		this.colour = colour;
	}

	/**
	 * Returns the colour of this ghost.
	 * 
	 * @return The colour of this ghost.
	 */
	public GhostColour getColour() {
		return colour;
	}

}
