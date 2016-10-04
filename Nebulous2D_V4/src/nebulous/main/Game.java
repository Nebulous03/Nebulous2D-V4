package nebulous.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nebulous.graphics.RenderEngine;
import nebulous.graphics.Window;
import nebulous.logic.Input;
import nebulous.logic.level.Level;
import nebulous.logic.Input;
import nebulous.utils.Console;
import nebulous.utils.Time;

public abstract class Game{
	
	private Map<String, Level> levels;
	private ArrayList<String> tags;
	private Level activeLevel = null;
	private int levelSize = 0;
	private String title = "Nebulous2D Game Engine";
	private int width = 1366;
	private int height = 768;
	public Window window = null;
	
	private double updateSpeed = 1.0 / 60;
	
	public Game(){
		this.window = createWindow();
		this.levels = new HashMap<String, Level>();
		this.tags = new ArrayList<String>();
		new RenderEngine(this);
		new Input();
	}
	
	public Game(int width, int height, String title) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.window = createWindow();
		this.levels = new HashMap<String, Level>();
		this.tags = new ArrayList<String>();
		new RenderEngine(this);
		new Input();
	}
	
	public void start(){
		window.init();
		RenderEngine.init();
		Input.init(window);
		init();
		tick();
	}
	
	public void stop(){
		glfwTerminate();
		Console.println("Game exited with game.stop()");
		System.exit(0);
	}
	
	private void tick(){
		int frames = 0;
        double frameCounter = 0;
		
		double lastTime = Time.getTime();
		double unprocessedTime = 0;

		while(!glfwWindowShouldClose(window.getWindowID())){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			double startTime = Time.getTime();
            double pastTime = startTime - lastTime;
			lastTime = startTime;
			unprocessedTime += pastTime;
			frameCounter += pastTime;
			
			while(unprocessedTime > updateSpeed){
				unprocessedTime -= updateSpeed;
				
				update();
				Input.update();
				if(activeLevel != null){
					activeLevel.update(0); // TODO: DELTA!!!
					activeLevel.updateObjects();
				} RenderEngine.update();
				
				if(frameCounter >= 1.0){
					Console.println("FPS: " + frames);
					frameCounter = 0;
					frames = 0;
				}
			}
			
			//render();
			if(activeLevel != null)activeLevel.render();
			window.render();
			frames++;
			
		}
	}

	public abstract void init();
	
	public abstract void update();
	
	private Window createWindow(){
		Window window = new Window().createWindow(width, height, title);
		window.setVisable(true);
		return window;
	}
	
	public String getTitle() {
		return title;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Window getWindow() {
		return window;
	}

	public double getFramecap() {
		return updateSpeed;
	}
	
	public void addLevel(String tag, Level level){
		levels.put(tag, level);
		tags.add(tag);
		level.init();
		levelSize++;
	}

	public Level getActiveLevel() {
		return activeLevel;
	}
	
	public void setActiveLevel(String tag) {
		this.activeLevel = levels.get(tag);
	}

	public void setActiveLevel(Level level) {
		this.activeLevel = level;
	}

}
