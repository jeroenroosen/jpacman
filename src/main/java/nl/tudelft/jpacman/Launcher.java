package nl.tudelft.jpacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;

import nl.tudelft.jpacman.factory.DefaultFactory;
import nl.tudelft.jpacman.game.GhostController;
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
import nl.tudelft.jpacman.gui.BoardPanel;
import nl.tudelft.jpacman.gui.ButtonPanel;
import nl.tudelft.jpacman.gui.ClassicScorePanel;
import nl.tudelft.jpacman.gui.GamePanel;
import nl.tudelft.jpacman.gui.PButton;
import nl.tudelft.jpacman.gui.PacManUI;
import nl.tudelft.jpacman.gui.ScorePanel;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.MapParser.MapParserException;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Ghost;
import nl.tudelft.jpacman.model.PacMan;
import nl.tudelft.jpacman.model.Pellet;
import nl.tudelft.jpacman.model.WallSquare;

/**
 * This class will eventually become a proper launcher with something like a
 * builder to create a new GUI.
 * 
 * @author Jeroen Roosen
 */
public class Launcher {

	// TODO split into small pieces.

	public static void main(String[] args) throws MapParserException {

		DefaultFactory factory = new DefaultFactory();
		MapParser mapParser = new MapParser(factory, factory, factory);

		InputStream inputStream = Launcher.class
				.getResourceAsStream("/board.txt");
		Level level = mapParser.parseMap(inputStream);

		GhostController ghostController = new RandomGhostController(level);

		final SinglePlayerGame game = new SinglePlayerGame(level,
				ghostController);

		ClassicSpriteStore store = new ClassicSpriteStore();
		Renderers renderers = new Renderers();
		renderers.registerRenderer(WallSquare.class, new WallRenderer());
		renderers.registerRenderer(FloorSquare.class, new FloorRenderer(
				renderers));
		renderers.registerRenderer(Pellet.class, new PelletRenderer(store));
		renderers.registerRenderer(PacMan.class, new PacManRenderer(store));
		renderers.registerRenderer(Ghost.class, new GhostRenderer(store));

		BoardPanel boardPanel = new BoardPanel(level.getBoard(),
				new ClassicBoardRenderer(renderers));

		ScorePanel scorePanel = new ClassicScorePanel(new ArrayList<>(
				level.getPacMans()));

		GamePanel gamePanel = new GamePanel(scorePanel, boardPanel);

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
					System.out.println("up");
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
		
		

		final PButton startButton = new PButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.start();
			}
		});
		startButton.requestFocusInWindow();
		
		PButton stopButton = new PButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.stop();
			}
		});
		
		ButtonPanel buttonPanel = new ButtonPanel(startButton, stopButton);
		new PacManUI(gamePanel, buttonPanel, keyListener);
	}

}
