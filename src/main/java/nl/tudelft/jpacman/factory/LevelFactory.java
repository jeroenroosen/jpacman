package nl.tudelft.jpacman.factory;

import java.util.Collection;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;

/**
 * Creates Level objects.
 * 
 * @author Jeroen Roosen
 */
public interface LevelFactory {

	/**
	 * Creates a new level.
	 * 
	 * @param board
	 *            The board.
	 * @param pacMan
	 *            The Pac-Mans on the board.
	 * @param ghosts
	 *            The ghosts on the board.
	 * @param totalPellets
	 *            The amount of pellets on the board.
	 * @return A new level with the specified components.
	 */
	Level createLevel(Board board, Collection<PacMan> pacMans, Collection<Ghost> ghosts,
			int totalPellets);
}
