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
	
	public FloorSquare() {
		this.occupants = new ArrayDeque<>();
	}
	
	@Override
	public boolean isAccessibleTo(Character character) {
		return true;
	}
	
	public void setPellet(Pellet pellet) {
		this.pellet = pellet;
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
