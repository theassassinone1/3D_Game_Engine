package com.base.engin;

public class Vertex {
	
	//variables
	public static final int SIZE = 5;
	private Vector2f texCoord;
	private Vector3f pos;
	
	
	//constructor (Vector3f pos)
	public Vertex(Vector3f pos){
		
		// this (some pos) + (a new Vector2f at coordinates(0,0))
		this(pos, new Vector2f(0,0));
	}
	
	//constructor (Vector3f pos ,Vector2f texCoord)
	public Vertex(Vector3f pos,Vector2f texCoord){
		
		//pos equals pos
		this.pos = pos;
		//texCoords (coordinates) equals texCords (coordinates)
		this.texCoord = texCoord;
	}
	
	//gets pos
	public Vector3f getPos() {
		//returns pos
		return pos;
	}
	
	//sets pos
	public void setPos(Vector3f pos) {
		//pos equals pos
		this.pos = pos;
	}

	//gets texture coordinates
	public Vector2f getTexCoord() {
		//returns texCoords (coordinates)
		return texCoord;
	}

	//sets texture coordinates
	public void setTexCoord(Vector2f texCoord) {
		//texCoords (coordinates) equals texCords (coordinates)
		this.texCoord = texCoord;
	}
}
