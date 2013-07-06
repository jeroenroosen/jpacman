package nl.tudelft.jpacman.model;

/**
 * Solid square that cannot be occupied by anyone.
 * 
 * @author Jeroen Roosen
 */
public class WallSquare implements Square {

	@Override
	public boolean isAccessibleTo(Character character) {
		return false;
	}

}
