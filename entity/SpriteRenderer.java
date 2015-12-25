package entity;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SpriteRenderer extends Renderer {
	private String filename;
	private BufferedImage image;
	private int imageWidth, imageHeight;
	private int renderWidth, renderHeight;
	public SpriteRenderer(Entity e, String filename) {
		super(e);
		e.renderer = this;
		this.filename = filename;
		this.loadImage();
	}

	void loadImage() {
		try {
			image = ImageIO.read(new File("res/" + filename));
			// calculate scale
			imageWidth = image.getWidth();
			imageHeight = image.getHeight();
			renderWidth = (int) (imageWidth * this.entity.transform.xScale);
			renderHeight = (int) (imageHeight * this.entity.transform.yScale);
		} catch (IOException e) {
			// TODO: notify that image load failed
		}
	}

	public void render(Graphics g, Camera c) {
		if (entity.transform.parent != null) {
			renderChild(g, c);
			return;
		}
		g.drawImage(image, (int) (entity.transform.x + c.transform.x),
		(int) (entity.transform.y + c.transform.y),
		renderWidth, renderHeight, this.entity.game);
	}

	public void renderChild(Graphics g, Camera c) {
		int drawX = (int) (entity.transform.x + c.transform.x + entity.transform.parent.x);
		int drawY = (int) (entity.transform.y + c.transform.y + entity.transform.parent.y);
		g.drawImage(image, drawX, drawY, renderWidth, renderHeight, this.entity.game);
	}
}
