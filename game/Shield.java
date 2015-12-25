package game;

import entity.*;
import engine.*;
import game.*;

public class Shield extends Tile {
	int health;
	Heart heart;

	public Shield(Game g) {
		super(g, "shield.png");
		heart = new Heart(g, this);
		health = 2;
	}

	@Override
	public Tile combine(Tile collider) {
		if (collider instanceof Arrow) {
			if (health > 0) {
				health--;
				if (health == 1) {
					heart.updateShape(Heart.HALF);
				} else {
					heart.updateShape(Heart.EMPTY);
				}
			} else {
				removeOnUpdate = true;
			}
			this.toDestroy.push(collider);
			return this;
		} else {
			return null;
		}
	}
}
