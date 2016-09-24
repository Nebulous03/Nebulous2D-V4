package nebulous.main;

import nebulous.graphics.Mesh;

public class GameMain extends Game{

	private static final int width = 1366;
	private static final int height = 768;
	private static final String title = "Nebulous2D Game Engine";

	public static GameMain game = new GameMain();
	
	public Mesh mesh;
	public Mesh mesh2;
	
	public static void main(String[] args) {
		game.getWindow().vSync(false);
		//game.getWindow().setVideoMode(VideoMode.NORMAL, 1920, 1080);
		game.start();
	}
	
	public GameMain() {
		super(width, height, title); // TODO: Fix this
	}

	@Override
	public void init() {
		
		float[] vertices = new float[]{
			        -0.5f,  0.5f, 0.0f,
			        -0.5f, -0.5f, 0.0f,
			         0.5f, -0.5f, 0.0f,
			         0.5f,  0.5f, 0.0f,
			};
		 
		int[] indices = new int[]{
			        0, 1, 3, 3, 1, 2,
			};
		 
		float[] colors = new float[]{
				    1f, 0.0f, 0.0f,
				    0.0f, 1f, 0.0f,
				    0.0f, 0.0f, 1f,
				    0.0f, 1f, 1f,
			};
		mesh = new Mesh(vertices, indices, colors);
		//mesh.setCustomShader(new Shader("/shaders/white.vs", "/shaders/white.fs"));
		
		
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render() {
	    mesh.render();
	    mesh2.render();
	}

}
