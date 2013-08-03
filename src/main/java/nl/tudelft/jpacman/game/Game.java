package nl.tudelft.jpacman.game;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.PacMan;

/**
 * High level class that handles a complete game, i.e. the player input, Ghost
 * AIs, the Level, etc.
 * 
 * @author Jeroen Roosen
 */
public abstract class Game {

	/**
	 * The controller that manages the ghosts' movements.
	 */
	private final GhostController ghostAI;

	/**
	 * Whether this game is currently in progress.
	 */
	private boolean inProgress;

	/**
	 * Create a new game.
	 * 
	 * @param ghostController
	 *            The controller moving the ghosts on the level around.
	 */
	public Game(GhostController ghostController) {
		assert ghostController != null;

		this.ghostAI = ghostController;
		this.inProgress = false;
	}

	/**
	 * Starts or resumes this game.
	 */
	public void start() {
		ghostAI.start();
		inProgress = true;
	}

	/**
	 * Stops or pauses this game.
	 */
	public void stop() {
		ghostAI.stop();
		inProgress = false;
	}

	/**
	 * Returns whether or not this game is in progress.
	 * 
	 * @return <code>true</code> iff the game is currently in progress.
	 */
	public boolean isInProgress() {
		return inProgress;
	}

	/**
	 * Moves the Pac-Man one square in the given direction and calculates the
	 * new game state.
	 * 
	 * @param pacMan
	 *            The Pac-Man to move.
	 * @param direction
	 *            The direction to move towards.
	 */
	public void movePacMan(PacMan pacMan, Direction direction) {
		if (isInProgress()) {
			getCurrentLevel().move(pacMan, direction);

			if (getCurrentLevel().lost() || getCurrentLevel().completed()) {
				stop();
			}
		}
	}

	/**
	 * @return The level that is currently being played.
	 */
	public abstract Level getCurrentLevel();
}
