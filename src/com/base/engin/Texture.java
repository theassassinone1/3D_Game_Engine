package com.base.engin;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	//variables
	private int id;
	
	//constructor (int id)
	public Texture(int id){
		// id equals id
		this.id = id;
	}
	
	//binds the texture
	public void bind(){
		
		//binds texture (texture type (2D) texture (id/name))
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	//gets the id of the texture
	public int getID(){
		//returns the id
		return id;
	}
}
