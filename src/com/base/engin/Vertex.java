package com.base.engin;

public class Vertex {
	
	//variables
	public static final int SIZE = 3;
	private Vector3f pos;
	
	//constructor
	public Vertex(Vector3f pos){
		//pos = pos
		this.pos = pos;
	}
	
	
	//gets pos
	public Vector3f getPos() {
		//returns pos
		return pos;
	}
	
	//sets pos
	public void setPos(Vector3f pos) {
		//pos = pos
		this.pos = pos;
	}
}
