package game;

import entity.*;
import engine.*;
import input.*;
import game.*;

import java.awt.event.KeyEvent;

public class Controller extends Component {
	public Controller(Entity e) {
		super(e);
	}

	@Override
	public void update() {
		InputHandler input = this.entity.game.input;
		if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
			this.entity.transform.x += 5;
		}
		if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
			this.entity.transform.x -= 5;
		}
		if (input.isKeyPressed(KeyEvent.VK_DOWN)) {
			this.entity.transform.y += 5;
		}
		if (input.isKeyPressed(KeyEvent.VK_UP)) {
			this.entity.transform.y -= 5;
		}
	}
}
