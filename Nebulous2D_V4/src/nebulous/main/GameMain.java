package nebulous.main;

import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;

public class GameMain extends Game{

	private static final int width = 1366;
	private static final int height = 768;
	private static final String title = "Nebulous2D Game Engine";

	public static GameMain game = new GameMain();
	
	public Mesh mesh;
	public Model model;
	
	public static void main(String[] args) {
		game.getWindow().vSync(false);
		//game.getWindow().setVideoMode(VideoMode.FULLSCREEN, 1920, 1080);
		game.start();
	}
	
	public GameMain() {
		super(width, height, title); // TODO: Fix this
	}

	@Override
	public void init() {
		
		float[] vertices = new float[]{
				-0.5f,  0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			     0.5f, -0.5f, 0f,
			     0.5f,  0.5f, 0f,
	        };
		 
		int[] indices = new int[]{
			        0, 1, 3, 3, 1, 2,
			};

		float[] textureCoords = new float[]{
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		mesh = new Mesh(vertices, indices, textureCoords);
		model = new Model(mesh, new Texture("/textures/unknown.png"));
		//model.setCustomShader(new Shader("/shaders/white.vs", "/shaders/white.fs"));
		
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render() {
	    model.render();
	}

}
