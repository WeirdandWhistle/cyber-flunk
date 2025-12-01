package tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TileManager {

	// public ArrayList<Tile> tiles;
	public HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();

	public TileManager() {
		// tiles = new ArrayList<>();
	}

	public void loadTileFromSpriteSheet(SpiteSheet ss, int x, int y, boolean collision, int index)
			throws IOException {
		BufferedImage fullSheet = ImageIO.read(new File(ss.filePath));
		BufferedImage texture = fullSheet.getSubimage(x * ss.width, y * ss.height, Tile.LENGTH,
				Tile.LENGTH);

		Tile tile = new Tile();
		tile.collision = collision;
		tile.index = index;
		tile.texture = texture;

		tileMap.put(index, tile);
	}

	public void loadTile(String pathName, int index, boolean collision) throws IOException {
		File file = new File(pathName);
		BufferedImage img = ImageIO.read(file);

		Tile tile = new Tile();
		tile.collision = collision;
		tile.index = index;
		tile.texture = img;

		tileMap.put(index, tile);

	}

}
