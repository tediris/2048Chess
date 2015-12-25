package entity;

import entity.*;

import java.lang.*;
import java.util.*;

public class Transform extends Component implements Comparable<Transform> {
	public float x, y, z;
	public float xScale, yScale;
	public Transform parent;
	ArrayList<Transform> children;
	public Transform(Entity e) {
		this(e, 0, 0, 0, 1, 1);
		children = new ArrayList<Transform>();
	}

	public Transform(Entity e, float x, float y, float z) {
		this(e, x, y, z, 1, 1);
	}

	public Transform(Entity e, float x, float y) {
		this(e, x, y, 0, 1, 1);
	}

	public Transform(Entity e, float x, float y, float z, float xScale, float yScale) {
		super(e);
		this.x = x;
		this.y = y;
		this.z = z;
		this.xScale = xScale;
		this.yScale = yScale;
	}

	public void attachChild(Transform child) {
		child.parent = this;
		children.add(child);
	}

	public int compareTo(Transform other) {
		float diff = this.z - other.z;
		if (diff < 0) {
			return -1;
		} else if (diff > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
