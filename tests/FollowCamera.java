package tests;

import engine.*;
import entity.*;
import tests.*;

public class FollowCamera extends Camera {
	Entity target;
	public FollowCamera(Game g, Entity target) {
		super(g);
		this.target = target;
	}

	public void update() {
		super.update();
		transform.x = target.transform.x - 224;
		transform.y = target.transform.y - 224;
	}
}
