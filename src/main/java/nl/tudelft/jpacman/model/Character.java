package nl.tudelft.jpacman.model;

/**
 * An actor in the game, such as Pac-Man or a Ghost.
 * 
 * @author Jeroen Roosen
 */
public abstract class Character {

	/**
	 * The direction this character is facing.
	 */
	private Direction dir;
	
	/**
	 * The square this character is occupying.
	 */
	private Square square;

	/**
	 * Creates a new character facing the given direction.
	 * 
	 * @param direction
	 *            The initial direction this character is facing.
	 */
	public Character(Direction direction) {
		this.dir = direction;
		assert invariant();
	}

	/**
	 * Once this character occupies a square, the square should indeed list this
	 * character as an occupant.
	 * 
	 * @return <code>true</code> iff this invariant holds.
	 */
	protected boolean invariant() {
		return square == null || square.getOccupants().contains(this);
	}

	/**
	 * Returns the direction this character is facing.
	 * 
	 * @return The direction this character is facing.
	 */
	public Direction getDirection() {
		return dir;
	}

	/**
	 * Sets the direction this character is facing.
	 * 
	 * @param direction
	 *            The new direction this character is facing.
	 */
	public void setDirection(Direction direction) {
		this.dir = direction;
	}

	/**
	 * Lets the character occupy the given square. This operation will remove
	 * the character from the previously occupying square and place it on the
	 * destination square if this square can be accessed.
	 * 
	 * @param destinationSquare
	 *            The square to occupy.
	 */
	public void occupy(Square destinationSquare) {
		assert destinationSquare != null;
		if (destinationSquare.isAccessibleTo(this)) {
			if (square != null) {
				square.removeOccupant(this);
			}
			destinationSquare.addOccupant(this);
			square = destinationSquare;
		}
		assert invariant();
	}

	/**
	 * @return The square this character is currently occupying.
	 */
	public Square getSquare() {
		return square;
	}

}
