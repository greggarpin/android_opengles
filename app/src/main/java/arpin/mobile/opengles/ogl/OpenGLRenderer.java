package arpin.mobile.opengles.ogl;

import android.opengl.GLES20;

import arpin.mobile.opengles.math.Color;
import arpin.mobile.opengles.math.VertexList;
import arpin.mobile.opengles.renderables.Renderable;
import arpin.mobile.opengles.renderables.Renderer;

public class OpenGLRenderer implements Renderer
{
    private ShaderProgram shaderProgram;
    final private Color clearColor = new Color(0.5f, 0.5f, 0.5f, 1f);

    public OpenGLRenderer(ShaderProgram program)
    {
        shaderProgram = program;

        GLES20.glClearColor(clearColor.getRed(), clearColor.getGreen(), clearColor.getBlue(), clearColor.getAlpha());
    }

    @Override
    public void render(Renderable obj)
    {
        int positionHandle = shaderProgram.getHandle("vPosition");
        VertexList coords = obj.getCoordinates();

        GLES20.glVertexAttribPointer(positionHandle, coords.size(), GLES20.GL_FLOAT, false, coords.serializedVertexSize(), coords.toFloatBuffer());
        GLES20.glEnableVertexAttribArray(positionHandle);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
    }

    @Override
    public void initializeRenderLoop()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        shaderProgram.use();
    }

    @Override
    public void cleanupRenderLoop()
    {
    }
}
