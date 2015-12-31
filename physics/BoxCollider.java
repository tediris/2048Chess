package physics;

import entity.*;
import engine.*;
import physics.*;

public class BoxCollider extends Collider {
	public float width, height;
	public Vector2 half;
	public BoxCollider(Entity e, float width, float height) {
		super(e);
		this.width = width;
		this.height = height;
		half = new Vector2(width/2, height/2);
	}

	public Vector2 mid() {
		return new Vector2(entity.body.position.x + width / 2,
			entity.body.position.y + height / 2);
	}
}
