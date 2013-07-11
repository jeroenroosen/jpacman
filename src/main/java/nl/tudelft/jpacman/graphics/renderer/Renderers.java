package nl.tudelft.jpacman.graphics.renderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;


/**
 * A proxy for multiple {@link Renderer}s. The proxy will be able to render any
 * object as long as it has a registered Renderer for its class type.
 * 
 * @author Jeroen Roosen
 */
public class Renderers {

	private final Map<Class<?>, Renderer<?>> renderers = new HashMap<>();

	/**
	 * Renders the subject using the renderer associated with the class type of
	 * the subject.
	 * 
	 * @param subject
	 *            The subject to render.
	 * @param g
	 *            The graphics context to render on.
	 * @param dimension
	 *            The dimension the subject should be rendered in.
	 * 
	 * @see Renderer#render(Object, Graphics, Dimension)
	 */
	@SuppressWarnings("unchecked")
	public <T> void render(T subject, Graphics g, Dimension dim) {
		assert subject != null;
		assert g != null;
		assert dim != null;

		Class<T> clazz = (Class<T>) subject.getClass();
		Renderer<T> renderer = getRenderer(clazz);
		if (renderer != null) {
			renderer.render(subject, g, dim);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> Renderer<T> getRenderer(Class<T> type) {
		return (Renderer<T>) renderers.get(type);
	}

	/**
	 * Registers a renderer at this proxy.
	 * 
	 * @param type
	 *            The class of objects this renderer is for.
	 * @param renderer
	 *            The renderer for the type.
	 */
	public <T> void registerRenderer(Class<T> type, Renderer<T> renderer) {
		renderers.put(type, renderer);
	}

}
