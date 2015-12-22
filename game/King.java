package game;

import entity.*;
import engine.*;
import game.*;

public class King extends Tile {
	public King(Game g) {
		super(g, "king.png");
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
