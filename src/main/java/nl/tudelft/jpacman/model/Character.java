package nl.tudelft.jpacman.model;

/**
 * An active entity in the game, such as Pac-Man or a Ghost.
 * 
 * @author Jeroen Roosen
 */
public abstract class Character {

	private Direction direction;

	/**
	 * Creates a new character facing the given direction.
	 * 
	 * @param direction
	 *            The initial direction this character is facing.
	 */
	public Character(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Returns the direction this character is facing.
	 * 
	 * @return The direction this character is facing.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets the direction this character is facing.
	 * 
	 * @param direction
	 *            The new direction this character is facing.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
