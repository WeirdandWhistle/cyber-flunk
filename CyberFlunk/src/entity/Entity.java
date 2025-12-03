package entity;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public BufferedImage texture;
	public Rectangle boudingBox;
	public Rectangle hitbox;

	public Entity(Rectangle boundingBox) {
		this.boudingBox = boundingBox;
		this.hitbox = boundingBox;
	}

	public Point getCenter() {
		return new Point(boudingBox.x + (boudingBox.width / 2),
				boudingBox.y + (boudingBox.height / 2));
	}

}
