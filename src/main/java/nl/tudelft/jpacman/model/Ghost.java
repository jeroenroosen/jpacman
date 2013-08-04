package nl.tudelft.jpacman.model;

import nl.tudelft.jpacman.ai.AI;

/**
 * The "bad" guy of our game, the Ghost.
 * 
 * @author Jeroen Roosen
 */
public class Ghost extends NonPlayerCharacter {

	/**
	 * The colour of the ghost.
	 */
	private final GhostColour ghostColour;

	/**
	 * Creates a new Ghost.
	 * 
	 * @param direction
	 *            The direction this ghost is facing from the start.
	 * @param colour
	 *            The colour of this ghost.
	 * @param ai
	 *            The AI that controls this NPC.
	 */
	public Ghost(Direction direction, GhostColour colour, AI ai) {
		super(direction, ai);
		this.ghostColour = colour;
	}

	/**
	 * Returns the colour of this ghost.
	 * 
	 * @return The colour of this ghost.
	 */
	public GhostColour getColour() {
		return ghostColour;
	}

}
