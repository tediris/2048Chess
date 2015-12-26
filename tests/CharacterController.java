package tests;

import entity.*;
import engine.*;
import input.*;
import tests.*;
import physics.*;

import java.awt.event.KeyEvent;

public class CharacterController extends Component {
	public CharacterController(Entity e) {
		super(e);
	}

	@Override
	public void update() {
		InputHandler input = entity.game.input;
		boolean key = false;
		if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
			key = true;
			entity.body.velocity.x = 5;
		}
		if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
			key = true;
			entity.body.velocity.x = -5;
		}
		if (input.isKeyPressed(KeyEvent.VK_DOWN)) {
			key = true;
			entity.body.velocity.y = 5;
		}
		if (input.isKeyPressed(KeyEvent.VK_UP)) {
			key = true;
			entity.body.velocity.y = -5;
		}
		if (!key) {
			entity.body.velocity.set(0, 0);
		}

	}
}
