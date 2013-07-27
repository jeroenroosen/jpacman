package nl.tudelft.jpacman.level;

import java.util.Collection;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Character;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.Square;

/**
 * A higher level accessor for {@link Board} which keeps references to Pac-Man
 * and the ghosts. Level also moves the various actors and elements on the board
 * and handles their collisions.
 * 
 * @author Jeroen Roosen
 */
public class Level {

	private final Board board;
	private final Collection<PacMan> pacMan;
	private final Collection<Ghost> ghosts;
	private final int totalPellets;

	private int pelletsConsumed;

	/**
	 * Create a new level.
	 * 
	 * @param board
	 *            The board.
	 * @param pacMan
	 *            The Pac-Mans on the board.
	 * @param ghosts
	 *            The ghosts on the board.
	 * @param totalPellets
	 *            The amount of pellets on the board.
	 */
	public Level(Board board, Collection<PacMan> pacMan,
			Collection<Ghost> ghosts, int totalPellets) {
		this.board = board;
		this.pacMan = pacMan;
		this.ghosts = ghosts;
		this.totalPellets = totalPellets;
	}

	/**
	 * Return the Pac-Mans on this level.
	 * 
	 * @return The Pac-Mans on this level.
	 */
	public Collection<PacMan> getPacMans() {
		return pacMan;
	}

	/**
	 * Returns the ghosts on this level.
	 * 
	 * @return The ghosts on this level.
	 */
	public Collection<Ghost> getGhosts() {
		return ghosts;
	}

	/**
	 * Returns the board of this level.
	 * 
	 * @return The board of this level.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Returns the total number of pellets on the board.
	 * 
	 * @return the total number of pellets on the board.
	 */
	public int getTotalPellets() {
		return totalPellets;
	}

	/**
	 * Returns the amount of pellets that have been consumed already.
	 * 
	 * @return The amount of pellets that have been consumed.
	 */
	public int getPelletsConsumed() {
		return pelletsConsumed;
	}

	/**
	 * Moves the character one square in the specified direction as long as the
	 * target square is accessible for the character as determined by
	 * {@link Square#isAccessibleTo(Character)}.
	 * 
	 * @param character
	 *            The character to move.
	 * @param direction
	 *            The direction to move the character in.
	 */
	public void move(Character character, Direction direction) {
		Square square = character.getSquare();
		Square destination = square.squareAt(direction);

		character.setDirection(direction);
		character.occupy(destination);

		if (isPacMan(character)) {
			consumePellet(destination);
		}
		
		boolean p = false;
		boolean g = false;
		for (Character c : destination.getOccupants()) {
			if (isPacMan(c)) {
				p = true;
			}
			if (c instanceof Ghost) {
				g = true;
			}
			if (p && g) {
				PacMan pac = (PacMan) c;
				pac.setAlive(false);
			}
		}
	}

	private void consumePellet(Square destination) {
		Pellet pellet = destination.getPellet();
		if (pellet != null) {
			destination.removePellet();
			pelletsConsumed++;
		}
	}

	private boolean isPacMan(Character character) {
		return character instanceof PacMan;
	}

	/**
	 * Returns whether this level has been completed, i.e. all pellets on the
	 * board have been consumed.
	 * 
	 * @return <code>true</code> iff all pellets on the board have been
	 *         consumed.
	 */
	public boolean completed() {
		return getTotalPellets() - getPelletsConsumed() == 0;
	}

	/**
	 * Returns whether this game has been lost, i.e. all Pac-Mans on the board
	 * are dead.
	 * 
	 * @return <code>true</code> iff all Pac-Mans on the board are dead.
	 */
	public boolean lost() {
		for (PacMan pac : pacMan) {
			if (pac.isAlive()) {
				return false;
			}
		}
		return true;
	}

}
