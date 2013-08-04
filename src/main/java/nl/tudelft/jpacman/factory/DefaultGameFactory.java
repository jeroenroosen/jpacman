package nl.tudelft.jpacman.factory;

import java.util.List;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.SinglePlayerGame;
import nl.tudelft.jpacman.level.Level;

/**
 * Creates new instances of {@link SinglePlayerGame} for single levels.
 * 
 * @author Jeroen Roosen
 */
public class DefaultGameFactory implements GameFactory {

	@Override
	public Game createGame(List<Level> levels) {
		assert levels != null;
		
		if (levels.size() != 1) {
			throw new UnsupportedOperationException("This factory can only provide games that contain a single level.");
		}
		
		Level level = levels.get(0);
		
		return new SinglePlayerGame(level);
	}

}
