package com.base.engin;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
	
	//variables
	private int vbo;
	private int ibo;
	private int size;
	
	//constructor
	public Mesh(){
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		size = 0;
	}
	
	//add Vertices method
	public void addVertices(Vertex[] vertices, int[] indices){
		
		//size equals the total number of vertices and times by how big one vertex is (total size of data stored in the vbo variable)
		size = indices.length; 
		// (add back later)* Vertex.SIZE;
		
		//binds a buffer object to a buffer binding point (vob)
		glBindBuffer(GL_ARRAY_BUFFER,vbo);
		//creates and initialises the buffer objects data store (GL_STATIC_DRAW tell opengl what kind of data its going to get (static data) )
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
	
		//binds a buffer object to a buffer binding point (ibo)
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		//creates and initialises the buffer objects data store (GL_STATIC_DRAW tell opengl what kind of data its going to get (static data) )
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
	}
	
	//draw method
	public void draw(){
		
		//enables VertexAttribArray (divides data into different segments 1,2,3,4 and so on)
		glEnableVertexAttribArray(0);
		//enables VertexAttribArray (divides data into different segments 1,2,3,4 and so on)
		glEnableVertexAttribArray(1);
		
		//binds a buffer object to a buffer binding point 
		glBindBuffer(GL_ARRAY_BUFFER,vbo);
		
		//how to interpret the data (starts at segment 0 , there are 3 elements in segment 0 , these elements are floats, normalise = false, how big is one vertex, where in each vertex douse this data start)
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		//how to interpret the data (starts at segment 0 , there are 2 elements in segment 0 , these elements are floats, normalise = false, how big is one vertex, where in each vertex douse this data start)
		glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
		
		//binds the element array buffer to ibo with in the draw method
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		//draws elements with in vbo with the ibo
		glDrawElements(GL_TRIANGLES,size, GL_UNSIGNED_INT, 0);
		
		//disables VertexAttribArrays
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}
}
