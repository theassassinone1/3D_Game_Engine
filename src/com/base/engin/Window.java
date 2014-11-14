package com.base.engin;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	//creates window
	public static void creteWindow(int width, int height, String title) {
		
		//sets title
		Display.setTitle(title);

		try {
			//sets displaymode (sets width, height)
			Display.setDisplayMode(new DisplayMode(width, height));
			//creates the display
			Display.create();
			
			//inits keyboard
			Keyboard.create();
			//inits mouse
			Mouse.create();
			
		} catch (LWJGLException e) {

			e.printStackTrace();
		}
	}
	
	//renders everything
	public static void render(){
		
		//updates the display
		Display.update();
	}
	
	//destroys the display (clean up), keyboard and mouse
	public static void Dispose(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	//boolean is close requested ?
	public static boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	//gets window width
	public static int getWidth(){
		
		//returns display width
		return Display.getDisplayMode().getWidth();
		}
	
	//gets window height
	public static int getHeight(){
		
		//returns display height
		return Display.getDisplayMode().getHeight();
		}
	
	//gets display title
	public static String gettitle(){
		
		//returns the display title
		return Display.getTitle();
	}
}
