package game;

import entity.*;
import engine.*;
import game.*;

public class Bow extends Tile {
	public Bow(Game g) {
		super(g, "bow.png");
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
