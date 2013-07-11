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

	private final ClassicSpriteStore sprites;

	/**
	 * Creates a new Pac-Man renderer.
	 * 
	 * @param sprites
	 *            The sprite provider.
	 */
	public PacManRenderer(ClassicSpriteStore sprites) {
		this.sprites = sprites;
	}

	@Override
	public void render(PacMan pacman, Graphics g, Dimension dim) {
		Sprite sprite = sprites.getPacmanSprite(pacman.getDirection());
		sprite.draw(g, 0, 0, dim.width, dim.height);
	}
}
