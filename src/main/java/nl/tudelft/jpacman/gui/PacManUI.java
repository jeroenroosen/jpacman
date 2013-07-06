package nl.tudelft.jpacman.gui;

import javax.swing.JFrame;

import nl.tudelft.jpacman.factory.DefaultFactory;
import nl.tudelft.jpacman.graphics.ClassicBoardRenderer;
import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.MapParser.MapParserException;

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

		DefaultFactory df = new DefaultFactory();
		Level level;
		try {
			level = new MapParser(df, df, df).parseMap(PacManUI.class
					.getResourceAsStream("/board.txt"));
		} catch (MapParserException e) {
			throw new RuntimeException(e);
		}

		boardPanel = new BoardPanel(level.getBoard(), new ClassicBoardRenderer(
				new ClassicSpriteStore()));

		frame.getContentPane().add(boardPanel);
		frame.setVisible(true);
		frame.pack();

		startGame();
	}

	private void startGame() {
		while (true) {

			boardPanel.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new PacManUI().launch();
	}
}
