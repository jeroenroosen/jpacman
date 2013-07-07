package nl.tudelft.jpacman.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Character;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.Square;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * <p>
 * A {@link BoardRenderer} that renders the classic two-dimensional Pac-Man
 * board.
 * </p>
 * 
 * <p>
 * This renderer will draw the board row-by-row, square by square, using a
 * square size of {@value #SQUARE_WIDTH} x {@value #SQUARE_HEIGHT} pixels. It
 * will first render the sprite of the square itself and then, if applicable,
 * render the occupants of the square.
 * </p>
 * 
 * @author Jeroen Roosen
 */
public class ClassicBoardRenderer implements BoardRenderer {

	private static final int SQUARE_WIDTH = 16;
	private static final int SQUARE_HEIGHT = 16;

	private final ClassicSpriteStore spriteStore;

	/**
	 * Creates a new board renderer that will render a board with squares of
	 * {@value #SQUARE_WIDTH} x {@value #SQUARE_HEIGHT} pixels.
	 * 
	 * @param spriteStore
	 *            The sprite store containing sprites for the objects on the
	 *            board.
	 */
	public ClassicBoardRenderer(ClassicSpriteStore spriteStore) {
		this.spriteStore = spriteStore;
	}

	@Override
	public void draw(Board board, Graphics graphics) {
		assert board != null;
		assert graphics != null;

		Graphics drawArea = graphics.create(0, 0, widthOf(board),
				heightOf(board));
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				Square square = board.getSquareAt(x, y);
				renderSquare(drawArea, square, x * SQUARE_WIDTH, y
						* SQUARE_HEIGHT);
			}
		}
		drawArea.dispose();
	}

	private void renderSquare(Graphics g, Square square, int x, int y) {
		assert square != null;

		if (square instanceof WallSquare) {
			spriteStore.getWallSprite().draw(g, x, y, SQUARE_WIDTH,
					SQUARE_HEIGHT);
		} else if (square instanceof FloorSquare) {
			spriteStore.getFloorSprite().draw(g, x, y, SQUARE_WIDTH,
					SQUARE_HEIGHT);
			FloorSquare s = (FloorSquare) square;
			Pellet pellet = s.getPellet();
			renderPellet(g, pellet, x, y);
			renderSquareOccupant(g, s.getTopLevelOccupant(), x, y);
		}
	}

	private void renderSquareOccupant(Graphics g, Character occupant, int x,
			int y) {
		if (occupant != null) {
			if (occupant instanceof PacMan && ((PacMan) occupant).isAlive()) {
				spriteStore.getPacmanSprite(occupant.getDirection()).draw(g, x,
						y, SQUARE_WIDTH, SQUARE_HEIGHT);
			} else if (occupant instanceof Ghost) {
				Ghost ghost = (Ghost) occupant;
				spriteStore.getGhostSprite(ghost.getColour(),
						ghost.getDirection()).draw(g, x, y, SQUARE_WIDTH,
						SQUARE_HEIGHT);
			}
		}
	}

	private void renderPellet(Graphics g, Pellet pellet, int x, int y) {
		if (pellet != null) {
			spriteStore.getPelletSprite().draw(g, x, y, SQUARE_WIDTH,
					SQUARE_HEIGHT);
		}
	}

	@Override
	public Dimension dimensionOf(Board board) {
		assert board != null;
		return new Dimension(widthOf(board), heightOf(board));
	}

	private int widthOf(Board b) {
		return b.getWidth() * SQUARE_WIDTH;
	}

	private int heightOf(Board b) {
		return b.getHeight() * SQUARE_HEIGHT;
	}

}
