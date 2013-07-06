package nl.tudelft.jpacman.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import nl.tudelft.jpacman.graphics.BoardRenderer;
import nl.tudelft.jpacman.model.Board;

/**
 * A {@link JPanel} that will show (and take the dimensions of) a rendered
 * board.
 * 
 * @author Jeroen Roosen
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -589935291636596023L;

	private final Board board;
	private final BoardRenderer renderer;

	/**
	 * Creates a new BoardPanel that will display the board using the renderer.
	 * 
	 * @param board
	 *            The board to show.
	 * @param renderer
	 *            The renderer.
	 */
	public BoardPanel(Board board, BoardRenderer renderer) {
		super();
		this.board = board;
		this.renderer = renderer;
		setPreferredSize(renderer.dimensionOf(board));
	}

	@Override
	protected void paintComponent(Graphics g) {
		renderer.draw(board, g);
	}

}
