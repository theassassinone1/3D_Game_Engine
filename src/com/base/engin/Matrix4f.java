package com.base.engin;

public class Matrix4f {
	
	//double array list
	private float [][] m;
	
	//constructor
	public Matrix4f(){
		
		//inits m array
		m = new float [4][4];
	}
	
	//inits matrix for identity
	public Matrix4f initIdentity(){
		
		m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
		m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
		
		return this;
	} 
	
	//inits matrix for translation
	public Matrix4f initTranslation(float x, float y, float z){
			
		m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = x;
		m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = y;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = z;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
			
		return this;
	} 
	
	//inits matrix for rotation
	public Matrix4f initRotation(float x, float y, float z){
		
		//creates new rotational x,y,z
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		//converts x, y and z into radians
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
		
		rx.m[0][0] = 1;					 rx.m[0][1] = 0; 				  rx.m[0][2] = 0;					rx.m[0][3] = 0;
		rx.m[1][0] = 0;					 rx.m[1][1] = (float)Math.cos(x); rx.m[1][2] = -(float)Math.sin(x); rx.m[1][3] = 0;
		rx.m[2][0] = 0; 				 rx.m[2][1] = (float)Math.sin(x); rx.m[2][2] =  (float)Math.cos(x); rx.m[2][3] = 0;
		rx.m[3][0] = 0; 				 rx.m[3][1] = 0; 				  rx.m[3][2] = 0; 					rx.m[3][3] = 1;
		
		ry.m[0][0] = (float)Math.cos(y); ry.m[0][1] = 0; 				  ry.m[0][2] = -(float)Math.sin(y); ry.m[0][3] = 0;
		ry.m[1][0] = 0; 				 ry.m[1][1] = 1;  				  ry.m[1][2] = 0;			 	    ry.m[1][3] = 0;
		ry.m[2][0] = (float)Math.sin(y); ry.m[2][1] = 0; 				  ry.m[2][2] =  (float)Math.cos(y); ry.m[2][3] = 0;
		ry.m[3][0] = 0; 				 ry.m[3][1] = 0; 				  ry.m[3][2] = 0;					ry.m[3][3] = 1;
		
		rz.m[0][0] = (float)Math.cos(z); rz.m[0][1] = -(float)Math.sin(z); rz.m[0][2] = 0; 					rz.m[0][3] = 0;
		rz.m[1][0] = (float)Math.sin(z); rz.m[1][1] =  (float)Math.cos(z); rz.m[1][2] = 0; 					rz.m[1][3] = 0;
		rz.m[2][0] = 0; 				 rz.m[2][1] = 0; 			 	   rz.m[2][2] = 1; 					rz.m[2][3] = 0;
		rz.m[3][0] = 0; 				 rz.m[3][1] = 0; 				   rz.m[3][2] = 0; 					rz.m[3][3] = 1;
		
		//m = rz times by ry time by rx
	    m = rz.multi(ry.multi(rx)).getM();
	    
	    return this;
	} 
	
	//inits matrix for scale
	public Matrix4f initScale(float x, float y, float z){
				
		m[0][0] = x; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
		m[1][0] = 0; m[1][1] = y; m[1][2] = 0; m[1][3] = 0;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = z; m[2][3] = 0;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
				
		return this;
	} 
	
	//inits matrix for projection
		public Matrix4f initProjection(float fov,float width, float height, float zNear, float zFar){
			
			//aspect ration
			float ar = width/height;
			//caluclats the distance from edge of window to the middle 
			float tanHalfFov = (float)Math.tan(Math.toRadians(fov / 2));
			//calculates the space between (depths)
			float zRange = zNear - zFar;
			
			m[0][0] = 1.0f / (tanHalfFov * ar); m[0][1] = 0; 				   m[0][2] = 0; 			  	   m[0][3] = 0;
			m[1][0] = 0;				 	  m[1][1] = 1.0f / tanHalfFov; m[1][2] = 0; 			  	   m[1][3] = 0;
			m[2][0] = 0; 				 	  m[2][1] = 0; 				   m[2][2] = (-zNear - zFar)/zRange; m[2][3] = 2 * zFar * zNear/zRange;
			m[3][0] = 0; 				 	  m[3][1] = 0; 				   m[3][2] = 1; 			  	   m[3][3] = 0;
			
			return this;
		} 
	
	public Matrix4f multi(Matrix4f a){
		
		//result
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.set(i, j, m[i][0] * a.get(0, j)+
							m[i][1] * a.get(1, j)+
							m[i][2] * a.get(2, j)+
							m[i][3] * a.get(3, j));
			}
		}
		
		//returns result
		return res;
	}
	
	//get
	public float get(int x , int y){
		
		//returns m (sub x)(sub y)
		return m [x][y];
		
	}
	
	//set
	public void set(int x , int y , float value){
		
		//m(sub x)(sub y) equals value
		m[x][y] = value;
	}
	
	//gets m
	public float[][] getM() {
		//returns m
		return m;
	}
	
	//sets m
	public void setM(float[][] m) {
		//m = m
		this.m = m;
	}
	
}
