attribute vec2 aPosition;//顶点位置
attribute vec2 aTexCoord;//S T 纹理坐标
varying vec2 vTexCoord;
uniform mat4 uMatrix;
uniform mat4 uSTMatrix;
void main() {
    vTexCoord = aTexCoord;
    gl_Position = vec4(aPosition, 0.0, 1.0);
}