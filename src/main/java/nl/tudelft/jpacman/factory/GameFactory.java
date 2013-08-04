package nl.tudelft.jpacman.factory;

import java.util.List;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Level;

/**
 * Creates Game objects.
 * 
 * @author Jeroen Roosen
 */
public interface GameFactory {

	/**
	 * Creates a Game that will allow the player to work his way through the set
	 * of levels.
	 * 
	 * @param levels
	 *            The levels to be played.
	 * @return A new Game instance.
	 */
	Game createGame(List<Level> levels);

}
