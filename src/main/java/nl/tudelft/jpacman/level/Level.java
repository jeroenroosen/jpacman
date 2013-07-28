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

	/**
	 * Create a new level.
	 * 
	 * @param board
	 *            The board.
	 * @param pacMan
	 *            The Pac-Mans on the board.
	 * @param ghosts
	 *            The ghosts on the board.
	 */
	public Level(Board board, Collection<PacMan> pacMan,
			Collection<Ghost> ghosts) {
		this.board = board;
		this.pacMan = pacMan;
		this.ghosts = ghosts;
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
	 * Counts the remaining pellets on the board and returns the amount.
	 * 
	 * @return The amount of pellets left on the board.
	 */
	public int remainingPellets() {
		int remaining = 0;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				if (board.getSquareAt(x, y).getPellet() != null) {
					remaining++;
				}
			}
		}
		return remaining;
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

		handlePellet(character, destination);
		handleCollisions(destination);
	}

	private void handlePellet(Character character, Square destination) {
		if (isPacMan(character)) {
			Pellet pellet = destination.getPellet();
			if (pellet != null) {
				destination.removePellet();
			}
		}
	}

	private void handleCollisions(Square destination) {
		boolean pacManPresent = false;
		boolean ghostPresent = false;
		for (Character c : destination.getOccupants()) {
			if (isPacMan(c)) {
				pacManPresent = true;
			}
			if (c instanceof Ghost) {
				ghostPresent = true;
			}
			if (pacManPresent && ghostPresent) {
				PacMan pac = (PacMan) c;
				killPacMan(pac);
			}
		}
	}

	private void killPacMan(PacMan pac) {
		pac.setAlive(false);
	}

	private boolean isPacMan(Character character) {
		return character instanceof PacMan;
	}

	/**
	 * Returns whether this level has been completed, i.e. all pellets on the
	 * board have been consumed and at least 1 PacMan is still alive.
	 * 
	 * @return <code>true</code> iff all pellets on the board have been
	 *         consumed and the game is not {@link #lost()}.
	 */
	public boolean completed() {
		return remainingPellets() == 0 && !lost();
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
