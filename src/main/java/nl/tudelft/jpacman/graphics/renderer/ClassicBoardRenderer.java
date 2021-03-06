package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.model.Board;

/**
 * <p>
 * A {@link Renderer} that renders the classic two-dimensional Pac-Man board.
 * </p>
 * 
 * <p>
 * This renderer will draw the board row-by-row, square by square. It will first
 * render the sprite of the square itself and then, if applicable, render the
 * occupants of the square.
 * </p>
 * 
 * @author Jeroen Roosen
 */
public class ClassicBoardRenderer implements Renderer<Board> {

	/**
	 * The renderers proxy that will be used to render the various elements.
	 */
	private final Renderers renderers;

	/**
	 * Creates a new board renderer.
	 * 
	 * @param subRenderers
	 *            The sub-renderers used to render the components of the board.
	 */
	public ClassicBoardRenderer(Renderers subRenderers) {
		this.renderers = subRenderers;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * The board will be rendered onto the provided graphics context, from top
	 * left to bottom right.
	 */
	@Override
	public void render(Board board, Graphics g, Dimension dim) {
		assert board != null;
		assert g != null;
		assert dim != null;

		Graphics drawArea = g.create(0, 0, dim.width, dim.height);

		int cellWidth = dim.width / board.getWidth();
		int cellHeight = dim.height / board.getHeight();

		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				// Square square = board.getSquareAt(x, y);
				// renderSquare(drawArea, square, x * SQUARE_WIDTH, y
				// * SQUARE_HEIGHT);
				Graphics subGraphics = drawArea.create(x * cellWidth, y
						* cellHeight, cellWidth, cellHeight);
				renderers.render(board.getSquareAt(x, y), subGraphics,
						new Dimension(cellWidth, cellHeight));
				subGraphics.dispose();
			}
		}
		drawArea.dispose();
	}
}
