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

	private boolean inProgress;

	/**
	 * Create a new game.
	 * 
	 * @param level
	 *            The level for this game.
	 */
	public Game(Level level) {
		this.level = level;
		this.inProgress = false;
	}

	/**
	 * Starts or resumes this game.
	 */
	public void start() {
		inProgress = true;
	}

	/**
	 * Stops or pauses this game.
	 */
	public void stop() {
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
	 * Returns whether or not the game is lost.
	 * 
	 * @return <code>true</code> iff no Pac-Mans remain on the board.
	 */
	public boolean gameOver() {
		return level.getPacMans().isEmpty();
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

			// TODO calculate match state

		}
	}
}
