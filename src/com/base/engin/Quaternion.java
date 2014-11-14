package com.base.engin;

public class Quaternion {

	// variables
	private float x;
	private float y;
	private float z;
	private float w;

	// constructor
	public Quaternion(float x, float y, float z, float w) {

		// x = x
		this.x = x;
		// y = y
		this.y = y;
		// z = z
		this.z = z;
		// w = w
		this.w = w;
	}
	
	// length function 
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	//takes the length and sets it to one (length = length/length = 1)
	public Quaternion normalise(){
		
		//calls the length
		float length = length();
		
		// set overall vector length to one
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		
		// returns this (above code)
		return this;
	}
	
	// takes all the imaginary numbers and multiples them by -1 to get a -(value) 
	public Quaternion conjugate(){
		
		//retuns a new quaternion with new negative values
		return new Quaternion( -x , -y , -z , w);
		
	}
	
	//multiplication of a quaternion (a vector with a rotational unit)
	public Quaternion multi(Quaternion q){
		
		float w_ = w * q.getW() - x * q.getX() - y * q.getY() - z * q.getZ();
		float x_ = x * q.getW() + w * q.getX() + y * q.getZ() - z * q.getY();
		float y_ = y * q.getW() + w * q.getY() + z * q.getX() - x * q.getZ();
		float z_ = z * q.getW() + w * q.getZ() + x * q.getY() - y * q.getX();
		
		//returns a new quaternion with (above math answers)
		return new Quaternion(x_,y_,z_,w_);
		
	}
	
	//multiplication (quaternion times by vectors) 
	public Quaternion multi(Vector3f v){
		
		float w_ = -x * v.getX() - y * v.getY() - z * v.getZ();
		float x_ =  w * v.getX() + y * v.getZ() - z * v.getY();
		float y_ =  w * v.getY() + z * v.getX() - x * v.getZ();
		float z_ =  w * v.getZ() + x * v.getY() - y * v.getX();
		
		//returns a new quaternion with (above math answers)
		return new Quaternion(x_,y_,z_,w_);
	}
	
	// gets x
	public float getX() {
		// returns x
		return x;
	}

	// set x
	public void setX(float x) {
		// x = x
		this.x = x;
	}

	// gets y
	public float getY() {
		// returns y
		return y;
	}

	// set y
	public void setY(float y) {
		// y = y
		this.y = y;
	}

	// gets z
	public float getZ() {
		// returns z
		return z;
	}

	// set z
	public void setZ(float z) {
		// z = z
		this.z = z;
	}

	// gets w
	public float getW() {
		// returns w
		return w;
	}

	// sets w
	public void setW(float w) {
		// w = w
		this.w = w;
	}
}
