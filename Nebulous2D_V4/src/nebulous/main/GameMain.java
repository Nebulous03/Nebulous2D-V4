package nebulous.main;

public class GameMain extends Game{

	private static final int width = 1366;
	private static final int height = 768;
	private static final String title = "Nebulous2D Game Engine";

	public static void main(String[] args) {
		GameMain game = new GameMain();
		game.start();
	}
	
	public GameMain() {
		super(width, height, title);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		
	}

}