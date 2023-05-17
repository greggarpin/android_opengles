package arpin.mobile.opengles.ogl;

import android.opengl.GLES20;

import arpin.mobile.opengles.math.VertexList;
import arpin.mobile.opengles.renderables.Renderable;
import arpin.mobile.opengles.renderables.Renderer;

public class OpenGLRenderer implements Renderer
{
    private ShaderProgram shaderProgram;

    public OpenGLRenderer(ShaderProgram program)
    {
        shaderProgram = program;
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
        shaderProgram.use();
    }

    @Override
    public void cleanupRenderLoop()
    {
    }
}
