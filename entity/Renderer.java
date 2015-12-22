package entity;

import entity.*;
import java.awt.*;

public abstract class Renderer extends Component {
	public abstract void render(Graphics g);
	public Renderer(Entity e) {
		super(e);
	}
}
