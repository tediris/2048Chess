package engine;

import input.InputHandler;
import entity.*;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Game extends JFrame {

	private boolean isRunning = true;
	private int fps = 30;
	public int windowWidth = 512;
	public int windowHeight = 512;

	BufferedImage backBuffer;
	Insets insets;

	public InputHandler input;
	public Camera camera;

	public ArrayList<Entity> entities;
	public ArrayList<Entity> newEntities;
	public ArrayList<Entity> entityCleanup;

	public Game() {
		/* Empty */
	}

	public Game(int width, int height) {
		this.windowWidth = width;
		this.windowHeight = height;
	}

	public void run() {
		init();

		while (isRunning) {
			long time = System.currentTimeMillis();
			update();
			draw();

			time = (1000 / fps) - (System.currentTimeMillis() - time);
			try {
				Thread.sleep(time);
			} catch (Exception e) {
				// TODO: add more robust handling
			}
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

		camera = new Camera(this);
	}

	void update() {
		for (Entity e : entities) {
			e.update();
		}
		input.refresh();
		cleanup();
		createNewEntities();
	}

	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		// fill in the background
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		// sort the entities
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

}
