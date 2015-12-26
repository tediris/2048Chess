package physics;

import physics.*;

public class BoundingBox {
	public Vector2 min, max;
	public BoundingBox(Vector2 min, Vector2 max) {
		this.min = min;
		this.max = max;
	}

	public static boolean Collision(BoundingBox a, BoundingBox b) {
		// Exit with no intersection if found separated along an axis
		if (a.max.x < b.min.x || a.min.x > b.max.x) return false;
		if (a.max.y < b.min.y || a.min.y > b.max.y) return false;

		// No separating axis found, therefor there is at least one overlapping axis
		return true;
	}

}
