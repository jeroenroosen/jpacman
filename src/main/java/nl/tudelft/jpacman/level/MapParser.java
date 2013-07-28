package nl.tudelft.jpacman.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nl.tudelft.jpacman.factory.BoardFactory;
import nl.tudelft.jpacman.factory.CharacterFactory;
import nl.tudelft.jpacman.factory.LevelFactory;
import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.GhostColour;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.Square;

/**
 * Parses text representations of {@link Board}s into {@link Level}s.
 * 
 * @author Jeroen Roosen
 */
public class MapParser {

	/**
	 * The order in which Ghosts are created.
	 */
	private static final GhostColour[] GHOST_ORDER = { GhostColour.RED,
			GhostColour.PINK, GhostColour.CYAN, GhostColour.ORANGE };

	/**
	 * The default direction characters are facing.
	 */
	private static final Direction DEFAULT_DIRECTION = Direction.WEST;

	/**
	 * The char for an empty square.
	 */
	private static final char EMPTY_SQUARE = ' ';

	/**
	 * The char for a square with a wall.
	 */
	private static final char WALL = '#';

	/**
	 * The char for a Pac-Man.
	 */
	private static final char PACMAN = 'P';

	/**
	 * The char for a pellet.
	 */
	private static final char PELLET = '.';

	/**
	 * The char for a ghost.
	 */
	private static final char GHOST = 'G';

	/**
	 * The factory that will create the board.
	 */
	private final BoardFactory boardFactory;

	/**
	 * The factory that will create the level.
	 */
	private final LevelFactory levelFactory;

	/**
	 * The factory that will create the characters.
	 */
	private final CharacterFactory characterFactory;

	/**
	 * The current ghost index.
	 */
	private int ghostIndex;

	/**
	 * Creates a new map parser that will use the provided factories to create
	 * {@link Level}s.
	 * 
	 * @param levelFactory
	 * @param boardFactory
	 * @param characterFactory
	 */
	public MapParser(LevelFactory levelFactory, BoardFactory boardFactory,
			CharacterFactory characterFactory) {
		this.levelFactory = levelFactory;
		this.boardFactory = boardFactory;
		this.characterFactory = characterFactory;

		this.ghostIndex = 0;
	}

	/**
	 * Parses a string representation of a Board into a new Level.
	 * 
	 * @param rows
	 *            The rows of the board.
	 * @return The parsed level.
	 * @throws MapParserException
	 *             When an invalid character is encountered.
	 */
	public Level parseMap(String[] rows) throws MapParserException {
		assert rows != null;

		int height = rows.length;
		enforceHeight(height);
		int width = rows[0].length();

		Square[][] grid = new Square[width][height];
		LevelBuilder builder = new LevelBuilder();
		for (int y = 0; y < height; y++) {
			char[] row = (rows[y]).toCharArray();
			enforceWidth(width, y, row);
			for (int x = 0; x < width; x++) {
				grid[x][y] = parseSquare(row[x], builder);
			}
		}
		Board board = boardFactory.createBoard(grid);
		connectGraph(board);

		return builder.withBoard(board).build(levelFactory);
	}

	/**
	 * Connects the squares on the board to each other, creating a connected
	 * graph of squares.
	 * 
	 * @param board
	 *            The board to connect.
	 */
	private void connectGraph(Board board) {
		int w = board.getWidth();
		int h = board.getHeight();

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Square node = board.getSquareAt(x, y);
				for (Direction d : Direction.values()) {
					Square neighbour = relativeSquare(x, y, board, d);
					node.addNeighbour(neighbour, d);
				}
			}
		}
	}

	/**
	 * Determines and returns the square touching the square at [x,y] in the
	 * given direction as seen from the square at [x,y]. Keep in mind that the
	 * board's edges are connected to their opposite sides, so the left-most
	 * square is connected to the right-most square and vice-versa, same for the
	 * vertical edges.
	 * 
	 * @param x
	 *            The x position of the square.
	 * @param y
	 *            The y position of the square.
	 * @param board
	 *            The board the square is on.
	 * @param d
	 *            The direction to look in.
	 * @return The adjacent square in the direction as seen from the square at
	 *         [x,y].
	 */
	private Square relativeSquare(int x, int y, Board board, Direction d) {
		int w = board.getWidth();
		int h = board.getHeight();

		int newX = (w + x + d.getDeltaX()) % w;
		int newY = (h + y + d.getDeltaY()) % h;

		return board.getSquareAt(newX, newY);
	}

	/**
	 * Enforces that a row has the same amount of elements as the width of the
	 * board and that the board has at least 1 column.
	 * 
	 * @param width
	 *            The desired amount of elements.
	 * @param y
	 *            The y position of this row.
	 * @param row
	 *            The row to check.
	 * @throws MapParserException
	 *             When the row did not have the correct amount of elements or
	 *             the given width is 0 or less.
	 */
	private void enforceWidth(int width, int y, char[] row)
			throws MapParserException {
		if (width <= 0) {
			throw new MapParserException("Board should have at least 1 column.");
		}
		if (row.length != width) {
			throw new MapParserException(
					"Encountered a row with an unexpected amount of cells at row "
							+ y);
		}
	}

	/**
	 * Enforces that a board has at least a single row.
	 * 
	 * @param height
	 *            The height of the board.
	 * @throws MapParserException
	 *             If the board doesn't have at least a single row.
	 */
	private void enforceHeight(int height) throws MapParserException {
		if (height == 0) {
			throw new MapParserException(
					"Unable to create a level for an empty map (no columns).");
		}
	}

	/**
	 * @return The board factory used by this parser.
	 */
	protected BoardFactory getBoardFactory() {
		return boardFactory;
	}

	/**
	 * @return The level factory used by this parser.
	 */
	protected LevelFactory getLevelFactory() {
		return levelFactory;
	}

	/**
	 * @return The character factory used by this parser.
	 */
	protected CharacterFactory getCharacterFactory() {
		return characterFactory;
	}

	/**
	 * Parses a square and assigns all relevant content to the level builder.
	 * 
	 * @param c
	 *            The character representation of the square to parse.
	 * @param builder
	 *            The level builder to which relevant data can be assigned.
	 * @return A new square as represented by the character.
	 * @throws MapParserException
	 *             When the character was invalid.
	 */
	protected Square parseSquare(char c, LevelBuilder builder)
			throws MapParserException {
		assert builder != null;

		switch (c) {
		case WALL:
			return getBoardFactory().createWallSquare();
		case EMPTY_SQUARE:
			return emptySquare();
		case PACMAN:
			return pacManSquare(builder);
		case PELLET:
			return pelletSquare();
		case GHOST:
			return ghostSquare(builder);
		default:
			throw new MapParserException("Unable to parse square for: " + c);
		}
	}

	/**
	 * @return The next ghost colour.
	 */
	private GhostColour nextGhostColour() {
		GhostColour colour = GHOST_ORDER[ghostIndex];
		ghostIndex++;
		ghostIndex %= GHOST_ORDER.length;
		return colour;
	}

	/**
	 * @return A new square with a pellet on it.
	 */
	private Square pelletSquare() {
		FloorSquare square = emptySquare();
		Pellet pellet = getBoardFactory().createPellet();
		square.setPellet(pellet);
		return square;
	}

	/**
	 * @return A new square with nothing on it.
	 */
	private FloorSquare emptySquare() {
		FloorSquare square = getBoardFactory().createFloorSquare();
		return square;
	}

	/**
	 * @param builder
	 *            The builder to link the Pac-Man.
	 * @return A new square with a Pac-Man on it.
	 */
	private Square pacManSquare(LevelBuilder builder) {
		FloorSquare square = emptySquare();
		PacMan pacMan = getCharacterFactory().createPacMan(DEFAULT_DIRECTION);
		pacMan.occupy(square);
		builder.addPacMan(pacMan);
		return square;
	}

	/**
	 * @param builder
	 *            The builder to link the ghost.
	 * @return A new square with a Ghost on it.
	 */
	private Square ghostSquare(LevelBuilder builder) {
		FloorSquare square = emptySquare();
		Ghost ghost = characterFactory.createGhost(nextGhostColour(),
				DEFAULT_DIRECTION);
		ghost.occupy(square);
		builder.addGhost(ghost);
		return square;
	}

	/**
	 * Parses a string representation of a Board into a new Level.
	 * 
	 * @param inputStream
	 *            The input stream providing the map.
	 * @return The parsed level.
	 * @throws MapParserException
	 *             When an invalid character is encountered.
	 */
	public Level parseMap(InputStream inputStream) throws MapParserException {
		assert inputStream != null;

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));

		List<String> rows = new ArrayList<String>();
		try {
			while (reader.ready()) {
				rows.add(reader.readLine());
			}
		} catch (IOException e) {
			throw new MapParserException("Unable to read input stream.", e);
		}

		return parseMap(rows.toArray(new String[rows.size()]));
	}

	/**
	 * Exception thrown by MapParser when components could not be parsed.
	 * 
	 * @author Jeroen Roosen
	 */
	public static class MapParserException extends Exception {

		/**
		 * Generated SVUID.
		 */
		private static final long serialVersionUID = -2031847716998937467L;

		/**
		 * Create a new exception.
		 * 
		 * @param message
		 *            The message describing the cause of this exception.
		 */
		public MapParserException(String message) {
			super(message);
		}

		/**
		 * Create a new exception.
		 * 
		 * @param message
		 *            The message describing the cause of this exception.
		 * @param cause
		 *            The cause of this exception.
		 */
		public MapParserException(String message, Throwable cause) {
			super(message, cause);
		}

	}

	/**
	 * Utility to build a level.
	 * 
	 * @author Jeroen Roosen
	 */
	protected class LevelBuilder {

		/**
		 * The Pac-Mans on the board.
		 */
		private Collection<PacMan> pacMans;

		/**
		 * The ghosts on the board.
		 */
		private Collection<Ghost> ghosts;

		/**
		 * The board.
		 */
		private Board board;

		/**
		 * Creates a new level builder.
		 */
		protected LevelBuilder() {
			ghosts = new ArrayList<>();
			pacMans = new ArrayList<>();
		}

		/**
		 * Adds a Pac-Man to this level.
		 * 
		 * @param pacMan
		 *            the Pac-Man to add to this Level.
		 * @return The builder for fluency.
		 */
		protected LevelBuilder addPacMan(PacMan pacMan) {
			assert pacMan != null;

			pacMans.add(pacMan);
			return this;
		}

		/**
		 * Adds a ghost to the level.
		 * 
		 * @param ghost
		 *            The ghost to add.
		 * @return The builder for fluency.
		 */
		protected LevelBuilder addGhost(Ghost ghost) {
			assert ghost != null;

			ghosts.add(ghost);
			return this;
		}

		/**
		 * Sets the board for this level.
		 * 
		 * @param levelBoard
		 *            The board to set for this Level.
		 * @return The builder for fluency.
		 */
		protected LevelBuilder withBoard(Board levelBoard) {
			assert levelBoard != null;

			this.board = levelBoard;
			return this;
		}

		/**
		 * Builds the level.
		 * 
		 * @param factory
		 *            The factory to build the level with.
		 * @return A new level with the board, Pac-Man and ghosts.
		 */
		protected Level build(LevelFactory factory) {
			assert board != null;
			return factory.createLevel(board, pacMans, ghosts);
		}
	}
}
