package game;

import entity.*;
import engine.*;
import game.*;

public class Arrow extends Tile {
	public Arrow(Game g) {
		super(g, "arrow.png");
	}

	@Override
	public Tile combine(Tile collider) {
		if (collider instanceof King || collider instanceof Bow) {
			collider.toDestroy.push(this);
			return collider;
		} else if (collider instanceof Shield) {
			return ((Shield) collider).combine(this);
		} else {
			return null;
		}
	}
}
