package nl.tudelft.jpacman.graphics.sprite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nl.tudelft.jpacman.model.Direction;
import nl.tudelft.jpacman.model.GhostColour;

/**
 * Sprite Store containing the classic Pac-Man sprites.
 * 
 * @author Jeroen Roosen
 */
public class ClassicSpriteStore extends SpriteStore {

	/**
	 * The sprite files are vertically stacked series for each direction, this
	 * array denotes the order.
	 */
	private static final Direction[] DIRECTIONS = { Direction.NORTH,
			Direction.EAST, Direction.SOUTH, Direction.WEST };

	/**
	 * The image size in pixels.
	 */
	private static final int SPRITE_SIZE = 16;

	/**
	 * The amount of frames in the pacman animation.
	 */
	private static final int PACMAN_ANIMATION_FRAMES = 4;

	/**
	 * The amount of frames in the ghost animation.
	 */
	private static final int GHOST_ANIMATION_FRAMES = 2;

	/**
	 * The delay between frames.
	 */
	private static final int ANIMATION_DELAY = 250;

	/**
	 * A cache so the sprites don't have to be read from file for every request.
	 */
	private final Map<String, Sprite> cache;

	/**
	 * Creates a new sprite store with an empty cache.
	 */
	public ClassicSpriteStore() {
		this.cache = new HashMap<>();
	}

	/**
	 * The Sprite for Pac-Man, for the direction he is facing.
	 * 
	 * @param direction
	 *            The direction Pac-Man is facing.
	 * @return The Sprite for Pac-Man.
	 */
	public Sprite getPacmanSprite(Direction direction) {
		assert direction != null;
		return cachedDirectionSprite(direction, "/sprite/pacman.png",
				PACMAN_ANIMATION_FRAMES);
	}

	/**
	 * Returns a cached or new animated sprite for a given direction.
	 * 
	 * @param direction
	 *            The direction of the sprite.
	 * @param resource
	 *            The resource name of the sprite.
	 * @param frames
	 *            The number of frames in this sprite.
	 * @return The animated sprite facing the given direction.
	 */
	private Sprite cachedDirectionSprite(Direction direction, String resource,
			int frames) {
		String dsn = directionSpriteName(resource, direction);
		Sprite sprite = cache.get(dsn);

		if (sprite == null) {
			Sprite baseImage = loadSprite(resource);
			for (int i = 0; i < DIRECTIONS.length; i++) {
				Sprite directionSprite = baseImage.split(0, i * SPRITE_SIZE,
						frames * SPRITE_SIZE, SPRITE_SIZE);
				AnimatedSprite animation = createAnimatedSprite(
						directionSprite, frames, ANIMATION_DELAY, true);
				animation.setAnimating(true);
				cache.put(directionSpriteName(resource, DIRECTIONS[i]),
						animation);
			}
			sprite = cache.get(dsn);
		}

		assert sprite != null;
		return sprite;
	}

	/**
	 * Creates a key for a resource facing a direction.
	 * @param resource The resource name.
	 * @param direction The direction.
	 * @return The key for the resource-direction combination.
	 */
	private String directionSpriteName(String resource, Direction direction) {
		return resource + direction.name();
	}

	/**
	 * Returns the sprite for the ghost of the specified colour, for the
	 * direction the ghost is facing.
	 * 
	 * @param type
	 *            The colour of the ghost.
	 * @param direction
	 *            The direction the ghost is facing.
	 * @return The Sprite for the ghost.
	 */
	public Sprite getGhostSprite(GhostColour type, Direction direction) {
		assert type != null;
		assert direction != null;

		String resource = "/sprite/ghost_" + type.name().toLowerCase() + ".png";
		return cachedDirectionSprite(direction, resource,
				GHOST_ANIMATION_FRAMES);
	}

	/**
	 * Returns the sprite for the floor square.
	 * 
	 * @return the sprite for the floor.
	 */
	public Sprite getFloorSprite() {
		return cachedImageSprite("/sprite/floor.png");
	}

	/**
	 * @return The sprite for the wall.
	 */
	public Sprite getWallSprite() {
		return cachedImageSprite("/sprite/wall.png");
	}

	/**
	 * @return The sprite for the
	 */
	public Sprite getPelletSprite() {
		return cachedImageSprite("/sprite/pellet.png");
	}

	/**
	 * Returns a new or cached sprite from a resource name.
	 * @param resource The resource name.
	 * @return The new or cached sprite.
	 */
	private Sprite cachedImageSprite(String resource) {
		Sprite s = cache.get(resource);
		if (s == null) {
			s = loadSprite(resource);
			cache.put(resource, s);
		}
		return s;
	}

	/**
	 * Overloads the default sprite loading, negating the exception. This class
	 * assumes all sprites are provided, hence the exception will be thrown as a
	 * {@link RuntimeException}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Sprite loadSprite(String resource) {
		try {
			return super.loadSprite(resource);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load sprite: " + resource, e);
		}
	}
}
