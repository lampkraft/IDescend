package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private BufferedImage image, spriteSheet;
	private List<BufferedImage> images;
	private int currentFrame, width, height, x, y, row, col, numberOfFrames;
	
	public SpriteSheet(BufferedImage image, int x, int y, int width, int height, int col, int row, int numberOfFrames) {
		
		this.image = image;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
		this.numberOfFrames = numberOfFrames;
		images = new ArrayList<BufferedImage>();
		extractImagesFromSpriteSheet();
	}
	
	public BufferedImage getSubimage(int num) {
		return images.get(num);
	}
	
	private void extractImagesFromSpriteSheet() {
		for(int i = 0; i < row; i++) {
			for(int a = 0; a < col; a++) {
				if(images.size() < numberOfFrames) {
					images.add(image.getSubimage(x + (a * width), y + (i * height), width, height));
					//System.out.println(images.get(i));
				} else {
					break;
				}
				
			}
		}
	}
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public int getNumberOfFrames() {
		return numberOfFrames;
	}
	
	public int getSpriteWidth() {
		return width;
	}
	
	public int getSpriteHeight() {
		return width;
	}
}
