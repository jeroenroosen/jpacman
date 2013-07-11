package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.model.Pellet;

/**
 * Renders pellets.
 * 
 * @author Jeroen Roosen
 */
public class PelletRenderer implements Renderer<Pellet> {

	private final ClassicSpriteStore sprites;

	/**
	 * Creates a new pellet renderer.
	 * 
	 * @param sprites
	 *            The sprite provider.
	 */
	public PelletRenderer(ClassicSpriteStore sprites) {
		this.sprites = sprites;
	}

	@Override
	public void render(Pellet subject, Graphics g, Dimension dim) {
		sprites.getPelletSprite().draw(g, 0, 0, dim.width, dim.height);
	}
}
