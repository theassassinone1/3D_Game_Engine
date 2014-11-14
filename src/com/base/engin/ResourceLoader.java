package com.base.engin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class ResourceLoader {
	
	public static String loadShaders(String fileName){
		
		//loads text
		StringBuilder shaderSource = new StringBuilder();
		
		//sets shader reader to be a buffered reader and sets it equal to null to start with 
		BufferedReader shaderReader = null;
		
		try{
			
			//initialises the shader reader
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
			
			//String 
			String line;
			
			//while the shader reader is reading the shader file
			while((line = shaderReader.readLine()) != null){
				//then append the line and start a new line 
				shaderSource.append(line).append("\n");
			}
			
			//closes the shader reader
			shaderReader.close();
		}catch(Exception e){
			//prints outs any errors
			e.printStackTrace();
			//exits the game if an error is found
			System.exit(1);
		}
		
		//returns shaderSource
		return shaderSource.toString();
	}
	
	public static Mesh loadMesh(String fileName){
		
		//looks of the fullstop with in the filename
		String[] splitArray = fileName.split("\\.");
		// fins the file extenuation of a file
		String ext = splitArray[splitArray.length -1];
		
		//if the file extenuation is not obj then
		if(!ext.equals("obj")){
			//print out an error
			System.err.println("File format error: File Format not supported for mesh data: " + ext);
			//print out the stack trace
			new Exception().printStackTrace();
			//exit game
			System.exit(1);
		}
		
		//array list of vertices
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		//array list of indices
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		//sets shader reader to be a buffered reader and sets it equal to null to start with 
		BufferedReader meshReader = null;
		
	try{
			
			//initialises the mesh reader
			meshReader = new BufferedReader(new FileReader("./res/models/" + fileName));
			
			//String 
			String line;
			
			//while the mesh reader is reading the mesh file
			while((line = meshReader.readLine()) != null){
				
				//string tokens looks of line spaces
				String[] tokens = line.split(" ");
				
				//removes empty strings
				tokens = Util.removeEmptyStrings(tokens);
				
				//if tokens equales and empty line or has a hash in it
				if(tokens.length == 0 || tokens[0].equals("#")){
					//keep running
					continue;
				
				//else if it has a v at the start then its a vertices
				}else if(tokens[0].equals("v")){
					//then load in the vertex (vertices) data 
					vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), 
														 Float.valueOf(tokens[2]),
														 Float.valueOf(tokens[3]))));
				//else if it has a f at the start then its a face
				}else if (tokens[0].equals("f")){
					//then load in the indices (face) data and subtract one to start from 0 and not 1
					indices.add(Integer.parseInt(tokens[1].split("/")[0]) -1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0]) -1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0]) -1);
				
					if(tokens.length > 4){
					
					indices.add(Integer.parseInt(tokens[1].split("/")[0]) -1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0]) -1);
					indices.add(Integer.parseInt(tokens[4].split("/")[0]) -1);
				}
			}
		}
			
			//closes the mesh reader
			meshReader.close();
			
			//creates new Mesh called res
			Mesh res = new Mesh();
			
			//creates vertex array list called vertex data 
			Vertex[] vertexData = new Vertex[vertices.size()];
			//converts array list into an array 
			vertices.toArray(vertexData);
			
			//creates integer array list called index data 
			Integer[] indexData = new Integer[indices.size()];
			//converts array list into an array 
			indices.toArray(indexData);
			
			//adds Vertices data and converted index data to vertices
			res.addVertices(vertexData, Util.toIntArray(indexData));
			
			//returns res
			return res;
	}catch(Exception e){
			
		//prints outs any errors
		e.printStackTrace();
		//exits the game if an error is found
		System.exit(1);
		
	}
		
		//returns nothing
		return null;
		
	}
}
