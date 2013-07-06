package nl.tudelft.jpacman.graphics.sprite;

import java.awt.Graphics;

public class AnimatedSprite implements Sprite {
	
	private static final Sprite END_OF_LOOP = new EmptySprite();

	private final Sprite[] frames;
	private final int delay;
	private final boolean loop;
	
	private int current;
	private boolean animating;
	private long lastUpdate;

	/**
	 * Creates a new animating sprite that will change frames every interval. By
	 * default the sprite is not animating.
	 * 
	 * @param frames
	 *            The frames of this animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether or not this sprite should be looping.
	 */
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop) {
		this(frames, delay, loop, false);
	}

	/**
	 * Creates a new animating sprite that will change frames every interval.
	 * 
	 * @param frames
	 *            The frames of this animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether or not this sprite should be looping.
	 * @param animating
	 *            Whether or not this sprite is animating from the start.
	 */
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop,
			boolean animating) {
		assert frames.length > 0;
		
		this.frames = frames;
		this.delay = delay;
		this.loop = loop;
		this.animating = animating;
		
		this.current = 0;
		this.lastUpdate = System.currentTimeMillis();
	}

	private Sprite currentSprite() {
		if (current < frames.length) {
			return frames[current];
		}
		return END_OF_LOOP;
	}
	
	/**
	 * Starts or stops the animation of this sprite.
	 * 
	 * @param animating
	 *            <code>true</code> to animate this sprite or <code>false</code>
	 *            to stop animating this sprite.
	 */
	public void setAnimating(boolean animating) {
		this.animating = animating;
	}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {
		update();
		currentSprite().draw(g, x, y, width, height);
	}

	@Override
	public Sprite split(int x, int y, int width, int height) {
		update();
		return currentSprite().split(x, y, width, height);
	}

	private void update() {
		long now = System.currentTimeMillis();
		if (animating) {
			while (lastUpdate < now) {
				lastUpdate += delay;
				current++;
				if (loop) {
					current %= frames.length;
				}
			}
		} else {
			lastUpdate = now;
		}
	}

	@Override
	public int getWidth() {
		return currentSprite().getWidth();
	}

	@Override
	public int getHeight() {
		return currentSprite().getHeight();
	}

}
