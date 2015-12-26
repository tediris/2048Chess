package physics;

import physics.*;
import entity.*;

import java.lang.*;

public class RigidBody extends Component{
	public Vector2 position;
	public Vector2 velocity;
	public float invMass;
	public float bounce;
	Vector2 force; // might not be necessary
	float width, height;
	public BoundingBox bounds;

	public RigidBody(Entity e, float width, float height) {
		super(e);
		e.body = this;
		this.width = width;
		this.height = height;
		invMass = 1f;
		bounce = 0.5f;
		bounds = new BoundingBox(
			new Vector2(e.transform.x, e.transform.y),
			new Vector2(e.transform.x + width, e.transform.y + height));

	}

	public static void ResolveCollision(RigidBody a, RigidBody b) {
		Vector2 rv = Vector2.add(b.velocity, a.velocity.neg());
		Vector2 normal = new Vector2();
		float penetration = GetCollisionNormal(a.bounds, b.bounds, normal);
		float velNormal = Vector2.dot(rv, normal);
		if (velNormal > 0) {
			// we are already moving away from the collision
			return;
		}

		float e = Math.min(a.bounce, b.bounce);

		float j = -(1 + e) * velNormal;
		j = j / (a.invMass + b.invMass);

		Vector2 impulse = normal.scale(j);
		a.velocity = Vector2.add(a.velocity, impulse.scale(a.invMass).neg());
		b.velocity = Vector2.add(b.velocity, impulse.scale(b.invMass));
	}

	private static float GetCollisionNormal(BoundingBox a, BoundingBox b, Vector2 result) {
		Vector2 aMid = new Vector2((a.max.x - a.min.x) / 2, (a.max.y - a.min.y) / 2);
		Vector2 bMid = new Vector2((b.max.x - b.min.x) / 2, (b.max.y - b.min.y) / 2);
		Vector2 n = Vector2.add(bMid, aMid.neg());
		Vector2 overlap = Vector2.add(aMid, bMid, n.abs().neg());
		if (overlap.x > overlap.y) {
			if (n.x > 0) {
				result.set(-1, 0);
			} else {
				result.set(1, 0);
			}
			return overlap.x;
		} else {
			if (n.y < 0) {
				result.set(0, -1);
			} else {
				result.set(0, 1);
			}
			return overlap.y;
		}
	}
}
