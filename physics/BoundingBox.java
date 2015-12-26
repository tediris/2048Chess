package physics;

import physics.*;

import java.lang.*;

public class BoundingBox {
	public Vector2 min, max, mid;
	private Vector2 pos;
	float radius;
	public BoundingBox(Vector2 min, Vector2 max) {
		this.min = min;
		this.max = max;
		this.mid = new Vector2((min.x + max.x) / 2, (min.y + max.y) / 2);
		radius = Vector2.distance(min, max) / 2;
		pos = new Vector2(min);
	}

	public void update(Vector2 newPos) {
		Vector2 delta = Vector2.add(newPos, pos.neg());
		min = Vector2.add(delta, min);
		max = Vector2.add(delta, max);
		mid = Vector2.add(delta, mid);
		pos.set(newPos.x, newPos.y);
	}

	private static boolean proximity(BoundingBox a, BoundingBox b) {
		float distance = Vector2.distance(a.mid, b.mid);
		return distance < (a.radius + b.radius);
	}

	public static boolean Collision(BoundingBox a, BoundingBox b) {
		// add this in to boost performance
		// if (!proximity(a, b)) return false;
		// Exit with no intersection if found separated along an axis
		if (a.max.x < b.min.x || a.min.x > b.max.x) return false;
		if (a.max.y < b.min.y || a.min.y > b.max.y) return false;

		// No separating axis found, therefor there is at least one overlapping axis
		return true;
	}

}
