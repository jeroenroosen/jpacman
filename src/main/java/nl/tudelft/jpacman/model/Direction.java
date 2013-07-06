package nl.tudelft.jpacman.model;

/**
 * Enumeration of north, south, east and west to denote directions elements are
 * facing on the board.
 * 
 * @author Jeroen Roosen
 */
public enum Direction {

	NORTH(0, -1),

	SOUTH(0, 1),

	WEST(-1, 0),

	EAST(1, 0);

	private final int dx, dy;

	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	/**
	 * @return The delta x for a single step in this direction, on a grid with
	 *         [0,0] as its topleft element and [w-1,h-1] as its bottom right
	 *         element.
	 */
	public int getDeltaX() {
		return dx;
	}

	/**
	 * @return The delta y for a single step in this direction, on a grid with
	 *         [0,0] as its topleft element and [w-1,h-1] as its bottom right
	 *         element.
	 */
	public int getDeltaY() {
		return dy;
	}
}