package com.base.engin;

public class Main {

	// final static variables (window width, height and title)
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "3DEngin";
	
	//frame cap 
	public static final double FRAME_CAP = 5000.0;
	
	//boolean that tells us if the game is running or not 
	private boolean isRunning;
	
	//give the main class a private instance of the game class
	private Game game;
	
	//main constructor (engine) 
	public Main() {
		
		//prints of opengl version
		System.out.println(RenderUtil.getOpenGLVersion());
		
		//inits RenderUtil
		RenderUtil.initGraphics();
		
		//set is running boolean to false 
		isRunning = false;
		
		//inits game 
		game = new Game();
	}
	
	//start function (starts the game) 
	public void Start() {

		// if is running (so if the game is running then just return (no problem))
		if (isRunning) {
			return;
		}
		
		//calls the run function 
		Run();
	}
	
	//Stop function
	public void Stop() {
		
		// if the game is not running then just return 
		if (!isRunning) {
			return;
		}
		
		//set is running to false (stopping the game)
		isRunning = false;
	}
	
	
	//run function
	private void Run() {
		
		//sets is running to true (starts the game)
		isRunning = true;
		
		//set int frames to 0
		int frames = 0;
		//set long frame counter to 0
		long frameCounter = 0;
		
		//frame time is equal to 1/ frame cap (5000.0)
		final double frameTime = 1.0/ FRAME_CAP;
		
		//gets how long the las frame took to render
		long lastTime = Time.getTime() ;
		
		//Unprocessed time (tracks frames that need possessing) 
		double unprocessedTime = 0;
		
		//while is running and close not requested then render
		while (isRunning) {
			
			boolean render = false;
			
			//set start time to time
			long startTime = Time.getTime();
			//set passedTime (fame render time) to the start time minus the last time (the amount of time it took to render the last frame)
			long passedTime = startTime - lastTime;
			//sets last time to equal start time (ready to calculate next frame render time)
			lastTime = startTime;
			
			//get how much time as passed that still needs to be processed in the form of a double
			unprocessedTime += passedTime/ (double)Time.SECOND;
			
			//set frameCounter long to passedTime 
			frameCounter += passedTime;
			
			//while unproTime is grater than frameTime 
			while(unprocessedTime > frameTime){
				
				//set render boolean to equal true
				render = true;
				
				//unproTime minus equals frameTime
				unprocessedTime -= frameTime;
				
				// if close is requested then run stop function
				if (Window.isCloseRequested()) {
					Stop();
				}
				
				//when game is updated set delta time 
				Time.setDelta(frameTime);
				//updates input
				Input.update();
				
				
				//takes into account changes in input and updates the game 
				game.Input();
				game.Update();
				
				//if frame count is grater than or equal to a second then
				if(frameCounter >= Time.SECOND){
					//prints out FPS (fames value)
					System.out.println("FPS:"+frames);
					//resets frames and frameCount
					frames = 0;
					frameCounter = 0;
				}
			}
			
			
			//if render is needed (= true) then: 
			if(render){
			
			//call render function while is running	
			Render();
			
			//increases the frames
			frames ++;
			}else{
				try {
					//sleeps if render is not needed (above)
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			//calls clean up function to clean up after 
			CleanUp();
	}
	
	//render function
	private void Render() {
		
		//calls clear screen method from renderUtil class
		RenderUtil.clearScreen();
		
		//renders the render in the game class 
		game.Render();
		
		//renders window (items inside the window)
		Window.render();

	}
	
	//clean up function
	private void CleanUp() {
		
		//runs window dispose function to get rid of the display
		Window.Dispose();
	}

	//main function
	public static void main(String[] args) {

		// creates window
		Window.creteWindow(WIDTH, HEIGHT, TITLE);
		
		//new main function (game)
		Main Game = new Main();
		
		//starts the game
		Game.Start();
	}
}
