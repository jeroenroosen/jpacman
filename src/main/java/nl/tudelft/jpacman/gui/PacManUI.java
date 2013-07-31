package nl.tudelft.jpacman.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Bit of a placeholder class to run the whole thing to see if it works.
 * 
 * @author Jeroen Roosen
 * 
 */
public class PacManUI extends JFrame {

	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = -5643926594210349731L;

	/**
	 * Default desired frame rate in frames per second (fps).
	 */
	private static final int FRAME_RATE = 50;

	/**
	 * The panel that will be redrawn with every frame.
	 */
	private GamePanel displayPanel;

	/**
	 * Creates a new PacManUI combining the {@link GamePanel} and the
	 * {@link KeyListener} into a runnable GUI.
	 * 
	 * @param gamePanel
	 *            The {@link GamePanel} to show.
	 * @param buttonPanel
	 *            The button panel to put at the bottom of the GUI.
	 * @param keyListener
	 *            The {@link KeyListener} to process key events.
	 */
	public PacManUI(GamePanel gamePanel, ButtonPanel buttonPanel,
			KeyListener keyListener) {
		super("JPacMan");
		this.displayPanel = gamePanel;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(keyListener);
		setFocusTraversalKeysEnabled(false);
		requestFocusInWindow();

		Container contentPanel = getContentPane();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(displayPanel, BorderLayout.CENTER);
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.setParentWindow(this);

		setVisible(true);
		pack();

		requestFocusInWindow();
		
		run();
	}

	/**
	 * Starts the repainting job, repainting the interface at the desired frame
	 * rate.
	 */
	private void run() {
		long interval = 1000 / FRAME_RATE;

		while (true) {
			long start = System.currentTimeMillis();

			displayPanel.repaint();

			long delay = System.currentTimeMillis() - start;

			try {
				Thread.sleep(Math.max(0, interval - delay));
			} catch (InterruptedException e) {
				// ignore.
			}
		}
	}
}
