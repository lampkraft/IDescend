package math;

public class Box {
	public int left, top, right, bottom;
	
	public Box() {
		this.right = 0;
		this.bottom = 0;
		this.left = 0;
		this.top = 0;
	}
	
	public Box( int left, int top, int right, int bottom) {
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
}
