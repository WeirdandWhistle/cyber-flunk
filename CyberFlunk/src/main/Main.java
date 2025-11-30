package main;

import java.awt.Dimension;

import windowLib.ClientContent;
import windowLib.GUI;

public class Main {

	public static void main(String[] args) {

		Dimension dem = new Dimension(GUI.DEFUALT_SIZE.width * 2, GUI.DEFUALT_SIZE.height * 2);

		GUI window = new GUI(dem);
		ClientContent cc = new ClientContent(GUI.DEFUALT_SIZE, 60);
		cc.zoom(dem);
		window.add(cc);
		new Thread(cc).start();
	}

}
