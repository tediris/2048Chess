import engine.*;
import entity.*;
import input.*;
import game.*;

public class ChessGame extends Game {
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		game.run();
		System.exit(0);
	}

	@Override
	public void initialize() {
		super.initialize();
		Entity grid = new Entity(this);
		grid.transform.z = -1;
		new SpriteRenderer(grid, "grid.png");
		new GameGrid(grid, 4, 4);
	}
}
