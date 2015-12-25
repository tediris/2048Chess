package entity;
import entity.*;
import engine.*;

import java.util.ArrayList;

public class Entity implements Comparable<Entity> {
	String name;
	public Transform transform;
	public Renderer renderer;
	public Game game;
	ArrayList<Component> components;
	public Entity(Game g, String name) {
		this.game = g;
		this.name = name;
		components = new ArrayList<Component>();
		transform = new Transform(this);
		g.addEntity(this);
	}

	public Entity(Game g) {
		this(g, null);
	}

	public void destroy() {
		for (Transform t : transform.children) {
			game.destroy(t.entity);
		}
		game.destroy(this);
	}

	public void addComponent(Component c) {
		components.add(c);
	}

	public void update() {
		for (Component c : components) {
			c.update();
		}
	}

	public int compareTo(Entity other) {
		return transform.compareTo(other.transform);
	}
}
