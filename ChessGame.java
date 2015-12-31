import engine.*;
import entity.*;
import input.*;
import game.*;

public class ChessGame extends Game {
	public static void main(String[] args) {
		ChessGame game = new ChessGame(640, 640);
		game.run();
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



		//UserInput user = new UserInput(this);
		// Entity user = new Entity(this);
		// user.transform.x = 200;
		// user.transform.y = 200;
		// user.transform.z = 2;
		// TextRenderer text = new TextRenderer(user);
		// text.text = "this is a test";
	}
}
