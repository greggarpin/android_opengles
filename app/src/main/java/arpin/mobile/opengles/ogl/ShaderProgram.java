package arpin.mobile.opengles.ogl;

import android.content.res.AssetManager;
import android.opengl.GLES20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import arpin.mobile.opengles.utils.Logger;

public class ShaderProgram
{
    private final int programHandle;
    private final Map<String, Integer> cachedHandles = new HashMap<>();

    /**
     * Private constructor. Use either createFromFiles() or getDefaultShader() method.
     */
    private ShaderProgram(final String vertexShaderSource, final String fragmentShaderSource)
    {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSource);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSource);

        programHandle = GLES20.glCreateProgram();

        GLES20.glAttachShader(programHandle, vertexShader);
        GLES20.glAttachShader(programHandle, fragmentShader);
        GLES20.glLinkProgram(programHandle);
    }

    public static ShaderProgram createFromAssetFiles(AssetManager assetMgr, final String vertexShaderFilename, final String fragmentShaderFilename)
    {
        try {
            return new ShaderProgram(
                    readAssetFile(assetMgr, vertexShaderFilename),
                    readAssetFile(assetMgr, fragmentShaderFilename));

        } catch (IOException ex) {
            Logger.getLogger().exception(String.format("Failed to load shaders from file: %s, %s", vertexShaderFilename, fragmentShaderFilename));

            return getDefaultShader();
        }
    }

    public static ShaderProgram getDefaultShader()
    {
        final String vertexShaderCode =
                "attribute vec4 vPosition; \n" +
                        "void main(){              \n" +
                        " gl_Position = vPosition; \n" +
                        "}                         \n";

        final String fragmentShaderCode =
                "precision mediump float;  \n" +
                        "void main(){              \n" +
                        " gl_FragColor = vec4 (1.0, 0.0, 0.0, 1.0); \n" +
                        "}                         \n";

        return new ShaderProgram(vertexShaderCode, fragmentShaderCode);
    }

    private static String readAssetFile(AssetManager assetMgr, final String filename) throws IOException
    {
        StringBuilder fileString = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(assetMgr.open(filename)));

        String line = null;
        while ((line = reader.readLine()) != null) {
            fileString.append(line);
        }

        return fileString.toString();
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