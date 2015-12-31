import engine.*;
import entity.*;
import input.*;
import game.*;
import tests.*;

public class TestText extends Game {
	public static void main(String[] args) {
		TestText game = new TestText(640, 640);
		game.run();
		System.exit(0);
	}

	public TestText(int width, int height) {
		super(width, height);
	}

	@Override
	public void initialize() {
		super.initialize();

		UserInput user = new UserInput(this);
		user.transform.x = 200;
		user.transform.y = 200;
		user.transform.z = 2;
		TextRenderer text = new TextRenderer(user, "HELLO");
		new UpdateText(user, text);
		Entity e = new Entity(this);
		e.transform.x = 10;
		e.transform.y = 10;
		new RectRenderer(e, 100, 100);
	}
}
