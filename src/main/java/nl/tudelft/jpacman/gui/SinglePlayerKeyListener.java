package nl.tudelft.jpacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import nl.tudelft.jpacman.game.SinglePlayerGame;

/**
 * Default implementation of a KeyListener for a single player game.
 * 
 * @author Jeroen Roosen
 */
public class SinglePlayerKeyListener implements KeyListener {

	/**
	 * The game to send the events to.
	 */
	private final SinglePlayerGame singlePlayer;

	/**
	 * Creates a new key listener.
	 * 
	 * @param game
	 *            The game to forward the key-events to.
	 */
	public SinglePlayerKeyListener(SinglePlayerGame game) {
		this.singlePlayer = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignore
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			singlePlayer.up();
			break;
			
		case KeyEvent.VK_DOWN:
			singlePlayer.down();
			break;
			
		case KeyEvent.VK_LEFT:
			singlePlayer.left();
			break;
			
		case KeyEvent.VK_RIGHT:
			singlePlayer.right();
			break;
			
		default:
			// do nothing
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ignore
	}

}
