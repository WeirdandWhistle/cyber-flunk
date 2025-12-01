package main;

import java.awt.Dimension;

import windowLib.ClientContent;
import windowLib.GUI;

public class Loader {

	public Loader() {

		Dimension dem = new Dimension(GUI.DEFUALT_SIZE.width * 2, GUI.DEFUALT_SIZE.height * 2);

		GUI frame = new GUI(dem);
		ClientContent cc = new ClientContent(GUI.DEFUALT_SIZE, 60);
		cc.zoom(dem);
		frame.add(cc);

		new SettingsManager(new Thread(cc), frame, cc);
	}
}
