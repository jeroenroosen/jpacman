package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Square;

/**
 * Default implementation of a {@link Board}.
 * 
 * @author Jeroen Roosen
 */
public class DefaultBoard implements Board {

	/**
	 * The matrix of squares forming this board.
	 */
	private final Square[][] grid;
	
	/**
	 * The amount of squares horizontally.
	 */
	private final int width;
	
	/**
	 * The amount of squares vertically.
	 */
	private final int height;

	/**
	 * Creates a new board.
	 * 
	 * @param squares
	 *            A grid of at least <code>1 x 1</code> squares, in a way such
	 *            that <code>squares[x][y]</code> returns the square at
	 *            <code>[x, y]</code>.
	 */
	public DefaultBoard(Square[][] squares) {
		assert squares.length > 0;
		assert (squares[0]).length > 0;

		this.grid = squares;
		this.width = squares.length;
		this.height = (squares[0]).length;
	}

	/**
	 * Places a square on the grid.
	 * 
	 * @param x
	 *            The x position.
	 * @param y
	 *            The y position.
	 * @param square
	 *            The square to put on the grid.
	 */
	public void put(int x, int y, Square square) {
		assert x < width;
		assert y < height;
		grid[x][y] = square;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Square getSquareAt(int x, int y) {
		assert x < width;
		assert y < height;
		return grid[x][y];
	}
}
