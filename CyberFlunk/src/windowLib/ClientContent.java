package windowLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ClientContent extends JPanel implements Runnable {

	public BufferedImage buffer;
	// private Graphics2D canvas;
	private Dimension dem;
	private Dimension drawSize;
	private Draw painter;
	private GameLogic gameLogic;
	public boolean stop;
	int frames;
	long runtime = 0;

	public ClientContent(Dimension dem, int frames) {
		this.frames = frames;
		this.stop = false;
		this.dem = dem;
		this.drawSize = dem;
		this.requestFocusInWindow();
		this.setPreferredSize(this.drawSize);
		buffer = new BufferedImage(dem.width, dem.height, BufferedImage.TYPE_INT_ARGB);
	}

	public void zoom(Dimension newDrawSize) {
		drawSize = newDrawSize;
		this.setPreferredSize(drawSize);
	}

	public void drawToScreen() {

		Graphics g = this.getGraphics();

		g.drawImage(buffer, 0, 0, drawSize.width, drawSize.height, null);
		runtime++;
		Graphics2D g2d = buffer.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, dem.height, dem.height);

	}
	public void paint() {
		if (painter == null) {
			return;
		}
		painter.paint(buffer.createGraphics());
	}
	public void runGameLogic() {
		if (gameLogic == null) {
			return;
		}
		gameLogic.gameloop();
	}
	public void update() {

		Graphics2D g2d = buffer.createGraphics();
		/*
		 * int sineWave = (int) (Math.sin(Math.toRadians(runtime)) * 255);
		 * sineWave = Math.abs(sineWave);
		 * 
		 * int cosineWave = (int) (Math.cos(Math.toRadians(runtime)) * 255);
		 * cosineWave = Math.abs(cosineWave); g2d.setColor(new Color(cosineWave,
		 * 0, sineWave)); g2d.fillRect(0, 0, dem.width, dem.height);
		 */

		runGameLogic();
		paint();
		drawToScreen();
	}

	@Override
	public void run() {

		double drawInterval = (1E9) / frames;
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (!stop) {
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime /= 1E6;
			if (remainingTime < 0) {
				remainingTime = 0;
			}

			try {
				// System.out.println("sleeping for " + remainingTime);
				Thread.sleep((long) remainingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nextDrawTime += drawInterval;

			update();

		}
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	public Draw getPainter() {
		return painter;
	}

	public void setPainter(Draw aPIDraw) {
		painter = aPIDraw;
	}

}
