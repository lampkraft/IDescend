package graphics;

import java.awt.image.BufferedImage;

import game.Entity;
import math.Box;

/**
 * Created by Lampkraft on 2015-04-08.
 */
public class Tile extends Entity{

    public Tile(float x, float y, int width, int height, float depth, BufferedImage image, boolean animatable)
    {
        this.sprite = new SpriteSheet(image, 0, 0, width, height, 1, 1, 1);
        this.image = this.sprite.getSubimage(0);
        this.isAnimatable = animatable;
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.depth = (int)(-yPos*depth);
    }
    
    public Tile(float x, float y, int width, int height, float depth, SpriteSheet sprite, boolean animatable)
    {
        this.sprite = sprite;
        this.image = this.sprite.getSubimage(0);
        this.isAnimatable = animatable;
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.depth = (int)(-yPos*depth);
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
}
