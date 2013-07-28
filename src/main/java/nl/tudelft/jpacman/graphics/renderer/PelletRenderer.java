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

	/**
	 * Sprite store containing sprites for pellets.
	 */
	private final ClassicSpriteStore sprites;

	/**
	 * Creates a new pellet renderer.
	 * 
	 * @param spriteStore
	 *            The sprite provider.
	 */
	public PelletRenderer(ClassicSpriteStore spriteStore) {
		this.sprites = spriteStore;
	}

	@Override
	public void render(Pellet subject, Graphics g, Dimension dim) {
		sprites.getPelletSprite().draw(g, 0, 0, dim.width, dim.height);
	}
}
