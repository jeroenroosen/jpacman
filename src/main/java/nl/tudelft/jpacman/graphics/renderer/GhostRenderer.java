package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.graphics.sprite.ClassicSpriteStore;
import nl.tudelft.jpacman.graphics.sprite.Sprite;
import nl.tudelft.jpacman.model.Ghost;

/**
 * Renders ghosts.
 * 
 * @author Jeroen Roosen
 */
public class GhostRenderer implements Renderer<Ghost> {

	/**
	 * The sprite store containing sprites for the different ghosts.
	 */
	private final ClassicSpriteStore sprites;

	/**
	 * Creates a new ghost renderer.
	 * 
	 * @param spriteStore
	 *            The sprite provider.
	 */
	public GhostRenderer(ClassicSpriteStore spriteStore) {
		this.sprites = spriteStore;
	}

	@Override
	public void render(Ghost ghost, Graphics g, Dimension dim) {
		Sprite sprite = sprites.getGhostSprite(ghost.getColour(),
				ghost.getDirection());
		sprite.draw(g, 0, 0, dim.width, dim.height);
	}

}
