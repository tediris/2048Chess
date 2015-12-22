package entity;

import entity.*;

public class Component {
	public boolean enabled = true;
	public Entity entity;
	public Component(Entity e) {
		entity = e;
		e.addComponent(this);
	}

	/* Components that need to be updated
	 * should override the update method,
	 * e.g. character controllers, etc.
	 */
	public void update() {
		/* Empty */
	}
}
