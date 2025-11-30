package windowLib;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI extends JFrame {

	public static Dimension DEFUALT_SIZE = new Dimension(800, 450);

	public GUI() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Default Title");
		this.setResizable(false);
		this.setPreferredSize(DEFUALT_SIZE);
		this.setUndecorated(false);
		this.setVisible(true);
		this.pack();
	}
	public GUI(Dimension size) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Default Title");
		this.setResizable(false);
		this.setPreferredSize(size);
		this.setUndecorated(false);
		this.setVisible(true);
		this.pack();
	}
	public GUI(boolean pack) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Default Title");
		this.setResizable(false);
		this.setPreferredSize(DEFUALT_SIZE);

		this.setUndecorated(false);
		if (pack) {
			this.pack();
		}
	}
	public GUI setDecorated(boolean decorated) {
		GUI g = new GUI(false);

		g.setTitle(getTitle());
		g.setAlwaysOnTop(isAlwaysOnTop());
		g.setResizable(isResizable());
		g.setPreferredSize(getPreferredSize());
		g.setDefaultCloseOperation(getDefaultCloseOperation());
		g.setLocation(getLocation());

		g.setUndecorated(!decorated);
		g.setVisible(true);
		g.pack();
		this.dispose();
		return g;
	}

}
