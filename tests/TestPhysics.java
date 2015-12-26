package tests;

import engine.*;
import entity.*;
import input.*;
import tests.*;
import physics.*;

public class TestPhysics extends Game {
	public static void main(String[] args) {
		TestPhysics game = new TestPhysics(512, 512);
		game.run();
		System.exit(0);
	}

	public TestPhysics(int w, int h) {
		super(w, h);
	}

	@Override
	public void initialize() {
		super.initialize();
		Entity grid = new Entity(this);
		grid.transform.z = -1;
		new SpriteRenderer(grid, "grid.png");
		Entity character = new Entity(this);
		new SpriteRenderer(character, "dummy.png");
		new RigidBody(character, 64, 64);
		new CharacterController(character);

		Entity box = new Entity(this);
		box.transform.x = 256;
		box.transform.y = 256;
		new SpriteRenderer(box, "block.png");
		new RigidBody(box, 128, 128);
		box.body.kinematic = true;
	}
}
