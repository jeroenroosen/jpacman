package nl.tudelft.jpacman.model;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Open square which can be occupied by anyone. An open square may or may not contain a consumable Pellet.
 * 
 * @author Jeroen Roosen
 *
 */
public class FloorSquare implements Square {

	private Pellet pellet;
	private final Deque<Character> occupants;

	/**
	 * Creates a new, empty square.
	 */
	public FloorSquare() {
		this.occupants = new ArrayDeque<>();
	}
	
	@Override
	public boolean isAccessibleTo(Character character) {
		return true;
	}
	
	/**
	 * 
	 * @param pellet
	 */
	public void setPellet(Pellet pellet) {
		assert pellet != null;
		this.pellet = pellet;
	}
	
	/**
	 * Removes the pellet from this square. If there is no pellet on this square, nothing happens.
	 */
	public void removePellet() {
		pellet = null;
	}
	
	public Pellet getPellet() {
		return pellet;
	}
	
	public Character getTopLevelOccupant() {
		return this.occupants.peekLast();
	}
	
	public void addOccupant(Character occupant) {
		occupants.addLast(occupant);
	}
	
	public void removeOccupant(Character occupant) {
		occupants.remove(occupant);
	}

}
