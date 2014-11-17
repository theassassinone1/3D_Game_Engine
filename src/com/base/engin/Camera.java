package com.base.engin;

import org.lwjgl.input.Keyboard;

public class Camera {
	
	//variables
	public static final Vector3f yAxis = new Vector3f(0,1,0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	//constructor
	public Camera(){
		//creates new vector3f (start camera)
		this(new Vector3f (0,0,0), new Vector3f(0,0,1), new Vector3f (0,1,0));
	}
	
	//constructor (V3f pos, V3f forward, V3f up)
	public Camera(Vector3f pos, Vector3f forward, Vector3f up){
		
		//pos equals pos
		this.pos = pos;
		//forward equals forward
		this.forward = forward;
		//up equals up
		this.up = up;
		
		//normalises the up variable 
		up.normalise();
		//normalises the foward variable
		forward.normalise();
	}
	
	//input method
	public void input(){
		
		float movAmt = (float)(10*Time.getDelta());
		float rotAmt = (float)(100*Time.getDelta());
		
		if(Input.getKey(Keyboard.KEY_W)){
			move(getForward(),movAmt);
		}
		if(Input.getKey(Keyboard.KEY_A)){
			move(getLeft(),movAmt);
		}
		if(Input.getKey(Keyboard.KEY_S)){
			move(getForward(),-movAmt);
		}
		if(Input.getKey(Keyboard.KEY_D)){
			move(getRight(),movAmt);
		}
		
		if(Input.getKey(Keyboard.KEY_UP)){
			rotateX(-rotAmt);
		}
		if(Input.getKey(Keyboard.KEY_DOWN)){
			rotateX(rotAmt);
		}
		if(Input.getKey(Keyboard.KEY_LEFT)){
			rotateY(-rotAmt);
		}
		if(Input.getKey(Keyboard.KEY_RIGHT)){
			rotateY(rotAmt);
		}
	}
	
	//camera movement
	public void move(Vector3f dir, float amount){
		
		//adds the direction times the amount to the pos variable
		pos = pos.add(dir.multi(amount));
	}
	
	//camera rotation x
	public void rotateX(float angle){
		
		//calculates horizontal axis (cross product of the y axis with the forward variable)
		Vector3f Haxis = yAxis.cross(forward);
		//normalise horizontal axis
		Haxis.normalise();
		
		//forward rotates around the horizontal axis
		forward.rotate(angle,Haxis);
		//normalise forward
		forward.normalise();
		
		//calculates new up value by crossing the forward variable with the horizontal axis
		up = forward.cross(Haxis);
		//normalise up
		up.normalise();
	}
	
	//camera rotation y
	public void rotateY(float angle){
		
		//calculates horizontal axis (cross product of the y axis with the forward variable)
		Vector3f Haxis = yAxis.cross(forward);
		//normalise horizontal axis
		Haxis.normalise();
	
		//forward rotates around the y axis
		forward.rotate(angle, yAxis);
		//normalise forward
		forward.normalise();
		
		//calculates new up value by crossing the forward variable with the horizontal axis
		up = forward.cross(Haxis);
		//normalise up
		up.normalise();
	}
	
	//gets left
	public Vector3f getLeft(){
		
		//Vecotor3f called left equals the cross product of forward and up (gets left value)
		Vector3f left = forward.cross(up);
		//normalises left
		left.normalise();
		//returns left
		return left;
	}
	
	//gets right
	public Vector3f getRight(){
		//Vecotr3f called right equals the cross product of up and forward (get right value)
		Vector3f right = up.cross(forward);
		//normailes right
		right.normalise();
		//returns right
		return right;
	}
	
	//gets pos
	public Vector3f getPos() {
		//retruns pos
		return pos;
	}
	
	//sets pos
	public void setPos(Vector3f pos) {
		//pos equals pos
		this.pos = pos;
	}
	
	//gets forward
	public Vector3f getForward() {
		//returns forward
		return forward;
	}
	
	//sets forward
	public void setForward(Vector3f forward) {
		//forward equals forward
		this.forward = forward;
	}
	
	//gets up
	public Vector3f getUp() {
		//returns up
		return up;
	}
	
	//sets up 
	public void setUp(Vector3f up) {
		//up equals up
		this.up = up;
	}
}
