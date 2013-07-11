package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import nl.tudelft.jpacman.model.WallSquare;

/**
 * Renders wall squares.
 * 
 * @author Jeroen Roosen
 */
public class WallRenderer implements Renderer<WallSquare> {
	
	@Override
	public void render(WallSquare subject, Graphics g, Dimension dim) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, dim.width, dim.height);
	}
}
