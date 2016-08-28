package graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Entity;
import math.Box;
import math.Vector2f;

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
    
    public void update() {
    	//collisionBox.update();
    }
    
    public void draw(Graphics2D g, View view, BufferedImage image) {
    
    	if(!invisible) {
	    	Vector2f drawPosition = getDrawPosition(view);
	        Vector2f drawScale = getDrawScale(view);
	        
	        g.drawImage(image, (int)(drawPosition.x-(xScaleLocal*(width/2))), (int)drawPosition.y, (int)(drawScale.x*xScaleLocal), (int)drawScale.y, null);
	        if(collisionBox != null) collisionBox.draw(g, drawPosition, drawScale);
	        g.setColor(new Color(0f, 0f, 0f, 1f));
	        g.drawRect(
					(int)(drawPosition.x-((width/2))),
					(int)(drawPosition.y),
					(int)((drawScale.x)),
					(int)(drawScale.y)
					);
    	
	        if(isSolid) g.drawString("Width: " + width + " Height: " + height, drawPosition.x-(xScaleLocal*(width/2)), drawPosition.y - 20);
    	}
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
}
