#version 330

in vec2 vTexCoords;
out vec4 fragColor;

uniform sampler2D sampledTexture;

void main()
{
    fragColor = texture(sampledTexture, vTexCoords);
}