package tiles;

public class SpiteSheet {
	public static int LENGTH = 16;

	public int width;
	public int height;
	public String filePath;
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

}
