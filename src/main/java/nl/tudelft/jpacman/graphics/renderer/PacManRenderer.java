package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.graphics.sprite.Sprite;
import nl.tudelft.jpacman.model.PacMan;

/**
 * Renders PacMan sprites.
 * 
 * @author Jeroen Roosen
 */
public class PacManRenderer implements Renderer<PacMan> {

	/**
	 * The sprite store containing sprites for Pac-Man.
	 */
	private final ClassicSpriteStore sprites;

	/**
	 * Creates a new Pac-Man renderer.
	 * 
	 * @param spriteStore
	 *            The sprite provider.
	 */
	public PacManRenderer(ClassicSpriteStore spriteStore) {
		this.sprites = spriteStore;
	}

	@Override
	public void render(PacMan pacman, Graphics g,
			final Dimension dim) {
		
		Sprite sprite;
		if (pacman.isAlive()) {
			sprite = sprites.getPacmanSprite(pacman.getDirection());
		}
		else {
			sprite = sprites.getDeadPacManSprite();
		}
		sprite.draw(g, 0, 0, dim.width, dim.height);
	}
}
