package keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyManager implements KeyListener {

	private Map<String, Boolean> key = new HashMap<>();
	private ArrayList<KeyPress> actions = new ArrayList<>();

	public KeyManager() {

		key.put("a", false);
		key.put("b", false);
		key.put("c", false);
		key.put("d", false);
		key.put("e", false);
		key.put("f", false);
		key.put("g", false);
		key.put("h", false);
		key.put("i", false);
		key.put("j", false);
		key.put("k", false);
		key.put("l", false);
		key.put("m", false);
		key.put("n", false);
		key.put("o", false);
		key.put("p", false);
		key.put("q", false);
		key.put("r", false);
		key.put("s", false);
		key.put("t", false);
		key.put("u", false);
		key.put("v", false);
		key.put("w", false);
		key.put("r", false);
		key.put("x", false);
		key.put("y", false);
		key.put("z", false);

		key.put("0", false);
		key.put("1", false);
		key.put("2", false);
		key.put("3", false);
		key.put("4", false);
		key.put("5", false);
		key.put("6", false);
		key.put("7", false);
		key.put("8", false);
		key.put("9", false);

		key.put("-", false);
		key.put("=", false);
		key.put("[", false);
		key.put("]", false);
		key.put("\\", false);
		key.put("'", false);
		key.put(";", false);
		key.put(",", false);
		key.put(".", false);
		key.put("/", false);
		key.put("`", false);

		key.put("F1", false);
		key.put("F2", false);
		key.put("F3", false);
		key.put("F4", false);
		key.put("F5", false);
		key.put("F6", false);
		key.put("F7", false);
		key.put("F8", false);
		key.put("F9", false);
		key.put("F10", false);
		key.put("F11", false);
		key.put("F12", false);

		key.put("esc", false);
		key.put("tab", false);
		key.put("caps", false);
		key.put("shift", false);
		key.put("ctrl", false);
		key.put("alt", false);
		key.put("backspace", false);
		key.put("enter", false);

		key.put("space", false);
		key.put(" ", false);

	}

	public boolean poll(String key) {
		return this.key.get(key.toLowerCase());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		allTheLogic(e, true);

		for (KeyPress action : actions) {
			action.Action(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		allTheLogic(e, false);

	}
	public void addKeyPress(KeyPress action) {
		actions.add(action);
	}
	public void allTheLogic(KeyEvent e, Boolean set) {
		switch (e.getKeyCode()) {
			case 8 :
				key.put("backspace", set);
				break;
			case 9 :
				key.put("tab", set);
				break;
			case 10 :
				key.put("enter", set);
				break;
			case 16 :
				key.put("shift", set);
				break;
			case 17 :
				key.put("ctrl", set);
				break;
			case 18 :
				key.put("alt", set);
				break;
			case 20 :
				key.put("caps", set);
				break;
			case 27 :
				key.put("esc", set);
				break;
			case 32 :
				key.put(" ", set);
				key.put("space", set);
				break;
		}

		char c = Character.toLowerCase(e.getKeyChar());

		if (c != KeyEvent.CHAR_UNDEFINED) {
			if (Character.isLetterOrDigit(c)) {
				key.put(String.valueOf(c), set);
			}
		}
		switch (c) {
			case '`' :
				key.put("`", set);
				break;
			case '-' :
				key.put("-", set);
				break;
			case '=' :
				key.put("=", set);
				break;
			case '[' :
				key.put("[", set);
				break;
			case ']' :
				key.put("]", set);
				break;
			case '\\' :
				key.put("\\", set);
				break;
			case ';' :
				key.put(";", set);
				break;
			case '\'' :
				key.put("\'", set);
				break;
			case '/' :
				key.put("/", set);
				break;
			case '.' :
				key.put(".", set);
				break;
			case ',' :
				key.put(",", set);
				break;
			// upper case versions
			case '~' :
				key.put("`", set);
				break;
			case '_' :
				key.put("-", set);
				break;
			case '+' :
				key.put("=", set);
				break;
			case '{' :
				key.put("[", set);
				break;
			case '}' :
				key.put("]", set);
				break;
			case '|' :
				key.put("\\", set);
				break;
			case ':' :
				key.put(";", set);
				break;
			case '"' :
				key.put("\'", set);
				break;
			case '?' :
				key.put("/", set);
				break;
			case '>' :
				key.put(".", set);
				break;
			case '<' :
				key.put(",", set);
				break;

			case '!' :
				key.put("1", set);
				break;
			case '@' :
				key.put("2", set);
				break;
			case '#' :
				key.put("3", set);
				break;
			case '$' :
				key.put("4", set);
				break;
			case '%' :
				key.put("5", set);
				break;
			case '^' :
				key.put("6", set);
				break;
			case '&' :
				key.put("7", set);
				break;
			case '*' :
				key.put("8", set);
				break;
			case '(' :
				key.put("9", set);
				break;
			case ')' :
				key.put("0", set);
				break;

		}
	}

}
