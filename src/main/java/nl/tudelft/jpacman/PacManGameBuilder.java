package nl.tudelft.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.jpacman.factory.BoardFactory;
import nl.tudelft.jpacman.factory.CharacterFactory;
import nl.tudelft.jpacman.factory.DefaultFactory;
import nl.tudelft.jpacman.factory.DefaultGameFactory;
import nl.tudelft.jpacman.factory.GameFactory;
import nl.tudelft.jpacman.factory.LevelFactory;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GhostController;
import nl.tudelft.jpacman.game.RandomGhostController;
import nl.tudelft.jpacman.game.SinglePlayerGame;
import nl.tudelft.jpacman.graphics.renderer.ClassicBoardRenderer;
import nl.tudelft.jpacman.graphics.renderer.FloorRenderer;
import nl.tudelft.jpacman.graphics.renderer.GhostRenderer;
import nl.tudelft.jpacman.graphics.renderer.PacManRenderer;
import nl.tudelft.jpacman.graphics.renderer.PelletRenderer;
import nl.tudelft.jpacman.graphics.renderer.Renderer;
import nl.tudelft.jpacman.graphics.renderer.Renderers;
import nl.tudelft.jpacman.graphics.renderer.WallRenderer;
import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.gui.BoardPanel;
import nl.tudelft.jpacman.gui.ButtonPanel;
import nl.tudelft.jpacman.gui.ClassicScorePanel;
import nl.tudelft.jpacman.gui.GamePanel;
import nl.tudelft.jpacman.gui.PButton;
import nl.tudelft.jpacman.gui.PacManUI;
import nl.tudelft.jpacman.gui.ScorePanel;
import nl.tudelft.jpacman.gui.SinglePlayerKeyListener;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.MapParser.MapParserException;
import nl.tudelft.jpacman.model.Board;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * Builder to create new JPacMan games. This class takes care of all the wiring
 * and provides default values for all components.
 * 
 * @author Jeroen Roosen
 */
public class PacManGameBuilder {

	/**
	 * The key listener that will forward key events to the {@link #game}.
	 */
	private KeyListener keyListener;

	/**
	 * The internal {@link Game}.
	 */
	private Game game;

	/**
	 * The list of {@link Level} that will be used by the {@link #game}.
	 */
	private List<Level> levels;

	/**
	 * The {@link DefaultFactory} instance.
	 */
	private DefaultFactory defaultFact;

	/**
	 * The character factory to create levels.
	 */
	private CharacterFactory charFact;

	/**
	 * The board factory to create levels.
	 */
	private BoardFactory boardFact;

	/**
	 * The level factory to create levels.
	 */
	private LevelFactory levelFact;

	/**
	 * The game factory to create the {@link #game}.
	 */
	private GameFactory gameFact;

	/**
	 * The AI controlling the ghosts for the {@link #game}.
	 */
	private GhostController ai;

	/**
	 * The panel containing the buttons.
	 */
	private ButtonPanel buttonPanel;

	/**
	 * The renderer that will be used to draw the board in real time.
	 */
	private Renderer<Board> boardRenderer;

	/**
	 * Hidden constructor to prevent instantiation.
	 */
	private PacManGameBuilder() {
		// hide constructor to prevent instantiation.
	}

	/**
	 * Create a new builder with the default classic single player setup.
	 * 
	 * @return The new builder with the default set up.
	 */
	public static PacManGameBuilder newBuilder() {
		return new PacManGameBuilder();
	}

	/**
	 * Builds the PacManUI using the set components and default values where the
	 * are lacking.
	 * 
	 * @return The PacManUI.
	 */
	public PacManUI buildUI() {
		return new PacManUI(getGamePanel(), getButtonPanel(), getKeyListener());
	}

	/**
	 * @return The set key listener, or the default one.
	 */
	private KeyListener getKeyListener() {
		if (keyListener != null) {
			return keyListener;
		}
		return defaultKeyListener();
	}

	/**
	 * Creates the default single {@link SinglePlayerKeyListener}.
	 * 
	 * @return The default single player key listener.
	 */
	private KeyListener defaultKeyListener() {
		Game game = getGame();
		if (game instanceof SinglePlayerGame) {
			return new SinglePlayerKeyListener((SinglePlayerGame) game);
		}
		throw new UnsupportedOperationException(
				"Unable to create default key listener for type "
						+ game.getClass());
	}

	/**
	 * Sets the provided key listener as the key listener.
	 * 
	 * @param listener
	 *            The key listener to set for this builder.
	 * @return This builder for fluency.
	 */
	public PacManGameBuilder withKeyListener(KeyListener listener) {
		this.keyListener = listener;
		return this;
	}

	/**
	 * @return The set game or creates a new instance if none was set.
	 */
	private Game getGame() {
		if (game == null) {
			game = createGame();
		}
		return game;
	}

	/**
	 * @return A new game instance.
	 */
	private Game createGame() {
		GameFactory factory = getGameFactory();
		return factory.createGame(getLevels(), getGhostController());
	}

	/**
	 * @return The list of levels, or the default levels if none are set.
	 */
	private List<Level> getLevels() {
		if (levels == null) {
			levels = defaultLevels();
		}
		return levels;
	}

	/**
	 * Sets the list of levels to use. Using will cause factories set by
	 * {@link #withBoardFactory(BoardFactory)},
	 * {@link #withCharacterFactory(CharacterFactory)} and
	 * {@link #withLevelFactory(LevelFactory)} to be ignored.
	 * 
	 * @param levelList
	 *            The levels to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withLevels(List<Level> levelList) {
		this.levels = levelList;
		return this;
	}

	/**
	 * Loads the default (single) level from the packaged resource.
	 * 
	 * @return The default list of levels.
	 */
	private List<Level> defaultLevels() {
		MapParser parser = new MapParser(getLevelFactory(), getBoardFactory(),
				getCharacterFactory());
		Level level;
		try {
			level = parser.parseMap(PacManGameBuilder.class
					.getResourceAsStream("/board.txt"));
		} catch (MapParserException e) {
			throw new RuntimeException("Unable to load default board.");
		}
		List<Level> levels = new ArrayList<>();
		levels.add(level);
		return levels;
	}

	/**
	 * @return The {@link CharacterFactory} if one was set, or the default
	 *         factory if not.
	 */
	private CharacterFactory getCharacterFactory() {
		if (charFact == null) {
			charFact = getDefaultFactory();
		}
		return charFact;
	}

	/**
	 * Sets the character factory used to create levels.
	 * 
	 * @param factory
	 *            The factory to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withCharacterFactory(CharacterFactory factory) {
		this.charFact = factory;
		return this;
	}

	/**
	 * @return The {@link DefaultFactory} instance.
	 */
	private DefaultFactory getDefaultFactory() {
		if (defaultFact == null) {
			defaultFact = new DefaultFactory();
		}
		return defaultFact;
	}

	/**
	 * @return The set board factory, or the default factory instance if none
	 *         was set.
	 */
	private BoardFactory getBoardFactory() {
		if (boardFact == null) {
			boardFact = getDefaultFactory();
		}
		return boardFact;
	}

	/**
	 * Sets the board factory used to create levels.
	 * 
	 * @param factory
	 *            The factory to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withBoardFactory(BoardFactory factory) {
		this.boardFact = factory;
		return this;
	}

	/**
	 * @return The set level factory, or the default factory instance if none
	 *         was set.
	 */
	private LevelFactory getLevelFactory() {
		if (levelFact == null) {
			levelFact = getDefaultFactory();
		}
		return levelFact;
	}

	/**
	 * Sets the factory to create the level.
	 * 
	 * @param factory
	 *            The factory to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withLevelFactory(LevelFactory factory) {
		this.levelFact = factory;
		return this;
	}

	/**
	 * @return The set game factory, or a new {@link DefaultGameFactory}
	 *         instance if none was set.
	 */
	private GameFactory getGameFactory() {
		if (gameFact == null) {
			gameFact = new DefaultGameFactory();
		}
		return gameFact;
	}

	/**
	 * Sets the factory to create the game.
	 * 
	 * @param factory
	 *            The factory to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withGameFactory(GameFactory factory) {
		this.gameFact = factory;
		return this;
	}

	/**
	 * @return The set ghost controller, or the default one if none was set.
	 */
	private GhostController getGhostController() {
		if (ai == null) {
			ai = createGhostController();
		}
		return ai;
	}

	/**
	 * Creates a new {@link RandomGhostController}.
	 * 
	 * @return A new random ghost controller.
	 */
	private GhostController createGhostController() {
		return new RandomGhostController(getLevels().get(0));
	}

	/**
	 * Sets the AI.
	 * 
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withGhostController(GhostController controller) {
		this.ai = controller;
		return this;
	}

	/**
	 * @return The set button panel, or the default panel if none was set.
	 */
	private ButtonPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = createButtonPanel();
		}
		return buttonPanel;
	}

	/**
	 * Sets the panel containing the buttons.
	 * 
	 * @param panel
	 *            The panel containing the buttons.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withButtonPanel(ButtonPanel panel) {
		this.buttonPanel = panel;
		return this;
	}

	/**
	 * @return A default button panel.
	 */
	private ButtonPanel createButtonPanel() {
		PButton startButton = createStartButton();
		PButton stopButton = createStopButton();

		return new ButtonPanel(startButton, stopButton);
	}

	/**
	 * @return A default stop button.
	 */
	private PButton createStopButton() {
		PButton stopButton = new PButton("Stop");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getGame().stop();
			}
		});
		return stopButton;
	}

	/**
	 * @return A default start button.
	 */
	private PButton createStartButton() {
		PButton startButton = new PButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getGame().start();
			}
		});
		startButton.requestFocusInWindow();
		return startButton;
	}

	/**
	 * @return A new game panel.
	 */
	private GamePanel getGamePanel() {
		ScorePanel scorePanel = getScorePanel();
		BoardPanel boardPanel = new BoardPanel(getGame().getCurrentLevel()
				.getBoard(), getBoardRenderer());
		return new GamePanel(scorePanel, boardPanel);
	}

	/**
	 * @return The set board renderer, or the default one if none was set.
	 */
	private Renderer<Board> getBoardRenderer() {
		if (boardRenderer == null) {
			return getDefaultBoardRenderer();
		}
		return boardRenderer;
	}

	/**
	 * Sets the renderer used to draw the board in real time.
	 * 
	 * @param renderer
	 *            The renderer to use.
	 * @return The builder for fluency.
	 */
	public PacManGameBuilder withBoardRenderer(Renderer<Board> renderer) {
		this.boardRenderer = renderer;
		return this;
	}

	/**
	 * Creates and returns a board renderer for classic PacMan.
	 * 
	 * @return The default, classic board renderer.
	 */
	private Renderer<Board> getDefaultBoardRenderer() {
		ClassicSpriteStore store = new ClassicSpriteStore();
		Renderers renderers = new Renderers();
		renderers.registerRenderer(WallSquare.class, new WallRenderer());
		renderers.registerRenderer(FloorSquare.class, new FloorRenderer(
				renderers));
		renderers.registerRenderer(Pellet.class, new PelletRenderer(store));
		renderers.registerRenderer(PacMan.class, new PacManRenderer(store));
		renderers.registerRenderer(Ghost.class, new GhostRenderer(store));

		return new ClassicBoardRenderer(renderers);
	}

	/**
	 * @return A new classic score panel
	 */
	private ScorePanel getScorePanel() {
		ArrayList<PacMan> players = new ArrayList<>(getGame().getCurrentLevel()
				.getPacMans());
		return new ClassicScorePanel(players);
	}
}
