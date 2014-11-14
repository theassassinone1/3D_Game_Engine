package com.base.engin;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtil {
	
	//clears screen
	public static void clearScreen(){
		
		//TODO Stencil Buffer
		
		//clears colour buffer and depth buffer
		glClear(GL_COLOR_BUFFER_BIT| GL_DEPTH_BUFFER_BIT);
	}
	
	//inits openGL
	public static void initGraphics(){
		
		//clears colour/ set all pixels to black 
		glClearColor(0.0f , 0.0f , 0.0f , 0.0f);
		
		//front face = every face drawn in clock wise order is the front face of the object being drawn
		glFrontFace(GL_CW);
		
		//cullface = the back face of the object 
		glCullFace(GL_BACK);
		
		//gets rid of second rendered face (can be re-enabled manually)
		glEnable(GL_CULL_FACE);
		
		//tests depth from last pixel drawn (draw ordering)
		glEnable(GL_DEPTH_TEST);
		
		//TODO Depth Clamp
		
		//gamma correction	
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	//String Method (gets opengl version)
	public static String getOpenGLVersion(){
		
		//returns open GL version
		return glGetString(GL_VERSION);
	}
	
}
