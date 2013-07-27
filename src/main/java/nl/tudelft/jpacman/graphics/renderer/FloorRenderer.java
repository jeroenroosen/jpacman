package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.model.Character;
import nl.tudelft.jpacman.model.FloorSquare;
import nl.tudelft.jpacman.model.Pellet;

/**
 * Classic floor renderer that will render a black floor and all the classic
 * sprites on top of that.
 * 
 * @author Jeroen Roosen
 */
public class FloorRenderer implements Renderer<FloorSquare> {

	private final Renderers renderers;

	/**
	 * Creates a new floor square renderer.
	 * @param subRenderers The sub-renderers to render the contents of this square.
	 */
	public FloorRenderer(Renderers subRenderers) {
		this.renderers = subRenderers;
	}

	@Override
	public void render(FloorSquare square, Graphics g, Dimension dim) {
		assert square != null;
		assert g != null;
		assert dim != null;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim.width, dim.height);

		Pellet pellet = square.getPellet();
		if (pellet != null) {
			renderers.render(pellet, g, dim);
		}

		for (Character character : square.getOccupants()) {
			renderers.render(character, g, dim);
		}
	}

}
