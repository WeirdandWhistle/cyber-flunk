package windowLib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

public class ClientContent extends JPanel implements Runnable {

	public BufferedImage buffer;
	// private Graphics2D canvas;
	private Dimension dem;
	private Dimension drawSize;
	private Draw painter;
	private Draw customOverlow = null;
	private GameLogic gameLogic;
	public boolean stop;
	public boolean drawOverlay = true;
	int frames;
	long runtime = 0;
	private long startTime;
	private double deltaTime = 0.0;

	HashMap<String, Double> fpsData = new HashMap<>();

	public ClientContent(Dimension dem, int frames) {
		this.frames = frames;
		this.stop = false;
		this.dem = dem;
		this.drawSize = dem;
		this.requestFocusInWindow();
		this.setPreferredSize(this.drawSize);
		buffer = new BufferedImage(dem.width, dem.height, BufferedImage.TYPE_INT_ARGB);

		fpsData.put("DT-avg-10", 0.0);
		fpsData.put("DT-count-10", 1.0);
		fpsData.put("DT-total-10", 0.0);
	}

	public void zoom(Dimension newDrawSize) {
		drawSize = newDrawSize;
		this.setPreferredSize(drawSize);
	}

	public void drawToScreen() {

		Graphics g = this.getGraphics();

		Graphics2D g2d = buffer.createGraphics();
		drawOverlay(g2d);

		g.drawImage(buffer, 0, 0, drawSize.width, drawSize.height, null);
		runtime++;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, dem.width, dem.height);

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

		fpsData.put("DT-total-10", fpsData.get("DT-total-10") + getDeltaTime());
		fpsData.put("DT-count-10", fpsData.get("DT-count-10") + 1.0);

		if (fpsData.get("DT-count-10") == 10) {
			fpsData.put("DT-avg-10", fpsData.get("DT-total-10") / fpsData.get("DT-count-10"));

			fpsData.put("DT-count-10", 0.0);
			// fpsData.put("DT-total-10", getDeltaTime());
			fpsData.put("DT-total-10", 0.0);
			// System.out.println("--- reset ---");
		}

		// Graphics2D g2d = buffer.createGraphics();
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
		// System.out.println(fpsData.get("DT-avg-10"));
	}
	public double getFPSTotal() {
		return (double) runtime
				/ (double) ((double) (System.currentTimeMillis() - startTime) / 1000);
	}
	public double getFPSDeltaTime() {
		return 1.0 / getDeltaTime();
	}
	public void onceASecond() {
		// System.out.println("FPS: " + Math.round(getFPS()));
	}
	public void drawOverlay(Graphics2D g2d) {
		// Graphics g2d = this.getGraphics();
		if (customOverlow != null) {
			// customOverlow.paint(g2d);
			System.out.println("drawing custom overlay");
		} else {
			g2d.setColor(new Color(0, 0, 0, 100));
			g2d.fillRect(0, 0, 55, 10);
			g2d.setColor(new Color(0, 200, 0, 200));
			g2d.drawString(("FPS: " + (Math.round(1 / (fpsData.get("DT-avg-10"))))), 0, 10);
		}
		// g2d.dispose();
	}
	public double getDeltaTime() {
		return deltaTime;
	}

	@Override
	public void run() {
		new Thread(() -> {
			while (!stop) {
				try {
					Thread.sleep(1000);
					onceASecond();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();;

		double drawInterval = (1E9) / frames;
		double nextDrawTime = System.nanoTime() + drawInterval;

		long lastFrameMillis = System.currentTimeMillis();
		long lastFrameNano = System.nanoTime();

		startTime = System.currentTimeMillis();
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

			// deltaTime = (double) (System.currentTimeMillis() -
			// lastFrameMillis) / 1000;
			deltaTime = (double) (System.nanoTime() - lastFrameNano) / 1E9;
			lastFrameMillis = System.currentTimeMillis();
			lastFrameNano = System.nanoTime();
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

	public Draw getCustomOverlow() {
		return customOverlow;
	}

	public void setCustomOverlow(Draw customOverlow) {
		this.customOverlow = customOverlow;
	}

}
