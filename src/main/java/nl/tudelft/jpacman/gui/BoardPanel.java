package nl.tudelft.jpacman.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import nl.tudelft.jpacman.graphics.renderer.Renderer;
import nl.tudelft.jpacman.model.Board;

/**
 * A {@link JPanel} that will show (and take the dimensions of) a rendered
 * board.
 * 
 * @author Jeroen Roosen
 */
public class BoardPanel extends JPanel {

	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = -589935291636596023L;

	/**
	 * Default tile size in pixels. This will determine the initial size of the
	 * panel based on the level displayed.
	 */
	private static final int DEFAULT_TILE_SIZE = 16;

	/**
	 * The board to display.
	 */
	private final Board board;

	/**
	 * The renderer to render the board with.
	 */
	private final Renderer<Board> renderer;

	/**
	 * Creates a new BoardPanel that will display the board using the renderer.
	 * 
	 * @param theBoard
	 *            The board to show.
	 * @param boardRenderer
	 *            The renderer.
	 */
	public BoardPanel(Board theBoard, Renderer<Board> boardRenderer) {
		this.board = theBoard;
		this.renderer = boardRenderer;
		Dimension initSize = new Dimension(theBoard.getWidth()
				* DEFAULT_TILE_SIZE, theBoard.getHeight() * DEFAULT_TILE_SIZE);
		this.setPreferredSize(initSize);
		this.setMinimumSize(initSize);
	}

	@Override
	protected void paintComponent(Graphics g) {
		renderer.render(board, g, getSize());
	}

}
