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
public class Game {

	/**
	 * The level that will be played during this level.
	 */
	private final Level currentLevel;

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
	 * @param level
	 *            The level for this game.
	 * @param ghostController
	 *            The controller moving the ghosts on the level around.
	 */
	public Game(Level level, GhostController ghostController) {
		assert level != null;
		assert ghostController != null;

		this.currentLevel = level;
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
	 * Returns the level currently being played.
	 * 
	 * @return The level being played.
	 */
	public Level getLevel() {
		return currentLevel;
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
			currentLevel.move(pacMan, direction);

			if (currentLevel.lost() || currentLevel.completed()) {
				stop();
			}
		}
	}
}
