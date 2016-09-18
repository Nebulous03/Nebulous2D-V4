package nebulous.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import nebulous.graphics.Window;

public abstract class Game {
	
	private String title = "Nebulous2D Game Engine";
	private int width = 1366;
	private int height = 768;
	private Window window = null;

	public Game(int width, int height, String title) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.window = createWindow();
	}
	
	public void start(){
		tick();
	}
	
	public void stop(){
		glfwTerminate();
	}
	
	private void tick(){
		init();
		while(!glfwWindowShouldClose(window.getWindowID())){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			update();
			render();
			
			glfwSwapBuffers(window.getWindowID());
			glfwPollEvents();
		}
	}
	
	public abstract void init();
	
	public abstract void update();
	
	public abstract void render();
	
	private Window createWindow(){
		Window window = new Window().createWindow(width, height, title);
		window.setVisable(true);
		return window;
	}

}