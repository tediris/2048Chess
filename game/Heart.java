package game;

import entity.*;
import engine.*;
import game.*;

import java.util.*;

public class Heart extends Entity {
	Tile parent;
	public static final int FULL = 0;
	public static final int HALF = 1;
	public static final int EMPTY = 2;
	public int state = FULL;
	SpriteRenderer full;
	SpriteRenderer half;
	SpriteRenderer empty;

	public Heart(Game g, Tile parent) {
		super(g);
		this.parent = parent;
		transform.z = 2; // render in front of tiles
		transform.x = 5;
		transform.y = 5;
		transform.xScale = 0.5f;
		transform.yScale = 0.5f;
		parent.transform.attachChild(transform);
		empty = new SpriteRenderer(this, "heart_empty.png");
		half = new SpriteRenderer(this, "heart_half.png");
		full = new SpriteRenderer(this, "heart_whole.png");
	}

	public void updateShape(int newShape) {
		state = newShape;
		switch (state) {
			case FULL:
				// switch to the full heart image
				renderer = full;
				break;
			case HALF:
				// switch to the half heart image
				renderer = half;
				break;
			case EMPTY:
				// disable the renderer
				renderer = empty;
				break;
			default:
				break;
		}
	}
}
