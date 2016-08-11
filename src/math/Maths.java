package math;

public class Maths {
	
	public static float getPointDistance(float x1, float y1, float x2, float y2) {
		float result = (float)Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return result;
	}
}
