package player;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import keys.KeyManager;
import tiles.Tile;

public class Player extends Entity {

	public String folder;
	public static final int walkSpeed = Tile.LENGTH * 4;

	public Player(String folder) {
		super(new Rectangle(0, 0, Tile.LENGTH, Tile.LENGTH));
		this.folder = folder;
		try {
			texture = ImageIO.read(new File(folder + "\\main.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Player(String folder, Rectangle boundingBox) {
		super(boundingBox);
		this.folder = folder;

		try {
			texture = ImageIO.read(new File(folder + "\\main.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void move(KeyManager km, double deltaTime) {
		double movVector[] = {0.0, 0.0};
		int moveSpeed = walkSpeed;
		if (km.poll("shift")) {
			moveSpeed *= 2;
		}

		if (km.poll("w")) {
			movVector[1] -= 1;
		} else if (km.poll("s")) {
			movVector[1] += 1;
		}
		if (km.poll("d")) {
			movVector[0] += 1;
		} else if (km.poll("a")) {
			movVector[0] -= 1;
		}

		// normilize
		if (movVector[0] == 0.0 && movVector[1] == 0.0) {
			return;
		}
		// c^2 = a^2 + b^2 | r/radius = c
		double r = Math.sqrt(Math.pow(movVector[0], 2) + Math.pow(movVector[1], 2));
		double unitVector[] = {movVector[0] / r, movVector[1] / r};

		double normMoveVec[] = {unitVector[0] * moveSpeed, unitVector[1] * moveSpeed};

		// System.out.println("DT " + deltaTime + " " + normMoveVec[0] *
		// deltaTime);

		boudingBox.x += (int) Math.round(normMoveVec[0] * deltaTime);
		boudingBox.y += (int) Math.round(normMoveVec[1] * deltaTime);
	}

	public void draw(Graphics2D g2d, int screenX, int screenY) {
		g2d.drawImage(texture, boudingBox.x - screenX, boudingBox.y - screenY, boudingBox.width,
				boudingBox.height, null);
	}

}
