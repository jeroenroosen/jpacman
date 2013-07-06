package nl.tudelft.jpacman.factory;

import java.util.Collection;

import nl.tudelft.jpacman.level.DefaultBoard;
import nl.tudelft.jpacman.level.DefaultLevel;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.GhostColour;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.Square;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * Factory for default model objects.
 * 
 * @author Jeroen Roosen
 */
public class DefaultFactory implements BoardFactory, LevelFactory, CharacterFactory {

	@Override
	public Board createBoard(Square[][] squares) {
		return new DefaultBoard(squares);
	}

	@Override
	public Level createLevel(Board board, PacMan pacMan,
			Collection<Ghost> ghosts, int totalPellets) {
		return new DefaultLevel(board, pacMan, ghosts, totalPellets);
	}

	@Override
	public WallSquare createWallSquare() {
		return new WallSquare();
	}

	@Override
	public FloorSquare createFloorSquare() {
		return new FloorSquare();
	}

	@Override
	public PacMan createPacMan(Direction direction) {
		return new PacMan(direction);
	}

	@Override
	public Ghost createGhost(GhostColour colour, Direction direction) {
		return new Ghost(direction, colour);
	}

	@Override
	public Pellet createPellet() {
		return new Pellet();
	}
}
