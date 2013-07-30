package nl.tudelft.jpacman.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	/**
	 * The board model represented by this level.
	 */
	private final Board gameBoard;

	/**
	 * The collection of Pac-Mans on the board.
	 */
	private final Collection<PacMan> playerCollection;

	/**
	 * The collection of Ghosts on the board.
	 */
	private final Collection<Ghost> ghostCollection;

	/**
	 * Create a new level.
	 * 
	 * @param board
	 *            The board.
	 * @param pacMans
	 *            The Pac-Mans on the board.
	 * @param ghosts
	 *            The ghosts on the board.
	 */
	public Level(Board board, Collection<PacMan> pacMans,
			Collection<Ghost> ghosts) {
		this.gameBoard = board;
		this.playerCollection = pacMans;
		this.ghostCollection = ghosts;
	}

	/**
	 * Return the Pac-Mans on this level.
	 * 
	 * @return The Pac-Mans on this level.
	 */
	public Collection<PacMan> getPacMans() {
		return playerCollection;
	}

	/**
	 * Returns the ghosts on this level.
	 * 
	 * @return The ghosts on this level.
	 */
	public Collection<Ghost> getGhosts() {
		return ghostCollection;
	}

	/**
	 * Returns the board of this level.
	 * 
	 * @return The board of this level.
	 */
	public Board getBoard() {
		return gameBoard;
	}

	/**
	 * Counts the remaining pellets on the board and returns the amount.
	 * 
	 * @return The amount of pellets left on the board.
	 */
	public int remainingPellets() {
		int remaining = 0;
		for (int x = 0; x < gameBoard.getWidth(); x++) {
			for (int y = 0; y < gameBoard.getHeight(); y++) {
				if (gameBoard.getSquareAt(x, y).getPellet() != null) {
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
		assert character != null;

		Square square = character.getSquare();
		Square destination = square.squareAt(direction);

		character.setDirection(direction);
		character.occupy(destination);

		handlePellet(character, destination);
		detectCollisions(destination);
	}

	/**
	 * Checks whether a pellet is present on the destination square and, if so,
	 * consumes it.
	 * 
	 * @param character
	 *            The character to consume the pellet.
	 * @param destination
	 *            The square that may or may not contain a pellet.
	 */
	private void handlePellet(Character character, Square destination) {
		if (isPacMan(character)) {
			Pellet pellet = destination.getPellet();
			if (pellet != null) {
				destination.removePellet();
				PacMan pac = (PacMan) character;
				pac.addPoints(pellet.getValue());
			}
		}
	}

	/**
	 * Checks whether Pac-Man collided with ghosts.
	 * 
	 * @param destination
	 *            The square to check for collisions.
	 */
	private void detectCollisions(Square destination) {
		List<PacMan> pacs = new ArrayList<>();
		List<Ghost> ghosts = new ArrayList<>();

		for (Character c : destination.getOccupants()) {
			if (c instanceof PacMan) {
				pacs.add((PacMan) c);
			}
			if (c instanceof Ghost) {
				ghosts.add((Ghost) c);
			}
		}

		handleCollisions(pacs, ghosts);
	}

	/**
	 * Handles the collisions of Pac-Man(s) and Ghosts.
	 * 
	 * @param pacMans
	 *            The Pac-Mans occupying the square.
	 * @param ghosts
	 *            The ghosts occupying the square.
	 */
	private void handleCollisions(List<PacMan> pacMans, List<Ghost> ghosts) {
		for (PacMan p : pacMans) {
			for (Ghost g : ghosts) {
				collide(p, g);
			}
		}
	}

	/**
	 * Handles the collision of a Pac-Man and a ghost.
	 * 
	 * @param pacMan
	 *            The Pac-Man colliding with the Ghost.
	 * @param ghost
	 *            The Ghost colliding with the Pac-Man.
	 */
	protected void collide(PacMan pacMan, Ghost ghost) {
		pacMan.setAlive(false);
	}

	/**
	 * Checks whether a character is a Pac-Man.
	 * 
	 * @param character
	 *            The character to check.
	 * @return <code>true</code> iff the character is a Pac-Man.
	 */
	private boolean isPacMan(Character character) {
		return character instanceof PacMan;
	}

	/**
	 * Returns whether this level has been completed, i.e. all pellets on the
	 * board have been consumed and at least 1 PacMan is still alive.
	 * 
	 * @return <code>true</code> iff all pellets on the board have been consumed
	 *         and the game is not {@link #lost()}.
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
		for (PacMan pac : playerCollection) {
			if (pac.isAlive()) {
				return false;
			}
		}
		return true;
	}

}
