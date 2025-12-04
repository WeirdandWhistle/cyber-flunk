package main;

import java.awt.Graphics2D;

import player.Player;
import tiles.Tile;
import windowLib.Draw;
import windowLib.GameLogic;

public class GameLoop implements Draw, GameLogic {

	public SettingsManager sm;

	public int screenX = 0;
	public int screenY = 0;

	public Player player;

	public GameLoop(SettingsManager sm) {
		this.sm = sm;
		player = new Player("res\\player");
	}

	@Override
	public void gameloop() {

		player.move(sm, sm.gameWindow.getDeltaTime());

		screenX = (int) (player.getCenter().getX() - sm.gameWindow.buffer.getWidth() / 2);
		screenY = (int) (player.getCenter().getY() - sm.gameWindow.buffer.getHeight() / 2);

		if (screenX < 0) {
			screenX = 0;
		}
		if (screenY < 0) {
			screenY = 0;
		}
		if (screenY > sm.tm.map.size() * Tile.LENGTH - sm.gameWindow.buffer.getHeight()) {
			screenY = sm.tm.map.size() * Tile.LENGTH - sm.gameWindow.buffer.getHeight();
		}

	}

	@Override
	public void paint(Graphics2D g2d) {

		sm.tm.draw(g2d, screenX, screenY);
		player.draw(g2d, screenX, screenY);

	}

}
