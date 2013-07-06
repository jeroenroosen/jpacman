package nl.tudelft.jpacman.factory;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.Square;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * Creates {@link Board}s, {@link Square}s and {@link Pellet}s to go on top.
 * 
 * @author Jeroen Roosen
 */
public interface BoardFactory {

	/**
	 * Creates a new board.
	 * 
	 * @param squares
	 *            The grid in a way that <code>squares[x][y]</code> returns the
	 *            square at <code>[x, y]</code>.
	 * @return The new board based on the grid of squares.
	 */
	Board createBoard(Square[][] squares);

	/**
	 * Create a new wall square.
	 * 
	 * @return A new wall square.
	 */
	WallSquare createWallSquare();

	/**
	 * Create a new, empty floor square.
	 * 
	 * @return A new, empty floor square.
	 */
	FloorSquare createFloorSquare();

	/**
	 * Create a new pellet.
	 * 
	 * @return A new pellet.
	 */
	Pellet createPellet();
}
