package physics;

import entity.*;
import engine.*;
import physics.*;

public class Collider extends Component {
	public RigidBody body;
	public Collider(Entity e) {
		super(e);
		// make sure that there is a body attached
		entity.body.colliders.add(this);
		body = entity.body;
	}

	@Override
	public void update() {
		//System.out.println(entity.game.bodies.size());
		for (RigidBody b : entity.game.bodies) {
			if (b == entity.body) continue;
			for (Collider c : b.colliders) {
				Collide(c);
			}
		}
	}

	public void Collide(Collider c) {
		/* Empty */
		if (RigidBody.Collision(this, c)) {
			RigidBody.ResolveCollision(this, c);
		}
	}

}
