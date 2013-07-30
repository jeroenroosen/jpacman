package nl.tudelft.jpacman.model;

/**
 * The main character of our game, Pac-Man.
 * 
 * @author Jeroen Roosen
 */
public class PacMan extends Character {

	/**
	 * <code>true</code> iff Pac-Man is currently alive.
	 */
	private boolean alive;

	/**
	 * The score Pac-Man obtained during a game.
	 */
	private int points;

	/**
	 * Creates a new PacMan.
	 * 
	 * @param direction
	 *            The initial direction this character is facing.
	 */
	public PacMan(Direction direction) {
		super(direction);
		alive = true;
		points = 0;
	}

	/**
	 * Returns whether this Pac-Man is alive.
	 * 
	 * @return <code>true</code> if Pac-Man is alive, or <code>false</code> if
	 *         he is dead.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets the <code>alive</code> flag for this Pac-Man.
	 * 
	 * @param isAlive
	 *            <code>true</code> if Pac-Man is alive, or <code>false</code>
	 *            if he is dead.
	 */
	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}

	/**
	 * Adds the given amount of points to the Pac-Man's score.
	 * 
	 * @param amount
	 *            The amount of points to add.
	 */
	public void addPoints(int amount) {
		points += amount;
	}

	/**
	 * Returns Pac-Man's score (number of points) he obtained during a game.
	 * 
	 * @return
	 */
	public int getScore() {
		return points;
	}
}
