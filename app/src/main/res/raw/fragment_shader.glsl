#extension GL_OES_EGL_image_external : require
precision lowp float;
varying vec2 vTexCoord;
uniform sampler2D uSample;
void main() {
    gl_FragColor = vec4(texture2D(uSample, vTexCoord).rgb, texture2D(uSample, vTexCoord + vec2(-0.5, 0)).r);
}