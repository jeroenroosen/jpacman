package nl.tudelft.jpacman.model;

/**
 * Solid square that cannot be occupied by anyone.
 * 
 * @author Jeroen Roosen
 */
public class WallSquare extends Square {

	/**
	 * Constructs a new Wall Square.
	 */
	public WallSquare() {
		super();
	}

	@Override
	public boolean isAccessibleTo(Character character) {
		return false;
	}

}
