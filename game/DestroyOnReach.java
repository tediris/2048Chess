package game;

import game.*;
import entity.*;
import engine.*;

import java.lang.*;

public class DestroyOnReach extends Component {
	float endX, endY, epsilon;

	public DestroyOnReach(Tile e, float x, float y) {
		super(e);
		this.endX = x;
		this.endY = y;
		e.transform.z = 0;
		this.epsilon = 0.1f;
	}

	@Override
	public void update() {
		if (Math.abs(entity.transform.x - endX) < epsilon
			&& Math.abs(entity.transform.y - endY) < epsilon) {
			entity.destroy();
		}
	}
}
