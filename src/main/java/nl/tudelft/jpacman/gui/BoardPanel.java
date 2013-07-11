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

	private static final long serialVersionUID = -589935291636596023L;

	private static final int DEFAULT_TILE_SIZE = 16;

	private final Board board;
	private final Renderer<Board> renderer;

	/**
	 * Creates a new BoardPanel that will display the board using the renderer.
	 * 
	 * @param board
	 *            The board to show.
	 * @param renderer
	 *            The renderer.
	 */
	public BoardPanel(Board board, Renderer<Board> renderer) {
		this.board = board;
		this.renderer = renderer;
		Dimension initSize = new Dimension(board.getWidth()
				* DEFAULT_TILE_SIZE, board.getHeight() * DEFAULT_TILE_SIZE);
		this.setPreferredSize(initSize);
		this.setMinimumSize(initSize);
	}

	@Override
	protected void paintComponent(Graphics g) {
		renderer.render(board, g, getSize());
	}

}
