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
	public <T> void render(T subject, Graphics g, Dimension dim) {
		assert subject != null;
		assert g != null;
		assert dim != null;

		Renderer<T> renderer = getRendererFor(subject);
		renderer.render(subject, g, dim);
	}

	@SuppressWarnings("unchecked")
	private <T> Renderer<T> getRendererFor(T subject) {
		Renderer<T> renderer = (Renderer<T>) renderers.get(subject.getClass());
		if (renderer == null) {
			throw new MissingRendererException(subject);
		}
		return renderer;
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

	/**
	 * Unchecked exception thrown when no renderer was registered for an object
	 * type.
	 * 
	 * @author Jeroen Roosen
	 */
	public static class MissingRendererException extends RuntimeException {

		private static final long serialVersionUID = 6699942784097151097L;

		/**
		 * Creates a new RuntimeException with a message detailing the lack of a
		 * renderer for the specified subject's class.
		 * 
		 * @param subject
		 *            The subject that didn't have a renderer for its type.
		 */
		public MissingRendererException(Object subject) {
			super("No render registered for type " + subject.getClass());
		}
	}
}
