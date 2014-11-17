package com.base.engin;


public class Game {
	
	//declarations
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Texture texture;
	
	public Game(){
		
		//mesh equals to a mesh loaded from the resource loader
		mesh = new Mesh();/* ResourceLoader.loadMesh("cube.obj");*/
		//loads texture
		texture = ResourceLoader.loadTexture("gridtex.png");
		//shader equals new shader
		shader = new Shader();
		//transform equals new transform
		transform = new Transform();
		//camera equals new camera
		camera = new Camera();
		
		
		Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0),new Vector2f(0,0)),
										  new Vertex(new Vector3f(0,1,0),new Vector2f(0.5f,0)),
										  new Vertex(new Vector3f(1,-1,0),new Vector2f(1.0f,0)),
										  new Vertex(new Vector3f(0,-1,1),new Vector2f(0, 0.5f))};
		
		int[] indices = new int[]{3,1,0,
								  2,1,3,
								  0,1,2,
								  0,2,3};
		
		mesh.addVertices(vertices, indices);
		
		//adds the vertex shader and loads which file is given
		shader.addVertexShader(ResourceLoader.loadShaders("basicVertex.vs"));
		//adds the fragment shader and loads which file is given
		shader.addFragmentShader(ResourceLoader.loadShaders("basicFragment.fs"));
		//compiles the shader
		shader.CompileShader();
		
		//sets projection
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		
		//adds uniform to the shader
		shader.addUniform("transform");
		
	}
	
	// input function
	public void Input(){
		
		camera.input();
	}
	
	float temp = 0.0f;
	
	// update function
	public void Update(){
		
		temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		//sets translation
		transform.setTranslation(sinTemp, 0, 5);
		//sets rotation
		transform.setRotation(0,sinTemp*180,0);
		//sets scale
		//transform.setScale(0.7f *sinTemp,0.7f * sinTemp,0.7f * sinTemp);
	}
	
	// render function
	public void Render(){
		
		//binds the shader
		shader.bind();
		//set the shader uniform
		shader.setUniform("transform", transform.getProjectedTransformation());
		//binds the texture
		texture.bind();
		//draws a mesh
		mesh.draw();
		
	}
}
