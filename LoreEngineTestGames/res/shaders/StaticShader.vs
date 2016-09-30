#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec2 texCoords;

out vec2 vTexCoords;

uniform mat4 transform;
uniform mat4 perspective;
uniform mat4 view;

void main()
{
    gl_Position = transform * vec4(position, 1.0);
	vTexCoords = texCoords;
}