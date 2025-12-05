package tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpiteSheet {
	public static int LENGTH = 16;

	public int width;
	public int height;
	public String filePath;
	public BufferedImage img;
	public HashMap<String, BufferedImage> sprites;
	public SpiteSheet(String filePath, int width, int height) {
		this.width = width;
		this.height = height;
		this.filePath = filePath;
	}
	public SpiteSheet(String filePath) {
		this.width = LENGTH;
		this.height = LENGTH;
		this.filePath = filePath;
	}
	public SpiteSheet loadIMG() throws IOException {
		this.img = ImageIO.read(new File(filePath));
		return this;
	}
	public SpiteSheet loadSpirtes() {
		if (this.img == null) {
			throw new IllegalCallerException(
					"The IMG for this sprite sheet is not loaded! Can't break down null IMG!");
		}
		this.sprites = new HashMap<>();

		int rows = img.getWidth() / width;
		int columns = img.getHeight() / height;

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				sprites.put(String.valueOf(j) + "-" + String.valueOf(i),
						img.getSubimage(j * width, i * height, width, height));
			}
		}
		return this;
	}
	public BufferedImage getSprite(int x, int y) {
		System.out.println(y * height + height);
		return img.getSubimage(x * width, y * height, width, height);
	}
}
