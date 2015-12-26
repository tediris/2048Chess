package engine;

import input.InputHandler;
import entity.*;
import physics.*;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Game extends JFrame {

	private boolean isRunning = true;
	private int fps = 30;
	private int tick = 1000 / fps;
	private int maxSkips = 10;
	public int windowWidth = 512;
	public int windowHeight = 512;

	BufferedImage backBuffer;
	Insets insets;

	public InputHandler input;
	public Camera camera;

	public ArrayList<Entity> entities;
	public ArrayList<Entity> newEntities;
	public ArrayList<Entity> entityCleanup;

	public ArrayList<RigidBody> bodies;
	public ArrayList<RigidBody> newBodies;
	public ArrayList<RigidBody> bodyCleanup;

	public Game() {
		/* Empty */
	}

	public Game(int width, int height) {
		this.windowWidth = width;
		this.windowHeight = height;
	}

	public void run() {
		init();

		long time = System.currentTimeMillis();
		int loops;

		while (isRunning) {
			loops = 0;
			long currTime = System.currentTimeMillis();
			while (currTime > time && loops < maxSkips) {
				update();
				time += tick;
				loops++;
			}
			draw();

			// yield the processor so the OS doesn't
			// think we are trying to go too ham
			Thread.yield();
			try {Thread.sleep(1);} catch(Exception e) {}
		}

		setVisible(false);
	}

	void init() {
		setTitle("Game");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right,
			insets.top + windowHeight + insets.bottom);
		backBuffer = new BufferedImage(windowWidth,
			windowHeight, BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		initialize();
	}

	/* Override this method when extending
	 * to do any initial setup, load images,
	 * etc.
	 */
	public void initialize() {
		entities = new ArrayList<Entity>();
		entityCleanup = new ArrayList<Entity>();
		newEntities = new ArrayList<Entity>();

		bodies = new ArrayList<RigidBody>();
		newBodies = new ArrayList<RigidBody>();
		bodyCleanup = new ArrayList<RigidBody>();

		camera = new Camera(this);
	}

	void update() {
		for (Entity e : entities) {
			e.update();
		}
		// Do a physics update
		checkCollision();

		// Refresh the input handler
		input.refresh();

		// Delete old entities and rigidbodies
		cleanup();
		cleanupBodies();

		// Add in all the new queued up entities
		createNewEntities();
		createNewBodies();
	}

	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		// fill in the background
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		// sort the entities
		// TODO: make this not happen all the time
		Collections.sort(entities);
		for (Entity e : entities) {
			if (e.renderer != null) {
				e.renderer.render(bbg, camera);
			}
		}
		// draw the buffer
		g.drawImage(backBuffer, 0, 0, this);
	}

	public void destroy(Entity e) {
		entityCleanup.add(e);
	}

	public void cleanup() {
		for (Entity e : entityCleanup) {
			entities.remove(e);
		}
		entityCleanup.clear();
	}

	public void addEntity(Entity e) {
		newEntities.add(e);
	}

	public void createNewEntities() {
		for (Entity e : newEntities) {
			entities.add(e);
		}
		newEntities.clear();
	}

	public void destroyBody(RigidBody b) {
		bodyCleanup.add(b);
	}

	public void cleanupBodies() {
		for (RigidBody b : bodyCleanup) {
			bodies.remove(b);
		}
		bodyCleanup.clear();
	}

	public void addBody(RigidBody b) {
		newBodies.add(b);
	}

	public void createNewBodies() {
		for (RigidBody b : newBodies) {
			bodies.add(b);
		}
		newBodies.clear();
	}

	private void checkCollision() {
		for (int i = 0; i < bodies.size(); i++) {
			for (int j = i + 1; j < bodies.size(); j++) {
				RigidBody a, b;
				a = bodies.get(i);
				b = bodies.get(j);
				if (BoundingBox.Collision(a.bounds, b.bounds)) {
					RigidBody.ResolveCollision(a, b);
				}
			}
		}
	}

	// use this when running a
	// performant game to force updates
	// to happen faster
	private void highResTimer() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(Long.MAX_VALUE);
				}
					catch(Exception exc) {}
			}
		}.start();
	}

}
