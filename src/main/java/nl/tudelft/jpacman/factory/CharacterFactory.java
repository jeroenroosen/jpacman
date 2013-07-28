package nl.tudelft.jpacman.factory;

import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.GhostColour;
import nl.tudelft.jpacman.model.PacMan;

/**
 * Creates {@link Character}s.
 * 
 * @author Jeroen Roosen
 */
public interface CharacterFactory {

	/**
	 * Create a new Pac-Man.
	 * 
	 * @param direction
	 *            The initial direction of this character.
	 * @return A new Pac-Man facing the initial direction.
	 */
	PacMan createPacMan(Direction direction);

	/**
	 * Create a new Ghost.
	 * 
	 * @param colour
	 *            The colour of the ghost.
	 * @param direction
	 *            The initial direction of this character.
	 * @return A new ghost facing the initial direction.
	 */
	Ghost createGhost(GhostColour colour, Direction direction);
}
