package com.base.engin;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Util {
	
	//creates a FloatBuffer (int size)
	public static FloatBuffer createFloatBuffer(int size){
		
		//returns createFloatBuffer
		return BufferUtils.createFloatBuffer(size);
		
	}
	
	//creates a IntegerBuffer (int size)
	public static IntBuffer createIntegerBuffer(int size){
			
		//returns createFloatBuffer
		return BufferUtils.createIntBuffer(size);
			
	}
	
	//flips all the java data (vertex) into the correct position of openGL to read (links with mesh class and vertex class)
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
		
		//creates a float buffer (size equals the total number of vertices and times by how big one vertex is)
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
	
		//adds data to the buffer
		for(int i=0; i < vertices.length; i++){
			
			//puts the x vertices and position data into the buffer 
			buffer.put(vertices[i].getPos().getX());
			//puts the y vertices and position data into the buffer 
			buffer.put(vertices[i].getPos().getY());
			//puts the z vertices and position data into the buffer 
			buffer.put(vertices[i].getPos().getZ());
		}
		
		//puts the buffer into the proper order so it can be read (flips it)
		buffer.flip();
		
		
		//returns the buffer
		return buffer;
	}
	
	//flips all the java data (matrices) into the correct position of openGL to read (links with shader class and matrix4f class)
	public static FloatBuffer  createFlippedBuffer(Matrix4f value){
		
		//creates a float buffer (size equals the size of the matrices)
		FloatBuffer buffer = createFloatBuffer (4 * 4);
		
		//adds data to the buffer
		for(int i=0; i < 4; i++){
			for (int j=0; j <4; j++){
				
				//puts the value of whatever i and j are into the buffer
				buffer.put(value.get(i, j));
			}
		}
		
		//puts the buffer into the proper order so it can be read (flips it)
		buffer.flip();
		
		//returns the buffer
		return buffer;
	}
	
	//flips all the java data (Intagers used in the ibo mesh class) into the correct position of openGL to read (links with mesh class)
	public static IntBuffer createFlippedBuffer(int... values){
		
		//creates a integer buffer called buffer
		IntBuffer buffer = createIntegerBuffer(values.length);
		
		//puts all data into the buffer
		buffer.put(values);
		
		//puts the buffer into the proper order so it can be read (flips it) 
		buffer.flip();
		
		return buffer;
	}
	
	//remove empty strings method
	public static String[] removeEmptyStrings(String[] data){
		
		//array list of results (all the not empty strings)
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < data.length; i++){
			//if the data is not empty 
			if(!data[i].equals("")){
				//then add it to the results string
				result.add(data[i]);
			}
		}
			//creates new string called res
			String[] res = new String[result.size()];
			
			//fills string array with results
			result.toArray(res);
			
			//returns res (results)
			return res;
	}
	
	//method to convert integer array in to int array
	public static int[] toIntArray(Integer[] data){
		
		//creates new int array called result
		int[] result = new int[data.length];
		
		for(int i =0; i < data.length;i++){
			result[i] = data[i].intValue();
		}
		
		return result;
	}
}
