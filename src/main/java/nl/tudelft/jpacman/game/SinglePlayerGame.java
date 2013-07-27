package nl.tudelft.jpacman.game;

import java.util.Collection;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.PacMan;

/**
 * A game with a single Pac-Man.
 * 
 * <ul>
 * Offers high level methods to manipulate the game:
 * <li>{@link #up()}
 * <li>{@link #down()}
 * <li>{@link #left()}
 * <li>{@link #right()}
 * </ul>
 * 
 * @author Jeroen Roosen
 */
public class SinglePlayerGame extends Game {

	private final PacMan pacMan;

	/**
	 * Creates a new single player game for a level.
	 * 
	 * @param level
	 *            A level with at least 1 Pac-Man on the board.
	 */
	public SinglePlayerGame(Level level, GhostController ghostController) {
		super(level, ghostController);
		Collection<PacMan> pacMans = level.getPacMans();
		assert pacMans.size() == 1;
		pacMan = pacMans.iterator().next();
	}

	/**
	 * Moves Pac-Man up 1 square.
	 */
	public void up() {
		movePacMan(pacMan, Direction.NORTH);
	}

	/**
	 * Moves Pac-Man down 1 square.
	 */
	public void down() {
		movePacMan(pacMan, Direction.SOUTH);
	}

	/**
	 * Moves Pac-Man left 1 square.
	 */
	public void left() {
		movePacMan(pacMan, Direction.WEST);
	}

	/**
	 * Moves Pac-Man square 1 square.
	 */
	public void right() {
		movePacMan(pacMan, Direction.EAST);
	}

}
