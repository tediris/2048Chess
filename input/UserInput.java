package input;

import input.*;
import entity.*;
import engine.*;

public class UserInput extends Entity {
	private String buffer;
	private InputHandler input;
	public UserInput(Game g) {
		super(g);
		buffer = "";
		this.input = g.input;
	}

	@Override
	public void update() {
		for (int i = 0; i < 256; i++) {
			if (input.isKeyDown(i)) {
				buffer += (char) i;
			}
		}
	}

	public String get() {
		return buffer;
	}

	public void reset() {
		buffer = "";
	}
}
