package game;

import entity.*;
import engine.*;
import game.*;

import java.util.*;

public class Tile extends Entity {

	public static final int KING = 0;
	public static final int ARROW = 1;
	public static final int BOW = 2;
	public static final int SHIELD = 3;

	public Slider slider;
	public GameGrid grid;
	public Stack<Tile> toDestroy;

	public Tile(Game g, String imageName) {
		super(g);
		this.transform.xScale = 0.25f;
		this.transform.yScale = 0.25f;
		transform.z = 1;
		toDestroy = new Stack<Tile>();
		new SpriteRenderer(this, imageName);
		slider = new Slider(this);
	}

	/* Need to override this method for
	 * custom tiles
	 */
	public Tile combine(Tile collider) {
		return null;
	}

	public void destroyChildTiles() {
		while (!toDestroy.empty()) {
			Tile t = toDestroy.pop();
			t.slider.xDest = this.slider.xDest;
			t.slider.yDest = this.slider.yDest;
			new DestroyOnReach(t, this.slider.xDest, this.slider.yDest);
		}
	}
}
