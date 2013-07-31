package nl.tudelft.jpacman.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import nl.tudelft.jpacman.game.Game;

/**
 * A {@link JPanel} that will visualize a {@link Game}. The panel will consist
 * of a top and a bottom section, respectively showing the score and the game.
 * 
 * @author Jeroen Roosen
 */
public class GamePanel extends JPanel {
	
	/**
	 * Generated SVUID.
	 */
	private static final long serialVersionUID = 6138816209904462363L;

	/**
	 * Creates a new Panel.
	 * @param scorePanel The score panel to put at the top.
	 * @param boardPanel The board panel to put at the bottom.
	 */
	public GamePanel(ScorePanel scorePanel, BoardPanel boardPanel) {
		super(new BorderLayout());
		add(scorePanel, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
	}
}
