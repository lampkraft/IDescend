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
        this.isAnimatable = animatable;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = new SpriteSheet(image, 0, 0, this.width, this.height, 1, 1, 1);
        this.image = this.sprite.getSubimage(0);
        this.depth = (int)(-this.y*depth);
    }
    
    public Tile(float x, float y, int width, int height, float depth, SpriteSheet sprite, boolean animatable)
    {
        
        this.isAnimatable = animatable;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.image = this.sprite.getSubimage(0);
        this.depth = (int)(-this.y*depth);
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
}
