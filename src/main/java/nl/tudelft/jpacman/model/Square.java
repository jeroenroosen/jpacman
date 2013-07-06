package nl.tudelft.jpacman.model;

/**
 * A square on a {@link Board}.
 * 
 * @author Jeroen Roosen
 */
public interface Square {

	/**
	 * Participants in the game may or may not step on a square, e.g a Ghost may
	 * pass through the gate of the Ghost pit, but Pac-Man may not.
	 * 
	 * @param character
	 *            The character that wishes to step on this square.
	 * @return <code>true</code> iff the character is allowed to access this
	 *         square.
	 */
	boolean isAccessibleTo(Character character);

}
