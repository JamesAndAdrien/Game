package engine;

public class Vector2f {

	private float x;
	
	private float y;
	
	public Vector2f(float x, float y) {
		
		this.x = x;
		
		this.y = y;
		
	}
	
	public float length() {
		
		return (float) Math.sqrt(x * x + y * y);
		
	}
	
	public float dot(Vector2f v) {
		
		return x * v.getX() + y * v.getY();
		
	}
	
	public Vector2f norm() {
		
		float length = length();
		
		if (length == 0) {
			
			x = 0;
		
			y = 0;
		
			return this;
			
		}
		
		else
			
			x /= length;
		
			y /= length;
			
			return this;
		
	}
	
	public Vector2f rotate(float alpha) {
		
		double rad = Math.toRadians(alpha);
		
		return new Vector2f((float)(x * Math.cos(rad) - y * Math.sin(rad)),(float)(x * Math.sin(rad) + y * Math.cos(rad)));
		
	}
	
	public Vector2f add(Vector2f v) {
		
		return new Vector2f(x + v.getX(),y + v.getY());
		
	}
	
	public Vector2f scale(float c) {
		
		return new Vector2f(x * c,y * c);
		
	}
	
	public Vector2f compAdd(float c) {
		
		return new Vector2f(x + c, y + c);
		
	}
	
	public String toString() {
		
		return "[" + x + "," + y + "]";
		
	}

	public float getY() {
		
		return y;
	
	}

	public void setY(float y) {
		
		this.y = y;
		
	}

	public float getX() {
		
		return x;
		
	}

	public void setX(float x) {
		
		this.x = x;
		
	}
	
}