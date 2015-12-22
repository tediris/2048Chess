import engine.*;
import entity.*;
import input.*;
import game.*;

public class ChessBattle extends Game {
	public static void main(String[] args) {
		ChessBattle game = new ChessBattle(512*2 + 128, 512);
		game.run();
		System.exit(0);
	}

	public ChessBattle(int w, int h) {
		super(w, h);
	}

	@Override
	public void initialize() {
		super.initialize();
		Entity grid = new Entity(this);
		grid.transform.z = -1;
		new SpriteRenderer(grid, "grid.png");
		new GameGrid(grid, 4, 4);

		Entity grid2 = new Entity(this);
		grid2.transform.z = -1;
		grid2.transform.x = 512 + 128;
		new SpriteRenderer(grid2, "grid.png");
		new GameGrid(grid2, 4, 4);
	}
}
