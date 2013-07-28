package nl.tudelft.jpacman.model;

/**
 * Enumeration of north, south, east and west to denote directions elements are
 * facing on the board.
 * 
 * @author Jeroen Roosen
 */
public enum Direction {

	/**
	 * North, or up.
	 */
	NORTH(0, -1),

	/**
	 * South, or down.
	 */
	SOUTH(0, 1),

	/**
	 * West, or left.
	 */
	WEST(-1, 0),

	/**
	 * East, or right.
	 */
	EAST(1, 0);

	/**
	 * The delta x to an element in the direction in a matrix with 0,0 (x,y) as
	 * its topleft element.
	 */
	private final int dx;

	/**
	 * The delta y to an element in the direction in a matrix with 0,0 (x,y) as
	 * its topleft element.
	 */
	private final int dy;

	/**
	 * Creates a new Direction with the given parameters.
	 * 
	 * @param dx
	 *            The delta x to an element in the direction in a matrix with
	 *            0,0 (x,y) as its topleft element.
	 * @param dy
	 *            The delta y to an element in the direction in a matrix with
	 *            0,0 (x,y) as its topleft element.
	 */
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * @return The delta x for a single step in this direction, in a matrix with
	 *         0,0 (x,y) as its topleft element.
	 */
	public int getDeltaX() {
		return dx;
	}

	/**
	 * @return The delta y for a single step in this direction, in a matrix with
	 *         0,0 (x,y) as its topleft element.
	 */
	public int getDeltaY() {
		return dy;
	}
}