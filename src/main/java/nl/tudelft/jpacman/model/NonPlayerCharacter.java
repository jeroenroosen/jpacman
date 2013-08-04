package nl.tudelft.jpacman.model;

import nl.tudelft.jpacman.ai.AI;

/**
 * A {@link Character} that will be controlled by an AI instead of a player.
 * 
 * @author Jeroen Roosen
 */
public abstract class NonPlayerCharacter extends Character {

	/**
	 * The AI that controls this character.
	 */
	private final AI controller;

	/**
	 * Creates a new NPC.
	 * 
	 * @param direction
	 *            The direction this NPC is initially facing.
	 * @param ai
	 *            The AI that controls this NPC.
	 */
	public NonPlayerCharacter(Direction direction, AI ai) {
		super(direction);
		this.controller = ai;
	}

	/**
	 * @return The time between consequtive moves in milliseconds.
	 */
	public int getDelay() {
		return controller.getDelay();
	}

	/**
	 * @return The next move for this NPC.
	 */
	public Direction nextMove() {
		return controller.nextMove(this);
	}
}
