#version 330

in vec2 vTexCoords;
out vec4 fragColor;

uniform sampler2D sampledTexture;

void main()
{
    vec4 color = ;
    color.rgb = 1.0 - color.rgb;
    fragColor = color;
}