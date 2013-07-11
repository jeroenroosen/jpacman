package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Renders subjects of a given type onto a {@link Graphics} context.
 * 
 * @author Jeroen Roosen
 * 
 * @param <T>
 *            The object type that is rendered by this renderer.
 */
public interface Renderer<T> {

	/**
	 * Renders the subject. The subject will be rendered on the provided
	 * graphics context, within the provided dimension, i.e. the render of the
	 * subject should clip to the dimension criteria.
	 * 
	 * @param subject
	 *            The subject to render.
	 * @param g
	 *            The graphics context to render on.
	 * @param dimension
	 *            The dimension the subject should be rendered in.
	 */
	void render(T subject, Graphics g, Dimension dimension);
}