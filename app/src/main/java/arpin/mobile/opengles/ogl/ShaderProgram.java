package arpin.mobile.opengles.ogl;

import android.opengl.GLES20;

import java.util.HashMap;
import java.util.Map;

public class ShaderProgram
{
    private final int programHandle;
    private final Map<String, Integer> cachedHandles = new HashMap<>();

    public ShaderProgram(final String vertexShaderSource, final String fragmentShaderSource)
    {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSource);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSource);

        programHandle = GLES20.glCreateProgram();

        GLES20.glAttachShader(programHandle, vertexShader);
        GLES20.glAttachShader(programHandle, fragmentShader);
        GLES20.glLinkProgram(programHandle);
    }

    public int getHandle(final String handleName)
    {
        try {
            if (!cachedHandles.containsKey(handleName)) {

                int handle = GLES20.glGetAttribLocation(programHandle, handleName);
                cachedHandles.put(handleName, handle);
            }
            return cachedHandles.get(handleName);

        } catch (NullPointerException ex) {
            // Should never happen because of the above check
            return 0;
        }
    }

    public void use()
    {
        GLES20.glUseProgram(programHandle);
    }

    private int loadShader(int type, final String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}