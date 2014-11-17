#version 330

in vec2 texCoord0;

uniform sampler2D sample;

void main(){
	gl_FragColor = texture2D(sample, texCoord0.xy);
}