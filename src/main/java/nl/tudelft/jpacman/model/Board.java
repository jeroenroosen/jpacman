package nl.tudelft.jpacman.model;

/**
 * The grid of {@link Square}s that makes up a Pac-Man game.
 * 
 * @author Jeroen Roosen
 */
public interface Board {

	/**
	 * Returns the amount of squares on the x-axis.
	 * 
	 * @return The amount of squares on the x-axis.
	 */
	int getWidth();

	/**
	 * Returns the amount of squares on the y-axis.
	 * 
	 * @return The amount of squares on the y-axis.
	 */
	int getHeight();

	/**
	 * Returns the square at the given coordinate.
	 * 
	 * @param x
	 *            The x position.
	 * @param y
	 *            The y position.
	 * @return The square at the given coordinate.
	 */
	Square getSquareAt(int x, int y);
}
