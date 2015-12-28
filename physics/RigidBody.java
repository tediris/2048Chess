package physics;

import physics.*;
import entity.*;

import java.lang.*;

public class RigidBody extends Component{
	public Vector2 position;
	public Vector2 velocity;
	public float invMass;
	public float bounce;
	public float gravityScale = 1.0f;
	public boolean kinematic = false;
	Vector2 force; // might not be necessary

	public RigidBody(Entity e) {
		super(e);
		e.body = this;
		//e.game.addBody(this);
		position = new Vector2(e.transform.x, e.transform.y);
		velocity = new Vector2();
		invMass = 1f;
		bounce = 0.5f;
	}

	@Override
	public void update() {
		if (!kinematic) {
			// account for gravity
			//velocity.y += 1.0 * gravityScale;
		}
		position.x += velocity.x;
		position.y += velocity.y;
		entity.transform.x = position.x;
		entity.transform.y = position.y;
	}

	// public static void ResolveCollision(RigidBody a, RigidBody b) {
	// 	Vector2 rv = Vector2.add(a.velocity, b.velocity.neg());
	// 	Vector2 normal = new Vector2();
	// 	float penetration = GetCollisionNormal(a.bounds, b.bounds, normal);
	// 	//System.out.println("Collision normal: " + normal);
	// 	float velNormal = Vector2.dot(rv, normal);
	// 	// if (velNormal > 0) {
	// 	// 	// we are already moving away from the collision
	// 	// 	return;
	// 	// }
	// 	resolveOverlap(a, normal, penetration);
	// 	resolveOverlap(b, normal.neg(), penetration);
	//
	// 	float e = Math.min(a.bounce, b.bounce);
	//
	// 	float j = -(1 + e) * velNormal;
	// 	j = j / (a.invMass + b.invMass);
	//
	// 	Vector2 impulse = normal.scale(j);
	// 	//if (!a.kinematic) a.velocity = Vector2.add(a.velocity, impulse.scale(a.invMass).neg());
	// 	//if (!b.kinematic) b.velocity = Vector2.add(b.velocity, impulse.scale(b.invMass));
	// }

	// private static void resolveOverlap(RigidBody b, Vector2 dir, float overlap) {
	// 	if (b.kinematic) return;
	// 	b.position.x += dir.x * overlap;
	// 	b.position.y += dir.y * overlap;
	// 	if (b.velocity.x * dir.x < 0) b.velocity.x = 0;
	// 	if (b.velocity.y * dir.y < 0) b.velocity.y = 0;
	// }
	//
	// private static float GetCollisionNormal(BoundingBox a, BoundingBox b, Vector2 result) {
	// 	Vector2 aExtent = new Vector2((a.max.x - a.min.x)/ 2, (a.max.y - a.min.y) / 2);
	// 	Vector2 bExtent = new Vector2((b.max.x - b.min.x)/ 2, (b.max.y - b.min.y) / 2);
	// 	Vector2 n = Vector2.add(b.mid, b.body.position, a.mid.neg(), a.body.position.neg());
	// 	Vector2 overlap = Vector2.add(aExtent, bExtent, n.abs().neg());
	// 	if (overlap.x > overlap.y) {
	// 		if (n.y > 0) {
	// 			result.set(0, -1);
	// 		} else {
	// 			result.set(0, 1);
	// 		}
	// 		return overlap.y;
	// 	} else {
	// 		if (n.x > 0) {
	// 			result.set(-1, 0);
	// 		} else {
	// 			result.set(1, 0);
	// 		}
	// 		return overlap.x;
	// 	}
	// }
}
