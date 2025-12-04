package main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.IOException;

import keys.KeyManager;
import tiles.SpiteSheet;
import tiles.TileManager;
import windowLib.ClientContent;
import windowLib.GUI;

public class SettingsManager {

	public Dimension renderScale = GUI.DEFUALT_SIZE;
	public GUI frame;
	public ClientContent gameWindow;
	public Thread gameThread;
	public GameLoop gl;
	public TileManager tm;
	public KeyManager km;

	public SettingsManager(Thread gameThread, GUI frame, ClientContent gameWindow) {
		this.gameThread = gameThread;
		this.frame = frame;
		this.gameWindow = gameWindow;
		tm = new TileManager();
		km = new KeyManager();
		gl = new GameLoop(this);

		init();

		gameThread.start();
	}

	public void init() {
		try {
			SpiteSheet ss1 = new SpiteSheet("res\\sprite_sheets\\urban-beach.png");

			tm.loadTile("res\\ground\\grass.png", 0, false);
			tm.loadTile("res\\ground\\brick.png", 1, false);

			tm.loadTileFromSpriteSheet(ss1, 0, 0, true, 3);
			tm.loadTileFromSpriteSheet(ss1, 1, 0, false, 4);
			tm.loadTileFromSpriteSheet(ss1, 2, 0, false, 5);
			tm.loadTileFromSpriteSheet(ss1, 3, 0, true, 6);

			tm.loadTileFromSpriteSheet(ss1, 0, 1, true, 7);
			tm.loadTileFromSpriteSheet(ss1, 1, 1, false, 8);
			tm.loadTileFromSpriteSheet(ss1, 2, 1, false, 9);
			tm.loadTileFromSpriteSheet(ss1, 3, 1, false, 10);

			tm.loadMap("res\\maps\\map1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameWindow.setPainter(gl);
		gameWindow.setGameLogic(gl);

		frame.addKeyListener(km);

		km.addKeyPress((int keyCode) -> {
			if (keyCode == KeyEvent.VK_ESCAPE) {
				gameWindow.stop = true;
				try {
					System.out.println("Stopping...");
					Thread.sleep(5);

					// gameWindow = new ClientContent(frame.getPreferredSize(),
					// 1);

					// frame.setDecorated(false);
					// frame.add(gameWindow);

					// gameWindow.getGraphics().drawImage(
					// ImageIO.read(new File("res\\menu\\power-off.png")), 0, 0,
					// frame.getPreferredSize().width,
					// frame.getPreferredSize().height, null);
					//
					// Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				frame.dispose();
				System.exit(0);
			}
		});
	}

}
