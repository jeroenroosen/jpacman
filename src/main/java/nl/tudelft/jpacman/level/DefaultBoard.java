package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Square;

/**
 * Default implementation of a {@link Board}.
 * 
 * @author Jeroen Roosen
 */
public class DefaultBoard implements Board {

	private final Square[][] grid;
	private final int width;
	private final int height;

	/**
	 * Creates a new board.
	 * 
	 * @param grid
	 *            A grid of at least <code>1 x 1</code> squares, in a way such
	 *            that <code>squares[x][y]</code> returns the square at
	 *            <code>[x, y]</code>.
	 */
	public DefaultBoard(Square[][] grid) {
		assert grid.length > 0;
		assert (grid[0]).length > 0;

		this.grid = grid;
		this.width = grid.length;
		this.height = (grid[0]).length;
	}

	/**
	 * Places a square on the grid;
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
