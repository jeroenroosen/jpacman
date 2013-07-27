package nl.tudelft.jpacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import nl.tudelft.jpacman.factory.DefaultFactory;
import nl.tudelft.jpacman.game.RandomGhostController;
import nl.tudelft.jpacman.game.SinglePlayerGame;
import nl.tudelft.jpacman.graphics.renderer.ClassicBoardRenderer;
import nl.tudelft.jpacman.graphics.renderer.FloorRenderer;
import nl.tudelft.jpacman.graphics.renderer.GhostRenderer;
import nl.tudelft.jpacman.graphics.renderer.PacManRenderer;
import nl.tudelft.jpacman.graphics.renderer.PelletRenderer;
import nl.tudelft.jpacman.graphics.renderer.Renderers;
import nl.tudelft.jpacman.graphics.renderer.WallRenderer;
import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.MapParser.MapParserException;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * Bit of a placeholder class to run the whole thing to see if it works.
 * 
 * @author Jeroen Roosen
 * 
 */
public class PacManUI {

	private BoardPanel boardPanel;

	public PacManUI() {
	}

	public void launch() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create level
		DefaultFactory df = new DefaultFactory();
		Level level;
		try {
			level = new MapParser(df, df, df).parseMap(PacManUI.class
					.getResourceAsStream("/board.txt"));
		} catch (MapParserException e) {
			throw new RuntimeException(e);
		}
		
		ClassicSpriteStore store = new ClassicSpriteStore();
		Renderers renderers = new Renderers();
		renderers.registerRenderer(WallSquare.class, new WallRenderer());
		renderers.registerRenderer(FloorSquare.class, new FloorRenderer(renderers));
		renderers.registerRenderer(Pellet.class, new PelletRenderer(store));
		renderers.registerRenderer(PacMan.class, new PacManRenderer(store));
		renderers.registerRenderer(Ghost.class, new GhostRenderer(store));

		boardPanel = new BoardPanel(level.getBoard(), new ClassicBoardRenderer(renderers));

		// make game / link keys

		final SinglePlayerGame game = new SinglePlayerGame(level, new RandomGhostController(level));
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// ignore
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// ignore
			}

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					game.up();
					break;

				case KeyEvent.VK_DOWN:
					game.down();
					break;

				case KeyEvent.VK_LEFT:
					game.left();
					break;

				case KeyEvent.VK_RIGHT:
					game.right();
					break;
				default:
					// do nothing
					break;
				}

			}
		};
		frame.addKeyListener(keyListener);
		frame.setFocusTraversalKeysEnabled(false);

		frame.getContentPane().add(boardPanel);
		frame.setVisible(true);
		frame.pack();

		game.start();
		startGame();
	}

	private void startGame() {
		while (true) {
			boardPanel.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new PacManUI().launch();
	}
}
