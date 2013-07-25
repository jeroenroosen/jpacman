package nl.tudelft.jpacman.model;

/**
 * Open square which can be occupied by anyone.
 * 
 * @author Jeroen Roosen
 */
public class FloorSquare extends Square {

	/**
	 * Creates a new, empty square.
	 */
	public FloorSquare() {
		super();
	}
	
	@Override
	public boolean isAccessibleTo(Character character) {
		return true;
	}

}
