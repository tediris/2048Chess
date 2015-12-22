package game;

import game.*;
import entity.*;
import engine.*;

import java.lang.*;

public class Slider extends Component {
	float xDest;
	float yDest;
	int tileSize;
	float maxVelocity = 64f;
	public Slider(Tile e) {
		super(e);
		xDest = e.transform.x;
		yDest = e.transform.y;
		// TODO: calculate this in a way that does not
		// break multiplayer
		tileSize = 128;
	}

	@Override
	public void update() {
		if (Math.abs(entity.transform.x - xDest) > tileSize) {
			if (entity.transform.x > xDest) {
				entity.transform.x -= maxVelocity;
			} else {
				entity.transform.x += maxVelocity;
			}
		} else {
			// slow down as we get close
			entity.transform.x = (entity.transform.x + xDest) / 2;
		}

		if (Math.abs(entity.transform.y - yDest) > tileSize) {
			if (entity.transform.y > yDest) {
				entity.transform.y -= maxVelocity;
			} else {
				entity.transform.y += maxVelocity;
			}
		} else {
			// slow down as we get close
			entity.transform.y = (entity.transform.y + yDest) / 2;
		}
	}
}
