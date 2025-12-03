package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TileManager {

	// public ArrayList<Tile> tiles;
	public HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
	public ArrayList<ArrayList<Integer>> map = new ArrayList<>();

	public TileManager() {
		// tiles = new ArrayList<>();
	}

	public void loadTileFromSpriteSheet(SpiteSheet ss, int x, int y, boolean collision, int index)
			throws IOException {
		BufferedImage fullSheet = ImageIO.read(new File(ss.filePath));
		// System.out.println("x+windth " + (x * ss.width + SpiteSheet.LENG));
		BufferedImage texture = fullSheet.getSubimage(x * ss.width, y * ss.height,
				SpiteSheet.LENGTH, SpiteSheet.LENGTH);

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

	public void loadMap(String mapPath) {
		File file = new File(mapPath);
		try (BufferedReader read = new BufferedReader(new FileReader(file))) {
			String line = read.readLine();
			int column = 0;
			while (line != null) {
				String broken[] = line.split(" ");

				map.add(new ArrayList<Integer>());
				for (int i = 0; i < broken.length; i++) {
					// System.out.print(String.valueOf(broken[i]) + " ");
					map.get(column).add(Integer.valueOf(broken[i]));
				}
				// System.out.println("");

				column++;
				line = read.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d, int screenX, int screenY) {
		// System.out.println("--- start ---");
		for (int column = 0; column < map.size(); column++) {
			ArrayList<Integer> col = map.get(column);
			for (int row = 0; row < col.size(); row++) {
				int index = col.get(row);
				// System.out.print(index + " ");
				g2d.drawImage(tileMap.get(index).texture, row * Tile.LENGTH - screenX,
						column * Tile.LENGTH - screenY, Tile.LENGTH, Tile.LENGTH, null);
			}
			// System.out.println("");

		}
	}

}
