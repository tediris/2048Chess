package physics;

import physics.*;

import java.lang.*;

public class Vector2 {
	public float x, y;
	public Vector2() {
		this(0, 0);
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 scale(float s) {
		return new Vector2(x * s, y * s);
	}

	public static Vector2 zero() {
		return new Vector2();
	}

	public static Vector2 add(Vector2... inputs) {
		float x = 0;
		float y = 0;
		for (Vector2 v : inputs) {
			x += v.x;
			y += v.y;
		}
		return new Vector2(x, y);
	}

	public static float dot(Vector2 a, Vector2 b) {
		return a.x * b.x + a.y * b.y;
	}

	public static float distance(Vector2 a, Vector2 b) {
		return new Vector2(a.x - b.x, a.y - b.y).magnitude();
	}

	public Vector2 neg() {
		return new Vector2(-x, -y);
	}

	public void negate() {
		x *= -1;
		y *= -1;
	}

	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public void normalize() {
		float mag = magnitude();
		x = x / mag;
		y = y / mag;
	}

	public Vector2 normalized() {
		float mag = magnitude();
		return new Vector2(x / mag, y / mag);
	}

	public Vector2 abs() {
		return new Vector2(Math.abs(x), Math.abs(y));
	}

	public String toString() {
		return "" + x + ", " + y;
	}
}
