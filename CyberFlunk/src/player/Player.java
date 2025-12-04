package player;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.SettingsManager;
import tiles.Tile;

public class Player extends Entity {

	public String folder;
	public static final int walkSpeed = Tile.LENGTH * 4;

	public Player(String folder) {
		super(new Rectangle(5 * Tile.LENGTH, 4 * Tile.LENGTH, Tile.LENGTH, Tile.LENGTH));
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
	public void move(SettingsManager sm, double deltaTime) {
		double movVector[] = {0.0, 0.0};
		int moveSpeed = walkSpeed;
		if (sm.km.poll("shift")) {
			moveSpeed *= 2;
		}

		if (sm.km.poll("w")) {
			movVector[1] -= 1;
		} else if (sm.km.poll("s")) {
			movVector[1] += 1;
		}
		if (sm.km.poll("d")) {
			movVector[0] += 1;
		} else if (sm.km.poll("a")) {
			movVector[0] -= 1;
		}

		// normilize
		if (movVector[0] == 0.0 && movVector[1] == 0.0) {
			// return;
		}
		// c^2 = a^2 + b^2 | r/radius = c
		double r = Math.sqrt(Math.pow(movVector[0], 2) + Math.pow(movVector[1], 2));
		double unitVector[] = {movVector[0] / r, movVector[1] / r};

		double normMoveVec[] = {unitVector[0] * moveSpeed, unitVector[1] * moveSpeed};

		// System.out.println("DT " + deltaTime + " " + normMoveVec[0] *
		// deltaTime);

		int moveX = (int) Math.round(normMoveVec[0] * deltaTime);
		int moveY = (int) Math.round(normMoveVec[1] * deltaTime);

		if (!collision(sm.tm, new Point(boudingBox.x + moveX, boudingBox.y + moveY))) {
			boudingBox.x += moveX;
			boudingBox.y += moveY;
		}

	}

	public void draw(Graphics2D g2d, int screenX, int screenY) {
		g2d.drawImage(texture, boudingBox.x - screenX, boudingBox.y - screenY, boudingBox.width,
				boudingBox.height, null);

		// g2d.setColor(Color.green);
		// g2d.drawRect(boudingBox.x, boudingBox.y, boudingBox.height,
		// boudingBox.width);

		// drawHitbox(g2d);
	}

}
