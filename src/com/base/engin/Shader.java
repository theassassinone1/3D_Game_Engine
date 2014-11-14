package com.base.engin;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import java.util.HashMap;

public class Shader {
	
	//variables
	public int program;
	private HashMap <String, Integer> uniforms;
	
	public Shader(){
		
		//inits shader program
		program = glCreateProgram();
		//inits uniforms hash map
		uniforms = new HashMap<String, Integer>();
		
		// if the program is equal to 0 then an error has occurred 
		if(program == 0){
			// if the program can not be created then print out this error
			System.err.println("Shader creation error (no valid memory location)");
			
			//exit the game
			System.exit(1);
		}
	}
	
	//binds the shader
	public void bind(){
		
		//start using the shader program
		glUseProgram(program);
	}
	
	//allows you to set uniform locations for the shades
	public void addUniform(String uniform){
		
		//Returns the location of a uniform variable called uniform Location
		int uniformLocation = glGetUniformLocation(program, uniform);
		
		//if location is invalid then
		if (uniformLocation == 0xFFFFFFFF){
			//print out an error
			System.err.println("Uniform location error (could not find uniform: )" + uniform);
			//creates an exception and prints out stack trace
			new Exception().printStackTrace();
			//exit the game
			System.exit(1);
		}
		
		//maps a key (string) to a location with in the hash map (creates new uniform in the hash map)
		uniforms.put(uniform, uniformLocation);
	
	}
	
	//adds to the vertex shader
	public void addVertexShader(String text){
		
		//calls program private method
		addProgram(text,GL_VERTEX_SHADER);
	}
	
	//adds to the geometry shader
	public void addGeometryShader(String text){
		
		//calls program private method
		addProgram(text,GL_GEOMETRY_SHADER);
	}
	
	//adds to the fragment shader 
	public void addFragmentShader(String text){
		
		//calls program private method
		addProgram(text,GL_FRAGMENT_SHADER);
	}
	
	//takes all the shader parts (fragment, vertex, geometry) and complies then into one shader program
	public void CompileShader(){
		
		//links the program object to the program variable (program = program)
		glLinkProgram(program);
	
		//check to see if the program has compiled correctly
		if(glGetShaderi(program, GL_LINK_STATUS) == 0){
			
			//prints out the error with the compiler failing
			System.err.println(glGetShaderInfoLog(program, 1024));
					
			//exit the game
			System.exit(1);
		}
		
		//validation check
		glValidateProgram(program);
		
		//check to see if the program has fully compiled correctly
				if(glGetShaderi(program, GL_VALIDATE_STATUS) == 0){
					
					//prints out the error with the compiler failing
					System.err.println(glGetShaderInfoLog(program, 1024));
							
					//exit the game
					System.exit(1);
				}
	}
	
	//adds the shades to the program
	private void addProgram(String text, int type){
		
		//variable (pointer to store all the code)
		int shader = glCreateShader(type);
		
		//if shader equals 0 then an error has occurred
		if(shader == 0){
			
			// prints out and error if shader creation has failed because of missing location when adding a shader
			System.err.println("Shader creation error (no valid memory location when adding shader)");
			
			//exit the game 
			System.exit(1);
		}
		
		//gets the source of the shader and link it with the text 
		glShaderSource(shader, text);
		
		//compiles the shader
		glCompileShader(shader);
	
		
		//check to see if the code has compiled correctly
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
			
			//prints out the error with the compiler failing
			System.err.println(glGetShaderInfoLog(shader, 1024));
			
			//exit the game
			System.exit(1);
		}
		
		//attaches the shader and shader compiler to the program
		glAttachShader (program, shader);
	}
	
	//sets uniform integers
	public void setUniformi(String uniformName, int value){
		
		//get integer location based off uniform name
		glUniform1i(uniforms.get(uniformName), value);
	}
	
	//sets uniform floats
	public void setUniformf(String uniformName, float value){
			
		//get float location based off uniform name
		glUniform1f(uniforms.get(uniformName), value);
	}
		
	//sets uniform vector 3f points
	public void setUniform(String uniformName, Vector3f value){
			
		//get integer location based off uniform name
		glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
	}
	
	//sets uniform matrices
	public void setUniform(String uniformName, Matrix4f value){
			
		//get integer location based off uniform name
		glUniformMatrix4(uniforms.get(uniformName),true, Util.createFlippedBuffer(value));
	}
}
