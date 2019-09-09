package engine;

public class Quaternion {

	private float w;
	
	private float x;
	
	private float y;
	
	private float z;
	
	public Quaternion(float w, float x, float y, float z) {
		
		this.w = w;
		
		this.x = x;
		
		this.y = y;
		
		this.z = z;
		
	}
	
	public float length() {
		
		return (float) Math.sqrt(w * w + x * x + y * y + z * z);
		
	}
	
	public Quaternion norm() {
		
		float length = length();
		
		w /= length;
		
		x /= length;
		
		y /= length;
		
		z /= length;
		
		return this;
		
	}
	
	public Quaternion conjugate() {
		
		return new Quaternion(w, -x, -y, -z);
		
	}
	
	public Quaternion mult(Quaternion q) {
		
		float w1 = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
		
		float x1 = x * q.getW() + w * q.getX() - z * q.getY() + y * q.getZ();
		
		float y1 = y * q.getW() + z * q.getX() + w * q.getY() - x * q.getZ();
		
		float z1 = z * q.getW() - y * q.getX() + x * q.getY() + w * q.getZ();
		
		return new Quaternion(w1, x1, y1, z1);
		
	}
	
	public Quaternion mult(Vector3f v) {
		
		float w1 = - x * v.getX() - y * v.getY() - z * v.getZ();
		
		float x1 = w * v.getX() + y * v.getZ() - z * v.getY();
		
		float y1 = w * v.getY() - x * v.getZ() + z * v.getX();
		
		float z1 = w * v.getZ() + x * v.getY() - y * v.getX();
		
		return new Quaternion(w1, x1, y1, z1);
		
	}
	
	public float getW() {
		
		return w;

	}

	public void setW(float w) {
		
		this.w = w;
	
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

