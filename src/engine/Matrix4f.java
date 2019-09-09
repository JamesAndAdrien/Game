package engine;

public class Matrix4f {

	private float[][] m;
	
	public Matrix4f() {
		
		m = new float[4][4];
		
	}
	
	public Matrix4f initI() {
		
		for (int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				if (i == j) {
					
					m[i][j] = 1;
					
				}
				
				else
					
					m[i][j] = 0;
				
			}
			
		}
		
		return this;
		
	}
	
	public Matrix4f mult(Matrix4f m2) {
		
		Matrix4f m3 = new Matrix4f();
		
		for(int i = 0; i < 4; i++) {
			
			for (int j = 0; j < 4; j++) {
				
				float f = 0;
				
				for (int k = 0; k < 4; k++) {
					
					f = f + m[i][k]*m2.get(k, j);
					
				}
				
				m3.set(i, j, f);
				
			}
			
		}
		
		return m3;
		
	}

	public float[][] getM() {
		
		return m;
		
	}
	
	public float get(int x, int y) {
		
		return m[x][y];
		
	}
	
	public void setM(float[][] m) {
		
		this.m = m;
		
	}
	
	public void set(int x, int y, float val) {
		
		m[x][y] = val;
		
	}
	
}