package nl.tudelft.jpacman.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.model.Board;

/**
 * Draws a {@link Board}.
 * 
 * @author Jeroen Roosen
 */
public interface BoardRenderer {

	/**
	 * Draws the board.
	 * 
	 * @param board
	 *            The board to draw.
	 * @param graphics
	 *            The graphics context.
	 */
	void draw(Board board, Graphics graphics);

	/**
	 * Calculates the dimension of a board when rendered by this renderer.
	 * 
	 * @param board
	 *            The board to calculate the dimension for.
	 * @return The dimension of a board when rendered by this renderer.
	 */
	Dimension dimensionOf(Board board);

}
