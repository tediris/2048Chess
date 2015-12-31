package entity;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class TextRenderer extends Renderer {
	public String text;
	public String fontName;
	public int fontSize;
	private Font font;
	private Color color;

	public TextRenderer(Entity e) {
		this(e, "");
	}

	public TextRenderer(Entity e, String s) {
		super(e);
		text = s;
		fontName = "TimesRoman";
		fontSize = 20;
		color = Color.BLACK;
		updateFont();
	}

	public void updateFont() {
		font = new Font(fontName, Font.PLAIN, fontSize);
	}

	public void render(Graphics g, Camera c) {
		g.setFont(font);
		g.setColor(color);
		if (entity.transform.parent != null) {
			renderChild(g, c);
			return;
		}
		int drawX = (int) (entity.transform.x - c.transform.x);
		int drawY = (int) (entity.transform.y - c.transform.y);
		g.drawString(text, drawX, drawY);
	}

	public void setText(String s) {
		text = s;
	}

	public void renderChild(Graphics g, Camera c) {
		int drawX = (int) (entity.transform.x - c.transform.x + entity.transform.parent.x);
		int drawY = (int) (entity.transform.y - c.transform.y + entity.transform.parent.y);
		g.drawString(text, drawX, drawY);
	}
}
