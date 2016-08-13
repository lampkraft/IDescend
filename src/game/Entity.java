package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import character.Animate;
import graphics.SpriteSheet;
import graphics.View;
import math.Box;
import math.Vector2f;

/**
 * Created by Lampkraft on 2015-04-05.
 */
public class Entity {
	
    protected float xPos, yPos, xScale = 1.f, yScale = 1.f, xScaleLocal = 1.f, yScaleLocal = 1.f, angle = 0.f;
    protected int width, height, depth;
    protected Box collisionBox;
    protected SpriteSheet sprite;
    protected BufferedImage image;
    protected Animate animate;
    protected List<SpriteSheet> animations = new ArrayList<SpriteSheet>();
    
    protected boolean isAnimatable, invisible = false;

    protected Vector2f getDrawPosition(View view)
    {
        return new Vector2f(((xPos - view.getX() + view.getWidth()/2 - width/2) * view.getZoom()) + view.getWidth()/2,
                            ((yPos - view.getY() + view.getHeight()/2 - height/2) * view.getZoom()) + view.getHeight()/2);
    }

    protected Vector2f getDrawScale(View view)
    {
        return new Vector2f(view.getZoom() * width * xScale, view.getZoom() * height * yScale);
    }

    protected void setScale(float x, float y)
    {
        xScale = x;
        yScale = y;
    }

    public void setPosition(float x, float y)
    {
        xPos = x;
        yPos = y;
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    protected void setAngle(float deg)
    {
    	angle = deg;
    }
    
    protected float getAngle()
    {
    	return angle;
    }
    
    public int getWidth()
    {
    	return width;
    }
    
    public int getHeight()
    {
    	return height;
    }

    public void setX(float x)
    {
        xPos = x;
    }

    public float getX()
    {
        return xPos;
    }
    
    public void setY(float y)
    {
        yPos = y;
    }

    public float getY()
    {
        return yPos;
    }
    
    public int getDepth() {
    	return depth;
    }
    
    public float getXPosCenter() {
    	return xPos + width/2;
    }
    
    public float getYPosCenter() {
    	return yPos + height/2;
    }
    
    public void setInvisible(boolean b) {
    	invisible = b;
    }
    
    public boolean getIsAnimatable() {
    	return isAnimatable;
    }
    
    public SpriteSheet getSprite() {
    	return sprite;
    }
    
    public void setSprite(SpriteSheet sprite) {
    	this.sprite = sprite;
    }
    
    public void update() {
    }
    
    public void draw(Graphics2D g, View view, BufferedImage image) {
    	Vector2f drawPosition = getDrawPosition(view);
        Vector2f drawScale = getDrawScale(view);
        g.drawImage(image, (int)(drawPosition.x-(xScaleLocal*(width/2))), (int)drawPosition.y, (int)(drawScale.x*xScaleLocal), (int)drawScale.y, null);
    }

	public BufferedImage getImage() {
		return null;
	}
	
	public void animate(int frame) {
		image = sprite.getSubimage(frame);
	}

}
