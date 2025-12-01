package main;

import java.awt.Dimension;

import windowLib.ClientContent;
import windowLib.GUI;

public class SettingsManager {

	public Dimension renderScale = GUI.DEFUALT_SIZE;
	public GUI frame;
	public ClientContent gameWindow;
	public Thread gameThread;
	private GameLoop gl;

	public SettingsManager(Thread gameThread, GUI frame, ClientContent gameWindow) {
		this.gameThread = gameThread;
		this.frame = frame;
		this.gameWindow = gameWindow;

		gl = new GameLoop();

		gameWindow.setPainter(gl);
		gameWindow.setGameLogic(gl);

		gameThread.start();
	}

}
