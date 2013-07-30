package nl.tudelft.jpacman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;

import nl.tudelft.jpacman.model.PacMan;

/**
 * Score panel that displays its contents white-on-black.
 * 
 * @author Jeroen Roosen
 */
public class ClassicScorePanel extends ScorePanel {

	/**
	 * The generated SVUID.
	 */
	private static final long serialVersionUID = -8338648485239287344L;

	/**
	 * Creates a new {@link ScorePanel} with white-on-black look and feel.
	 * 
	 * @param players
	 *            The players to display.
	 */
	public ClassicScorePanel(List<PacMan> players) {
		super(players);
		setBackground(Color.BLACK);
	}

	@Override
	protected JLabel newScoreLine(PacMan pacMan) {
		JLabel scoreLine = super.newScoreLine(pacMan);
		scoreLine.setForeground(Color.WHITE);
		scoreLine.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		return scoreLine;
	}

	@Override
	protected JLabel newNameLabel(String tag) {
		JLabel nameLine = super.newNameLabel(tag);
		nameLine.setForeground(Color.WHITE);
		nameLine.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		return nameLine;
	}
}
