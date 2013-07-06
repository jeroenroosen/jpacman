package nl.tudelft.jpacman.level;

import java.util.Collection;

import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;

/**
 * Default implementation of a Level.
 * 
 * @author Jeroen Roosen
 */
public class DefaultLevel implements Level {

	private final Board board;
	private final PacMan pacMan;
	private final Collection<Ghost> ghosts;
	private final int totalPellets;

	/**
	 * Create a new level.
	 * 
	 * @param board
	 *            The board.
	 * @param pacMan
	 *            The Pac-Man on the board.
	 * @param ghosts
	 *            The ghosts on the board.
	 * @param totalPellets
	 *            The amount of pellets on the board.
	 */
	public DefaultLevel(Board board, PacMan pacMan, Collection<Ghost> ghosts,
			int totalPellets) {
		this.board = board;
		this.pacMan = pacMan;
		this.ghosts = ghosts;
		this.totalPellets = totalPellets;
	}

	@Override
	public PacMan getPacMan() {
		return pacMan;
	}

	@Override
	public Collection<Ghost> getGhost() {
		return ghosts;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public int getTotalPellets() {
		return totalPellets;
	}

}
