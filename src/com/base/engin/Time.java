package com.base.engin;

public class Time {
	
	//seconds variable (long = 1000000000L)
	public static final long SECOND = 1000000000L;
	
	//delta variable 
	private static double delta;
	
	//gets time
	public static long getTime(){
		
		//gets nano time
		return System.nanoTime();
	}
	
	//gets delta
	public static double getDelta(){
		
		//returns delta
		return delta;		
	}
	
	//sets delta
	public static void setDelta(double delta){
		
		//sets delta to equal delta
		Time.delta =  delta;
	}
}
