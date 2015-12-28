package tests;

import entity.*;
import engine.*;
import input.*;
import tests.*;
import physics.*;

import java.awt.event.KeyEvent;

public class CharacterController extends Component {

	RigidBody body;
	InputHandler input;

	public CharacterController(Entity e) {
		super(e);
		body = entity.body;
		input = entity.game.input;
	}

	@Override
	public void update() {
		boolean key = false;
		if (input.isKeyPressed(KeyEvent.VK_RIGHT)) {
			key = true;
			body.velocity.x = 5;
		} else if (input.isKeyPressed(KeyEvent.VK_LEFT)) {
			key = true;
			body.velocity.x = -5;
		} else {
			body.velocity.set(0, body.velocity.y);
		}
	}
}
