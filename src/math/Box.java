package math;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import character.Character;
import graphics.View;

public class Box {
	public int left, top, right, bottom;
	private Character host;
	
	public Box(Character host) {
		this.host = host;
		this.right = 0;
		this.bottom = 0;
		this.left = 0;
		this.top = 0;
	}
	
	public Box(Character host, int left, int top, int right, int bottom) {
		this.host = host;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
	}
	
	public void setSize(int width, int height) {
		this.right = width;
		this.bottom = height;
	}
	
	public void setPosition(int x, int y) {
		this.left = x;
		this.top = y;
	}
	
	public boolean getCollisionPoint(int x, int y) {
		if(		x > host.getX() + left && y > host.getY() + top &&
				x < host.getX() + host.getWidth() - right && y < host.getY() + host.getHeight() - bottom) {
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g, Vector2f drawPosition, Vector2f drawScale) {
		g.setColor(new Color(1f, 1f, 0f, 1f));
		g.drawRect(
				(int)(drawPosition.x-((host.getWidth()/2 - left))),
				(int)(drawPosition.y + top),
				(int)((drawScale.x - right - left)),
				(int)(drawScale.y - bottom - top)
				);
	}
}
