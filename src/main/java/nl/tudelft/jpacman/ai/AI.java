package nl.tudelft.jpacman.ai;

import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.NonPlayerCharacter;

/**
 * The artificial intelligence controlling an NPC.
 * 
 * @author Jeroen Roosen
 */
public interface AI {

	/**
	 * @return The suggested time (in milliseconds) between consequtive moves .
	 */
	int getDelay();

	/**
	 * Calculates the next move for the NPC and returns the suggested direction
	 * to move in.
	 * 
	 * @param npc
	 *            The NPC to calculate the next move for.
	 * @return The suggested direction for the next move, <code>null</code> if
	 *         no suggestion could be made.
	 */
	Direction nextMove(NonPlayerCharacter npc);
}
