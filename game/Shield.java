package game;

import entity.*;
import engine.*;
import game.*;

public class Shield extends Tile {
	public Shield(Game g) {
		super(g, "shield.png");
	}

	@Override
	public Tile combine(Tile collider) {
		if (collider instanceof Arrow) {
			this.toDestroy.push(collider);
			return this;
		} else {
			return null;
		}
	}
}
