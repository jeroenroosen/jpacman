package nl.tudelft.jpacman.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.model.PacMan;

/**
 * {@link JPanel} that displays the current score of a game.
 * 
 * @author Jeroen Roosen
 */
public class ScorePanel extends JPanel {

	/**
	 * The generated SVUID.
	 */
	private static final long serialVersionUID = -1578357801220230420L;

	/**
	 * Creates a new grid of 2 rows and columns equal to the amount of players.
	 * The top line will display the numbered player names and the bottom row
	 * will display each player's score.
	 * 
	 * @param players
	 *            The players to display.
	 */
	public ScorePanel(List<PacMan> players) {
		super(new GridLayout(2, players.size()));
		for (int i = 1; i <= players.size(); i++) {
			add(newNameLabel("Player " + i));
		}
		for (int i = 0; i < players.size(); i++) {
			add(newScoreLine(players.get(i)));
		}
	}

	/**
	 * Creates a new name label.
	 * 
	 * @param tag
	 *            The name of the player.
	 * @return A new name label.
	 */
	protected JLabel newNameLabel(String tag) {
		return new JLabel(tag, JLabel.CENTER);
	}

	protected JLabel newScoreLine(PacMan pacMan) {
		return new PlayerLabel(pacMan);
	}

	/**
	 * Label that keeps track of a Player to display his score.
	 * 
	 * @author Jeroen Roosen
	 */
	protected class PlayerLabel extends JLabel {

		/**
		 * The generated SVUID.
		 */
		private static final long serialVersionUID = -316066535067736258L;
		/**
		 * The player to track.
		 */
		private final PacMan player;

		/**
		 * Creates a new label that tracks the given player.
		 * 
		 * @param pacMan
		 *            The player to track.
		 */
		PlayerLabel(PacMan pacMan) {
			super(String.valueOf(pacMan.getScore()), JLabel.CENTER);
			this.player = pacMan;
		}

		@Override
		public void paint(Graphics g) {
			setText(String.valueOf(player.getScore()));
			super.paint(g);
		}
	}
}
