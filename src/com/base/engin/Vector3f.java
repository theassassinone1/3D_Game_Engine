package com.base.engin;

public class Vector3f {

	// float x, y and z variable
	private float x;
	private float y;
	private float z;

	// Constructor
	public Vector3f(float x, float y, float z) {

		// x = x
		this.x = x;
		// y = y
		this.y = y;
		// z = z
		this.z = z;

	}

	// length function (gets the "length" the amount of the vector but not the direction)
	public float length() {
		// Square root
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	//multiplication between two vectors that takes into account the direction of the vector 
	public float dot(Vector3f v) {
		// returns the x value of a vector times by x plus the y time by the y plus the z times by the z
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	
	//the vector perpendicular to two other vectors
	public Vector3f cross(Vector3f v){
		
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		
		return new Vector3f (x_,y_,z_);
	}
	
	//takes the length and sets it to one (length = length/length = 1)
	public Vector3f normalise() {

		//calls the length
		float length = length();

		// set overall vector length to one
		x /= length;
		y /= length;
		z /= length;

		// returns this vector
		return this;
	}

	// vector rotate function
	public Vector3f rotate(float angle, Vector3f axis) {
		
		//sine of half and angle equals to the angle divided by 2 
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		//co-sine of half and angle equals to the angle divided by 2 
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
		
		//rX = the x on the axis times by the sin of half an angle
		float rX = axis.getX() * sinHalfAngle;
		//rY = the y on the axis times by the sin of half an angle
		float rY = axis.getY() * sinHalfAngle;
		//rZ = the z on the axis times by the sin of half an angle
		float rZ = axis.getZ() * sinHalfAngle;
		//rW = the co-sine of half an angle
		float rW = cosHalfAngle;
		
		//creates new Quaternion called rotation and takes in the rX,rY,rZ and rW
		Quaternion rotation = new Quaternion(rX,rY,rZ,rW);
		//turns all imaginary quaternion components to negative values
		Quaternion conjugate = rotation.conjugate(); 
		
		//w is the result of the rotation multiplied by this vector (rotation vector) multiplied by the conjugate ("cancels" out imaginary components from the quaternion)
		Quaternion w = rotation.multi(this).multi(conjugate);
		
		//sets x to the x component of w
		x = w.getX();
		//sets y to the y component of w
		y = w.getY();
		//sets z to the z component of w
		z = w.getZ();
		
		//returns this (Vector rotation using quaternions)
		return this;
	}

	// add function
	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}

	// add function
	public Vector3f add(float v) {
		return new Vector3f(x + v, y + v, z + v);
	}

	// sub function
	public Vector3f sub(Vector3f v) {
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}

	// sub function
	public Vector3f sub(float v) {
		return new Vector3f(x - v, y - v, z - v);
	}

	// divide function
	public Vector3f div(Vector3f v) {
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}

	// divide function
	public Vector3f div(float v) {
		return new Vector3f(x / v, y / v, z / v);
	}

	// multiply function
	public Vector3f multi(Vector3f v) {
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}

	// multiply function
	public Vector3f multi(float v) {
		return new Vector3f(x * v, y * v, z * v);
	}

	// gets x
	public float getX() {

		// returns x
		return x;
	}

	// sets x
	public void setX(float x) {

		// x = x
		this.x = x;
	}

	// gets y
	public float getY() {

		// returns x
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
}
