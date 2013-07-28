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
	 * Creates a new PacMan.
	 * 
	 * @param direction
	 *            The initial direction this character is facing.
	 */
	public PacMan(Direction direction) {
		super(direction);
		alive = true;
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
}
