package physics;

import physics.*;

import java.lang.*;

public class BoundingBox {
	public Vector2 min, max, mid;
	public RigidBody body;
	float radius;
	public BoundingBox(Vector2 min, Vector2 max, RigidBody body) {
		this.min = min;
		this.max = max;
		this.mid = new Vector2((min.x + max.x) / 2, (min.y + max.y) / 2);
		radius = Vector2.distance(min, max) / 2;
		this.body = body;
	}

	private static boolean proximity(BoundingBox a, BoundingBox b) {
		float distance = Vector2.distance(
			Vector2.add(a.mid, a.body.position),
			Vector2.add(b.mid, b.body.position));
		return distance < (a.radius + b.radius);
	}

	public static boolean Collision(BoundingBox a, BoundingBox b) {
		// add this in to boost performance
		// if (!proximity(a, b)) return false;

		// Exit with no intersection if found separated along an axis
		if (a.max.x + a.body.position.x < b.min.x + b.body.position.x ||
			a.min.x + a.body.position.x > b.max.x + b.body.position.x)
			return false;
		if (a.max.y + a.body.position.y < b.min.y + b.body.position.y ||
			a.min.y + a.body.position.y > b.max.y + b.body.position.y)
			return false;

		// No separating axis found, therefor there is at least one overlapping axis
		return true;
	}

}
