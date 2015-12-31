package physics;

import physics.*;
import entity.*;

import java.lang.*;
import java.util.*;

public class RigidBody extends Component{
	public Vector2 position;
	public Vector2 velocity;
	public float invMass;
	public float bounce;
	public float gravityScale = 1.0f;
	public boolean kinematic = false;
	Vector2 force; // might not be necessary
	public ArrayList<Collider> colliders;

	public RigidBody(Entity e) {
		super(e);
		e.body = this;
		e.game.addBody(this);
		position = new Vector2(e.transform.x, e.transform.y);
		velocity = new Vector2();
		invMass = 1f;
		bounce = 0.5f;
		colliders = new ArrayList<Collider>();
	}

	@Override
	public void update() {
		if (!kinematic) {
			// account for gravity
			velocity.y += 1.0 * gravityScale;
		}
		position.x += velocity.x;
		position.y += velocity.y;
		entity.transform.x = position.x;
		entity.transform.y = position.y;
	}

	public void updateTransform() {
		entity.transform.x = position.x;
		entity.transform.y = position.y;
	}

	public static boolean BoxCollision(BoxCollider a, BoxCollider b) {
		Vector2 dist = (Vector2.add(a.mid().neg(), b.mid())).abs();
		return (dist.x < a.half.x + b.half.x && dist.y < a.half.y + b.half.y);
	}

	public static boolean Collision(Collider a, Collider b) {
		if (a instanceof BoxCollider) {
			if (b instanceof BoxCollider) {
				return BoxCollision((BoxCollider) a, (BoxCollider) b);
			}
		}
		return false;
	}

	private void correct(Vector2 penetration) {
		if (kinematic) return;
		position.x += penetration.x;
		position.y += penetration.y;
		if (penetration.x != 0) velocity.x = 0;
		if (penetration.y != 0) velocity.y = 0;
		updateTransform();
	}

	public static void ResolveBox(BoxCollider a, BoxCollider b) {
		Vector2 dist = (Vector2.add(a.mid().neg(), b.mid())).abs();
		Vector2 axes = Vector2.add(a.half, b.half);
		Vector2 penetration = Vector2.add(axes, dist.neg());
		Vector2 rel = Vector2.add(a.mid().neg(), b.mid());
		if (penetration.x < penetration.y) {
			// x axis is the normal
			penetration.y = 0;
			if (rel.x > 0) {
				// b is to the right
				a.body.correct(penetration.neg());
				b.body.correct(penetration);
			} else {
				// a is to the right
				a.body.correct(penetration);
				b.body.correct(penetration.neg());
			}

		} else {
			// y axis is the normal
			penetration.x = 0;
			if (rel.y > 0) {
				// b is below
				a.body.correct(penetration.neg());
				b.body.correct(penetration);
			} else {
				// b is above
				a.body.correct(penetration);
				b.body.correct(penetration.neg());
			}
		}

	}

	public static void ResolveCollision(Collider a, Collider b) {
		if (a instanceof BoxCollider) {
			if (b instanceof BoxCollider) {
				ResolveBox((BoxCollider) a, (BoxCollider) b);
			}
		}
	}

}
