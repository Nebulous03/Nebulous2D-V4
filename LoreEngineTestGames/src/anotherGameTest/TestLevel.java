package anotherGameTest;

import nebulous.logic.level.Level;

public class TestLevel extends Level{
	
	public Player player;

	@Override
	public void init() {
		player = new Player();
		add("player", player);
	}

	@Override
	public void update(double delta) {
		
	}

}
