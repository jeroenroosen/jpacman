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

	private final Level level;
	private final GhostController ghostController;

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
		
		this.level = level;
		this.ghostController = ghostController;
		this.inProgress = false;
	}

	/**
	 * Starts or resumes this game.
	 */
	public void start() {
		ghostController.start();
		inProgress = true;
	}

	/**
	 * Stops or pauses this game.
	 */
	public void stop() {
		ghostController.stop();
		inProgress = false;
	}

	/**
	 * Returns the level currently being played.
	 * 
	 * @return The level being played.
	 */
	public Level getLevel() {
		return level;
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
			level.move(pacMan, direction);

			if (level.lost() || level.completed()) {
				stop();
			}
		}
	}
}
