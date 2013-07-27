package nl.tudelft.jpacman.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A square on a {@link Board}. A square may contain a {@link Pellet} and/or a
 * number of occupying {@link Character}s.
 * 
 * @author Jeroen Roosen
 */
public abstract class Square {

	private Pellet pellet;
	private final Deque<Character> occupants;
	private Map<Direction, Square> edges;

	/**
	 * Creates a new, empty square.
	 */
	protected Square() {
		this.occupants = new ArrayDeque<>();
		this.edges = new HashMap<>();
	}

	/**
	 * Adds an edge to a square touching this square in the direction as seen
	 * from this square.
	 * 
	 * @param square
	 *            The new neighbour.
	 * @param direction
	 *            The direction the new neighbour is in as seen from this
	 *            square.
	 */
	public void addNeighbour(Square square, Direction direction) {
		edges.put(direction, square);
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
	 * Removes the pellet from this square. If there is no pellet on this
	 * square, nothing happens.
	 */
	public void removePellet() {
		pellet = null;
	}

	/**
	 * @return The pellet on this Square, or <code>null</code> if there isn't
	 *         any.
	 */
	public Pellet getPellet() {
		return pellet;
	}

	/**
	 * @return An new ordered list of the occupants on this Square, starting
	 *         with the Character that occupied it first and ending with the
	 *         Character that occupied it last.
	 */
	public List<Character> getOccupants() {
		return new ArrayList<>(occupants);
	}

	/**
	 * Adds a {@link Character} on top of this square, making it the new top
	 * level occupant.
	 * 
	 * @param occupant
	 *            The new top level occupant.
	 */
	public void addOccupant(Character occupant) {
		assert occupant != null;
		occupants.addLast(occupant);
	}

	/**
	 * Removes an occupant from this square, given that it is present.
	 * 
	 * @param occupant
	 *            The {@link Character} to remove from this square.
	 */
	public void removeOccupant(Character occupant) {
		assert occupant != null;
		occupants.remove(occupant);
	}

	/**
	 * Returns the square touching this square in the direction as seen from
	 * this square.
	 * 
	 * @param direction
	 *            The direction to look in.
	 * @return The square touching this square, in the given direction as seen
	 *         from this square.
	 */
	public Square squareAt(Direction direction) {
		assert direction != null;
		return edges.get(direction);
	}

	/**
	 * @return The set of 4 squares touching this square.
	 */
	public Set<Square> getNeighbours() {
		return new HashSet<>(edges.values());
	}

	/**
	 * Returns the direction of the given square, provided that it is a
	 * neighbouring square.
	 * 
	 * @param neighbour
	 *            The square to get the direction of.
	 * @return The direction of the neighbouring square as seen from this square
	 *         or <code>null</code> if the given square was not a neighbour.
	 */
	public Direction directionOf(Square neighbour) {
		for (Entry<Direction, Square> e : edges.entrySet()) {
			if (e.getValue() == neighbour) {
				return e.getKey();
			}
		}
		return null;
	}

	/**
	 * Actors in the game may or may not step on a square, e.g a Ghost may pass
	 * through the gate of the Ghost pit, but Pac-Man may not.
	 * 
	 * @param character
	 *            The character that wishes to step on this square.
	 * @return <code>true</code> iff the character is allowed to access this
	 *         square.
	 */
	public abstract boolean isAccessibleTo(Character character);

}
