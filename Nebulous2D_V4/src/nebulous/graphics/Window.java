package nebulous.graphics;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.Version;
import static org.lwjgl.opengl.GL11.*;

import nebulous.utils.Console;

public class Window {
	
	private String TITLE;
	private int WIDTH, HEIGHT;
	private long windowID = 0;

	public Window() {}
	
	public Window createWindow(int width, int height, String title){
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		initGLWindow();
		printGLStats();
		return this;
	}
	
	private void initGLWindow(){
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (glfwInit()){
			Console.println("GLFWInit() completed successfully...");
		} else {
			Console.printErr("GLFWInit() failed to complete successfully!");
			throw new IllegalStateException("ERROR: Unable to initialize GLFW!");
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		
		windowID = glfwCreateWindow(WIDTH, HEIGHT, TITLE, 0, 0);
		if(windowID == 0){ throw new RuntimeException("ERROR: Failed to create the GLFW window!");}
		
		glfwMakeContextCurrent(windowID);
		glfwSwapInterval(1);
		
		GL.createCapabilities();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public void setVisable(boolean visable){
		if(visable) glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		else glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
	}
	
	public void setResizable(boolean resizable){
		if(resizable) glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		else glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
	}
	
	public static void printGLStats(){
        Console.printMOTD(
        " OPENGL: " + glGetString(GL_VERSION) + "\n" +
        " LWJGL: " + Version.getVersion() + "\n" + 
        " GRAPHICS: " + glGetString(GL_RENDERER) + "\n" +
        " VENDORS: " + glGetString(GL_VENDOR) + "\n" +
        " OPERATING SYSTEM: " + System.getProperty("os.name") + "\n" +
        " JAVA VERSION: " + System.getProperty("java.version") + "\n" +
        " CURRENT DIRECTORY: \n" +
        " " + System.getProperty("user.dir")
        );
    }

	public String getTitle() {
		return TITLE;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public long getWindowID() {
		return windowID;
	}

}