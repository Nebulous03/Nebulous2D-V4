package anotherGameTest;

import nebulous.logic.level.Level;
import nebulous.main.Game;

public class MainGame extends Game{
	
	public Level level;

	@Override
	public void init() {
		level = new TestLevel();
		setActiveLevel(level);
	}

	@Override
	public void update() {
		
	}
	
	public static void main(String[] args){
		MainGame game = new MainGame();
		game.start();
	}

}
