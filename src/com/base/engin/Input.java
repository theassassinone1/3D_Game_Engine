package com.base.engin;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	//static final int that set max number of key codes 
	public static final int NUM_KEYCODES = 256;
	//static final int that set max number of mouse buttons 
	public static final int NUM_MOUSEBUTTONS = 5;
	
	//creates an array list of integers called currentKeys
	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	//creates a down keys array list
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	//creates a up keys array list
	private static ArrayList<Integer> upKeys = new ArrayList<Integer>();
	
	//creates an array list of integers called currentMouse
	private static ArrayList<Integer> currentMouse = new ArrayList<Integer>();
	//creates a up mouse button array list
	private static ArrayList<Integer> mouseUp = new ArrayList<Integer>();
	//creates a down mouse button array list
	private static ArrayList<Integer> mouseDown = new ArrayList<Integer>();
	
	//update function
	public static void update(){
		
		//clears up keys array list
		upKeys.clear();
		
		//loop for every key and test if
		for(int i= 0; i < NUM_KEYCODES; i++){
			//a key is not up and a key was pressed in the last frame  
			if(!getKey(i) && currentKeys.contains(i)){
				//then add a key to the up keys array 
				upKeys.add(i);
					}
				}
		
		//clears down keys array list
		downKeys.clear();
		
		//loop for every key and test if
		for(int i= 0; i < NUM_KEYCODES; i++){
			//a key is down and a key was not pressed in the last frame 
			if(getKey(i) && !currentKeys.contains(i)){
				//then add a key to the down keys array 
				downKeys.add(i);
			}
		}
		
		//clears array list
		currentKeys.clear();
		
		//loop for every key and test if
		for(int i= 0; i < NUM_KEYCODES; i++){
			//a key is down 
			if(getKey(i)){
				//then add that key to the array current keys
				currentKeys.add(i);
			}
		}
	
		mouseUp.clear();
		
		//loop for every key and test if
		for(int i= 0; i < NUM_MOUSEBUTTONS; i++){
			//a key is down and a key was not pressed in the last frame 
			if(!getMouse(i) && currentMouse.contains(i)){
				//then add a key to the down keys array 
				mouseUp.add(i);
			}
		}
				
		mouseDown.clear();
				
		//loop for every key and test if
		for(int i= 0; i < NUM_MOUSEBUTTONS; i++){
			//a key is down and a key was not pressed in the last frame 
			if(getMouse(i) && !currentMouse.contains(i)){
				//then add a key to the down keys array 
				mouseDown.add(i);
			}
		}
		
		currentMouse.clear();
		
		//loop for every mouse button and test if
		for(int i= 0; i < NUM_MOUSEBUTTONS; i++){
			//a button is down 
			if(getMouse(i)){
				//then add that button to the current mouse array
				currentMouse.add(i);
			}
		}
		
	}
	
	//get key function
	public static boolean getKey(int keyCode){
		
		//returns a key code if a key is pressed
		return Keyboard.isKeyDown(keyCode);		
	}
	
	//key down function
	public static boolean getKeyDown(int keyCode){
		
		//returns down key array contains
		return downKeys.contains(keyCode);
		
	}
	
	//key up function 
	public static boolean getKeyUp(int keyCode){
		
		//returns up key array contains
		return upKeys.contains(keyCode);			
	}
	
	//get mouse button function
	public static boolean getMouse(int mouseButton){
		
		//returns a mouse button code if a mouse button is pressed
		return Mouse.isButtonDown(mouseButton);
	}
	
	//mouse button  down function
	public static boolean getMouseDown(int mouseButton){
		
		//returns mouse button down array contains
		return mouseDown.contains(mouseButton);
	}
	
	//mouse button on function
	public static boolean getMouseUp(int mouseButton){
		
		//returns mouse button up array contains
		return mouseUp.contains(mouseButton);
	}
	
	public static Vector2f getMousePosition(){
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
}