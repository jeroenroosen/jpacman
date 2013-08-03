package nl.tudelft.jpacman;

import nl.tudelft.jpacman.level.MapParser.MapParserException;

/**
 * This class will eventually become a proper launcher with something like a
 * builder to create a new GUI.
 * 
 * @author Jeroen Roosen
 */
public final class Launcher {

	/**
	 * Starts the default JPacMan.
	 * 
	 * @param args
	 *            Ignored.
	 * @throws MapParserException
	 */
	public static void main(String[] args) {
		PacManGameBuilder.newBuilder().buildUI().start();
	}
}
