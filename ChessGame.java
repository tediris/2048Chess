import engine.*;
import entity.*;
import input.*;
import game.*;

public class ChessGame extends Game {
	public static void main(String[] args) {
		ChessGame game = new ChessGame(640, 640);
		game.runExperimental();
		System.exit(0);
	}

	public ChessGame(int width, int height) {
		super(width, height);
	}

	@Override
	public void initialize() {
		super.initialize();
		Entity grid = new Entity(this);
		grid.transform.z = -1;
		new SpriteRenderer(grid, "grid5.png");
		new GameGrid(grid, 5, 5);
	}
}
