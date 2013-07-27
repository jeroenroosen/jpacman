package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Ghost;


/**
 * The ghost controller is responsible for moving the {@link Ghost}s around on a {@link Board}.
 * 
 * @author Jeroen Roosen
 */
public interface GhostController {
	
	/**
	 * Starts this controller, causing the ghosts to move.
	 */
	void start();
	
	/**
	 * Stops this controller, causing the ghosts to stop moving.
	 */
	void stop();

}
