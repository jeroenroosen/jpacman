package nl.tudelft.jpacman.ai;

import java.util.Random;

import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.NonPlayerCharacter;
import nl.tudelft.jpacman.model.Square;

/**
 * Moves NPCs around in random directions. The delay between moves varies
 * between 3 and 5 moves per second.
 * 
 * @author Jeroen Roosen
 */
public class RandomAI implements AI {

	/**
	 * 5 moves per second.
	 */
	private static final int SHORT_DELAY = 200;

	/**
	 * 3 moves per second.
	 */
	private static final int LONG_DELAY = 333;

	/**
	 * The random number generator.
	 */
	private final Random rng;

	/**
	 * Creates a new AI that moves NPCs around in random directions.
	 */
	public RandomAI() {
		rng = new Random();
	}

	@Override
	public int getDelay() {
		return rng.nextInt(LONG_DELAY - SHORT_DELAY)
				+ SHORT_DELAY;
	}

	@Override
	public Direction nextMove(NonPlayerCharacter npc) {
		Direction[] dirs = Direction.values();
		int r = rng.nextInt(dirs.length);

		for (int i = 0; i < dirs.length; i++) {
			Direction d = dirs[(r + i) % dirs.length];
			Square square = npc.getSquare();
			Square dest = square.squareAt(d);
			if (dest.isAccessibleTo(npc)) {
				return d;
			}
		}

		return null;
	}

}
