package nebulous.graphics.shaders;

public class InvertedShader extends Shader{
	
	public InvertedShader() {
		super("/shaders/invertedColor.vs", "/shaders/invertedColor.fs");
	}

	@Override
	public void addUniforms() {
		
	}

	@Override
	public void updateUniforms() {
		
	}

}
