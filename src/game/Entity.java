package game;

import java.awt.Color;
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
	
    protected float xScale = 1.f, yScale = 1.f, xScaleLocal = 1.f, yScaleLocal = 1.f, angle = 0.f;
    public float x, y;
    public boolean isColiding = false;
    protected int width, height, depth;
    protected Box collisionBox;
    protected SpriteSheet sprite;
    protected BufferedImage image;
    protected Animate animate;
    protected String name = "";
    protected boolean isSolid;
    protected List<SpriteSheet> animations = new ArrayList<SpriteSheet>();
    
    protected boolean isAnimatable, invisible = false;

    protected Vector2f getDrawPosition(View view)
    {
        return new Vector2f(((x - view.getX() + view.getWidth()/2 - width/2) * view.getZoom()) + view.getWidth()/2,
                            ((y - view.getY() + view.getHeight()/2 - height/2) * view.getZoom()) + view.getHeight()/2);
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
        this.x = x;
        this.y = y;
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
    	this.x = x;
    }

    public float getX()
    {
        return x;
    }
    
    public void setY(float y)
    {
    	this.y = y;
    }

    public float getY()
    {
        return y;
    }
    
    public int getDepth() {
    	return depth;
    }
    
    public float getXPosCenter() {
    	return x + width/2;
    }
    
    public float getYPosCenter() {
    	return y + height/2;
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
    
    public boolean getIsSolid() {
    	return isSolid;
    }
    
    public void setIsSolid(boolean b) {
    	isSolid = b;
    }
    
    public Box getCollisionBox() {
    	return collisionBox;
    }
    
    public void createCollisionBox(int left, int top, int right, int bottom) {
    	collisionBox = new Box(this, left, top, right, bottom);
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void update() {
    }
    
    public void draw(Graphics2D g, View view, BufferedImage image) {
    	
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
    }

	public BufferedImage getImage() {
		return null;
	}
	
	public void animate(int frame) {
		image = sprite.getSubimage(frame);
	}

}
