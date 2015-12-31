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
		//Entity grid = new Entity(this);
		//grid.transform.z = -1;
		//new SpriteRenderer(grid, "grid.png");
		Entity character = new Entity(this);
		new SpriteRenderer(character, "dummy.png");
		RigidBody test = new RigidBody(character);
		new CharacterController(character);
		new BoxCollider(character, 64, 64);

		Entity box = new Entity(this);
		box.transform.x = 256;
		box.transform.y = 256;
		new SpriteRenderer(box, "block.png");
		new RigidBody(box);
		new BoxCollider(box, 128, 128);
		box.body.kinematic = true;

		Entity box2 = new Entity(this);
		box2.transform.x = 0;
		box2.transform.y = 128;
		new SpriteRenderer(box2, "block.png");
		new RigidBody(box2);
		new BoxCollider(box2, 128, 128);
		box2.body.kinematic = true;

		Entity box3 = new Entity(this);
		box3.transform.x = 128;
		box3.transform.y = 128;
		new SpriteRenderer(box3, "block.png");
		new RigidBody(box3);
		new BoxCollider(box3, 128, 128);
		box3.body.kinematic = true;

		camera = new FollowCamera(this, character);
	}
}
