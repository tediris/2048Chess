package entity;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class RectRenderer extends Renderer {
	private Color color;
	public float width, height;

	public RectRenderer(Entity e, float w, float h) {
		super(e);
		width = w;
		height = h;
		color = Color.BLACK;
	}

	public void render(Graphics g, Camera c) {
		g.setColor(color);
		if (entity.transform.parent != null) {
			renderChild(g, c);
			return;
		}
		int drawX = (int) (entity.transform.x - c.transform.x);
		int drawY = (int) (entity.transform.y - c.transform.y);
		g.drawRect(drawX, drawY, (int) width, (int) height);
	}

	public void renderChild(Graphics g, Camera c) {
		int drawX = (int) (entity.transform.x - c.transform.x + entity.transform.parent.x);
		int drawY = (int) (entity.transform.y - c.transform.y + entity.transform.parent.y);
		g.drawRect(drawX, drawY, (int) width, (int) height);
	}
}
