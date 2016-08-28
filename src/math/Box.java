package math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import character.Character;
import game.Entity;
import game.ObjectsController;
import graphics.View;

public class Box {
	public int left, top, right, bottom;
	public float x, y;
	private Entity host;
	
	public Box(Entity host) {
		this.host = host;
		this.right = 0;
		this.bottom = 0;
		this.left = 0;
		this.top = 0;
		setAbsoluteX();
		setAbsoluteY();
	}
	
	public Box(Entity host, int left, int top, int right, int bottom) {
		this.host = host;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
		//setAbsoluteX();
		//setAbsoluteY();
	}
	
	public void setSize(int width, int height) {
		this.right = width;
		this.bottom = height;
	}
	
	public int getAbsoluteX() {
		return (int)host.x + left;
	}
	
	public int getAbsoluteY() {
		return (int)host.y + top;
	}
	
	public int getAbsoluteWidth() {
		return (int)host.getWidth() - right - left;
	}
	
	public int getAbsoluteHeight() {
		return (int)host.getHeight() - bottom - top;
	}
	
	public void setAbsoluteX() {
		x = host.x + left;
	}
	
	public void setAbsoluteY() {
		y = host.y + top;
	}
	
	public void setPosition(int x, int y) {
		this.left = x;
		this.top = y;
	}
	
	public boolean getCollisionPoint(int x, int y) {
		if(		x > host.getX() + left &&
				y > host.getY() + top &&
				x < host.getX() + host.getWidth() - right &&
				y < host.getY() + host.getHeight() - bottom) {
			return true;
		}
		return false;
	}
	
	public boolean getCollisionMeeting(Entity target, int checkX, int checkY) {
		
		if(
				/*getAbsoluteX() + getAbsoluteWidth() > target.getCollisionBox().getAbsoluteX() &&
				getAbsoluteX() < target.getCollisionBox().getAbsoluteX() + target.getCollisionBox().getAbsoluteWidth() &&
				getAbsoluteY() + getAbsoluteHeight() > target.getCollisionBox().getAbsoluteY() &&
				getAbsoluteY() < target.getCollisionBox().getAbsoluteY() + target.getCollisionBox().getAbsoluteHeight()*/
				/*host.x + host.getWidth() > target.x &&
				host.x < target.x + target.getWidth() &&
				host.y + host.getHeight() > target.y &&
				host.y < target.y + target.getHeight()*/
				checkX > host.getX() + left &&
				checkY > host.getY() + top &&
				checkX < host.getX() + host.getWidth() - right &&
				checkY < host.getY() + host.getHeight() - bottom
		  ) {
			return true;
		}
		return false;
	}
	
	public void update() {

	}
	
	public void draw(Graphics2D g, Vector2f drawPosition, Vector2f drawScale) {
		g.setColor(new Color(1f, 1f, 0f, 1f));
		if(host.isColiding) g.setColor(new Color(0f, 1f, 0f, 1f));
		g.drawRect(
				(int)(drawPosition.x -((host.getWidth()/2 - left))),
				(int)(drawPosition.y + top),
				(int)((drawScale.x - right - left)),
				(int)(drawScale.y - bottom - top)
				);
	}
}
