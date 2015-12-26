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
	static final float slowDown = 0.5f;
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
			float xDist = Math.abs(xDest - entity.transform.x);
			if (entity.transform.x > xDest) {
				entity.transform.x -= xDist * slowDown;
			} else {
				entity.transform.x += xDist * slowDown;
			}
		}

		if (Math.abs(entity.transform.y - yDest) > tileSize) {
			if (entity.transform.y > yDest) {
				entity.transform.y -= maxVelocity;
			} else {
				entity.transform.y += maxVelocity;
			}
		} else {
			// slow down as we get close
			float yDist = Math.abs(yDest - entity.transform.y);
			if (entity.transform.y > yDest) {
				entity.transform.y -= yDist * slowDown;
			} else {
				entity.transform.y += yDist * slowDown;
			}
		}
	}
}
