package nebulous.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {

	public static Matrix4f getTransformationMatrix(Vector3f translation, float rotation, Vector3f scale){
		Matrix4f matrix = new Matrix4f().identity()
		.translate(translation.x, translation.y, translation.z)
		.rotate(rotation, new Vector3f(0,0,1))
		.scale(scale)
		.transpose();
		return matrix;
	}
	
	public static Matrix4f getPerspectiveMatrix(float fov, float aspectRatio, float zNear, float xFar){
		return new Matrix4f().perspective(fov, aspectRatio, zNear, xFar).transpose();
	}
	
	public static Matrix4f getViewMatrix(Camera camera){
		Matrix4f matrix = new Matrix4f().identity()
		.translate(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z)
		.rotate((float)Math.toRadians(camera.getRotation().x), new Vector3f(1, 0, 0))
		.rotate((float)Math.toRadians(camera.getRotation().y), new Vector3f(0, 1, 0))
		.rotate((float)Math.toRadians(camera.getRotation().z), new Vector3f(0, 0, 1))
		.transpose();
		return matrix;
	}
	
}
