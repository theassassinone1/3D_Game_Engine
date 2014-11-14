package com.base.engin;

public class Vector2f {
	
	//float x and y variable 
	private float x;
	private float y;
	
	////Constructor  (takes x and y floats)
	public Vector2f(float x, float y) {
		
		//x = x
		this.x = x;
		//y = y
		this.y = y;
	}
	
	//length function (gets the length of the vector)
	public float length(){
		//Square root 
		return (float)Math.sqrt(x*x+y*y);
	}
	
	//multiplication between two vectors that takes into account the direction of the vector 
	public float dot(Vector2f v){
		//returns the x value of a vector time by x plus the y time by the y
		return x * v.getX() + y	* v.getY();
	}
	
	//takes the length and sets it to one (length = length/length = 1)
	public Vector2f normalise(){
		
		//calls the length
		float length = length();
		
		//set overall vector length to one
		x /= length;
		y/= length;
		
		//returns this vector
		return this;
		
	}
	
	//vector rotate function
	public Vector2f rotate(float angle){
		
		//radius
		double rad = Math.toRadians(angle);
		//co-sine
		double cos = Math.cos(rad);
		//sine of angle
		double sin = Math.sin(rad);
		
		return new Vector2f ((float)(x * cos - y * sin),(float)(x * sin + y * cos));
	}
	
	//add function
	public Vector2f add(Vector2f v){
		return new Vector2f(x + v.getX(), y + v.getY());
	}
	
	//add function
	public Vector2f add(float v){
		return new Vector2f(x + v, y + v);
	}
	
	//subtract function
	public Vector2f sub(Vector2f v){
		return new Vector2f(x - v.getX(), y - v.getY());
	}
	
	//subtract function
	public Vector2f sub(float v){
		return new Vector2f(x - v, y - v);
	}
	
	//divide function
	public Vector2f div(Vector2f v){
		return new Vector2f(x / v.getX(), y / v.getY());
	}
	
	//divide function
	public Vector2f div(float v){
		return new Vector2f(x / v, y / v);
	}
	
	//multiply function
	public Vector2f multi(Vector2f v){
		return new Vector2f(x * v.getX(), y * v.getY());
	}
	
	//multiply function
	public Vector2f multi(float v){
		return new Vector2f(x * v, y * v);
	}
	
	//to string function (returns a string)
	public String  toString(){
		
		return "(" + x + "," + y + ")";
	}
	
	//gets x
	public float getX(){
		
		//returns x
		return x;
	}
	
	//sets x
	public void setX(float x){
		//x = x
		this.x = x;
	}
	
	//gets y
	public float getY(){
		
		//returns x
		return y;
	}
	
	//set y
	public void setY(float y){
		
		//y = y 
		this.y = y;
	}
}
