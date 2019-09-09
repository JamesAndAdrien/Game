package engine;

public class Vector3f {

	private float x;
	
	private float y;
	
	private float z;
	
	public Vector3f(float x, float y, float z) {
		
		this.setX(x);
		
		this.setY(y);
		
		this.setZ(z);
		
	}
	
	public float length() {
		
		return (float) Math.sqrt(x * x + y * y + z * z);
		
	}
	
	public float dot(Vector3f v) {
		
		return x * v.getX() + y * v.getY() + z * v.getZ();
		
	}
	
	public Vector3f cross(Vector3f v) {
		
		float x1 = y * v.getZ() - z * v.getY();
		
		float y1 = z * v.getX() - x * v.getZ();
		
		float z1 = x * v.getY() - y * v.getX();
		
		return new Vector3f(x1,y1,z1);
		
	}
	
	public Vector3f norm() {
		
		float length = length();
		
		x /= length;
		
		y /= length;
		
		z /= length;
		
		return this;
		
	}
	
	public Vector3f add(Vector3f v) {
		
		return new Vector3f(x + v.getX(),y + v.getY(),z + v.getZ());
		
	}
	
	public Vector3f scale(float c) {
		
		return new Vector3f(x * c,y * c,z * c);
		
	}
	
	public Vector3f compAdd(float c) {
		
		return new Vector3f(x + c,y + c,z + c);
		
	}
	
	public String toString() {
		
		return "[" + x + "," + y + "," + z + "]";
		
	}

	public float getX() {
		
		return x;
	
	}

	public void setX(float x) {
		
		this.x = x;
		
	}

	public float getY() {
		
		return y;
		
	}

	public void setY(float y) {
		
		this.y = y;
		
	}

	public float getZ() {
		
		return z;
		
	}

	public void setZ(float z) {
		
		this.z = z;
		
	}
	
}