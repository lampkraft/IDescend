package math;

public class Box {
	private int left, top, right, bottom;
	
	public Box() {
		this.setRight(0);
		this.setBottom(0);
		this.setLeft(0);
		this.setTop(0);
	}
	
	public Box(int right, int bottom, int left, int top) {
		this.setRight(right);
		this.setBottom(bottom);
		this.setLeft(left);
		this.setTop(top);
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
}
