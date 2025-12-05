package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import tiles.SpiteSheet;
import tiles.Tile;
import tiles.TileManager;

public class Entity {

	public static Rectangle defaultHitbox = new Rectangle(Tile.LENGTH / 4, Tile.LENGTH / 2,
			Tile.LENGTH / 2, Tile.LENGTH / 2);

	public BufferedImage texture;
	public SpiteSheet spiteSheet;
	public Rectangle boudingBox;
	public Rectangle hitbox;
	public String direction;
	public String lastDirection;

	public Entity(Rectangle boundingBox) {
		this.boudingBox = boundingBox;
		this.hitbox = defaultHitbox;
	}

	public Point getCenter() {
		return new Point(boudingBox.x + (boudingBox.width / 2),
				boudingBox.y + (boudingBox.height / 2));
	}

	public boolean collision(TileManager tm, Point origin) {

		int minXindex = (int) Math.floor((double) origin.x / Tile.LENGTH);
		int minYindex = (int) Math.floor((double) origin.y / Tile.LENGTH);

		int maxXindex = (int) Math.ceilDiv(origin.x, Tile.LENGTH);
		int maxYindex = (int) Math.ceilDiv(origin.y, Tile.LENGTH);

		if (minXindex < 0) {
			return true;
		}
		if (minYindex < 0) {
			return true;
		}
		if (maxXindex < 0) {
			return true;
		}
		if (maxYindex < 0) {
			return true;
		}

		Rectangle playerHitbox = new Rectangle(origin.x + hitbox.x, origin.y + hitbox.y,
				hitbox.width, hitbox.height);

		for (int column = minYindex; column < maxYindex + 1; column++) {
			for (int row = minXindex; row < maxXindex + 1; row++) {
				if (tm.tileMap.get(tm.map.get(column).get(row).intValue()).collision) {
					Rectangle hit = new Rectangle(row * Tile.LENGTH, column * Tile.LENGTH,
							Tile.LENGTH, Tile.LENGTH);
					if (hit.intersects(playerHitbox)) {
						return true;
					}
				}
			}
		}

		return false;
	}
	public boolean collisionDebug(TileManager tm, Point origin, Graphics2D g2d) {

		int minXindex = (int) Math.floor((double) origin.x / Tile.LENGTH);
		int minYindex = (int) Math.floor((double) origin.y / Tile.LENGTH);

		int maxXindex = (int) Math.ceilDiv(origin.x, Tile.LENGTH);
		int maxYindex = (int) Math.ceilDiv(origin.y, Tile.LENGTH);

		g2d.setColor(Color.green);
		g2d.fillRect(minXindex * Tile.LENGTH, minYindex * Tile.LENGTH, 5, 5);

		g2d.fillRect(maxXindex * Tile.LENGTH + Tile.LENGTH - 5,
				maxYindex * Tile.LENGTH + Tile.LENGTH - 5, 5, 5);

		if (minXindex < 0) {
			return true;
		}
		if (minYindex < 0) {
			return true;
		}
		if (maxXindex < 0) {
			return true;
		}
		if (maxYindex < 0) {
			return true;
		}

		Rectangle playerHitbox = new Rectangle(origin.x + hitbox.x, origin.y + hitbox.y,
				hitbox.width, hitbox.height);

		for (int column = minYindex; column < maxYindex + 1; column++) {
			for (int row = minXindex; row < maxXindex + 1; row++) {
				if (tm.tileMap.get(tm.map.get(column).get(row).intValue()).collision) {
					Rectangle hit = new Rectangle(row * Tile.LENGTH, column * Tile.LENGTH,
							Tile.LENGTH, Tile.LENGTH);
					g2d.setColor(Color.pink);
					g2d.drawRect(hit.x, hit.y, Tile.LENGTH, Tile.LENGTH);
					if (hit.intersects(playerHitbox)) {
						g2d.setColor(Color.YELLOW);
						g2d.drawRect(hit.x, hit.y, Tile.LENGTH, Tile.LENGTH);
						System.out.println("BANG!");
						return true;
					}
				}
			}
		}

		return false;
	}
	public Rectangle calculateAbsoluteHitbox() {
		return new Rectangle(boudingBox.x + hitbox.x, boudingBox.y + hitbox.y, hitbox.width,
				hitbox.height);
	}
	public void drawHitbox(Graphics2D g2d) {
		Rectangle draw = calculateAbsoluteHitbox();
		g2d.setColor(Color.red);
		g2d.drawRect(draw.x, draw.y, draw.width, draw.height);
	}

}
