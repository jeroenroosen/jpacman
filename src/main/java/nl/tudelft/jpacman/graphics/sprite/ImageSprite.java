package nl.tudelft.jpacman.graphics.sprite;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * Simplest implementation of a Sprite, it merely consists of a static image.
 * 
 * @author Jeroen Roosen
 */
public class ImageSprite implements Sprite {

	private final Image image;

	/**
	 * Creates a new sprite from an image.
	 * 
	 * @param image
	 *            The image to create a sprite from.
	 */
	public ImageSprite(Image image) {
		this.image = image;
	}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {
		g.drawImage(image, x, y, x + width, y + height, 0, 0,
				image.getWidth(null), image.getHeight(null), null);
	}

	@Override
	public Sprite split(int x, int y, int width, int height) {
		if (image.getWidth(null) < x || image.getHeight(null) < y) {
			return new EmptySprite();
		}

		BufferedImage newImage = newImage(width, height);
		newImage.createGraphics().drawImage(image, 0, 0, width - 1, height - 1,
				x, y, x + width - 1, y + height - 1, null);

		return new ImageSprite(newImage);
	}

	private BufferedImage newImage(int width, int height) {
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		return gc.createCompatibleImage(width, height, Transparency.BITMASK);
	}

	@Override
	public int getWidth() {
		return image.getWidth(null);
	}

	@Override
	public int getHeight() {
		return image.getHeight(null);
	}

}
