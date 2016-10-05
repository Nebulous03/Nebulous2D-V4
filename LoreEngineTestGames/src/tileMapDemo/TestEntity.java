package tileMapDemo;

import nebulous.graphics.primatives.Texture;
import nebulous.logic.objects.Entity;

public class TestEntity extends Entity{

	public TestEntity(Texture texture) {
		super(texture);
	}

	@Override
	public void update() {
		updateCollision();
	}


}
