package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import math.Vector2f;

public class Background {
	
	private BufferedImage image;
	
	public Background(BufferedImage image) {
		this.image = image;
	}
	
	public void drawTiled(Graphics2D g, View view) {
		for(int i = 0; i < view.getWidth()/image.getWidth()+1; i++) {
			for(int a = 0; a < view.getHeight()/image.getHeight()+1; a++) {
				g.drawImage(image, (int)(i * image.getWidth()), (int)(a * image.getHeight()), image.getWidth(), image.getHeight(), null);
			}
		}
    }
	public void drawRegular(Graphics2D g, View view) {
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }
	public void drawStretched(Graphics2D g, View view) {
		g.drawImage(image, 0, 0, view.getWidth(), view.getHeight(), null);
    }
}
