attribute vec4 vPosition;
uniform mat4 projectionMatrix;
uniform mat4 modelviewMatrix;

void main()
{
    mat4 test = mat4(1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    gl_Position = projectionMatrix * vPosition;
}