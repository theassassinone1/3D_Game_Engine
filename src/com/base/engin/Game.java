package com.base.engin;

import org.lwjgl.input.Keyboard;

public class Game {
	
	//declarations
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	public Game(){
		
		//mesh equals to a new mesh
		mesh = ResourceLoader.loadMesh("cube.obj");//new Mesh();
		
		/*//the vertices of that new mesh (creates a triange)
		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f( -1, -1, 0)),
									  new Vertex(new Vector3f( 0, 1, 0)),
									  new Vertex(new Vector3f( 1, -1, 0)),
									  new Vertex(new Vector3f(0,-1,1))};
		
		//integer array called indices
		int[] indices = new int[]{0,1,3,
								  3,1,2,
								  2,1,0,
								  0,3,2};
		
		//adds the vertices
		mesh.addVertices(vertices, indices);*/
		
		//shader equals new shader
		shader = new Shader();
		
		//adds the vertex shader and loads which file is given
		shader.addVertexShader(ResourceLoader.loadShaders("basicVertex.vs"));
		//adds the fragment shader and loads which file is given
		shader.addFragmentShader(ResourceLoader.loadShaders("basicFragment.fs"));
		//compiles the shader
		shader.CompileShader();
		
		//transform equals new transform
		transform = new Transform();
		
		transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		
		//adds uniform to the shader
		shader.addUniform("transform");
		
	}
	
	// input function
	public void Input(){
		if(Input.getKeyDown(Keyboard.KEY_W)){
			System.out.println("W is down");
		}
		if(Input.getKeyUp(Keyboard.KEY_W)){
			System.out.println("W is up");
		}
		if(Input.getMouseDown(0)){
			System.out.println("left mouse button is down at" + Input.getMousePosition().toString());
		}
		if(Input.getMouseUp(0)){
			System.out.println("left mouse button is up");
		}
	}
	
	float temp = 0.0f;
	
	// update function
	public void Update(){
		
		temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		//sets translation
		transform.setTranslation(sinTemp, 0, 5);
		//sets rotation
		transform.setRotation(0, sinTemp * 180, sinTemp * 180);
		//sets scale
		transform.setScale(0.7f * sinTemp,0.7f * sinTemp,0.7f * sinTemp);
	}
	
	// render function
	public void Render(){
		
		//binds the shader
		shader.bind();
		//set the shader uniform
		shader.setUniform("transform", transform.getProjectedTransformation());
		//draws a mesh
		mesh.draw();
	}
}
