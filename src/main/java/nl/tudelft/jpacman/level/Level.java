package nl.tudelft.jpacman.level;

import java.util.Collection;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;

/**
 * A higher level accessor for {@link Board} which keeps references to Pac-Man
 * and the ghosts.
 * 
 * @author Jeroen Roosen
 */
public interface Level {

	/**
	 * Return the Pac-Man on this level.
	 * 
	 * @return The Pac-Man on this level.
	 */
	PacMan getPacMan();

	/**
	 * Returns the ghosts on this level.
	 * 
	 * @return The ghosts on this level.
	 */
	Collection<Ghost> getGhost();

	/**
	 * Returns the board of this level.
	 * 
	 * @return The board of this level.
	 */
	Board getBoard();

	/**
	 * Returns the total number of pellets on the board.
	 * 
	 * @return the total number of pellets on the board.
	 */
	int getTotalPellets();

}
