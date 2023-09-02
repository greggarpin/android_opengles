package arpin.mobile.opengles.ogl;

import android.opengl.GLES20;

import arpin.mobile.opengles.math.Color;
import arpin.mobile.opengles.math.Float;
import arpin.mobile.opengles.math.Frustum;
import arpin.mobile.opengles.math.Matrix;
import arpin.mobile.opengles.math.VertexList;
import arpin.mobile.opengles.renderables.Renderable;
import arpin.mobile.opengles.renderables.Renderer;

public class OpenGLRenderer implements Renderer
{
    private ShaderProgram shaderProgram;
    final private Color clearColor = new Color(0.5f, 0.5f, 0.5f, 1f);

    private Matrix modelview = Matrix.identity();
    private Matrix projection = Matrix.identity();
    private Frustum perspectiveFrustum = new Frustum();

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

        int projHandle = shaderProgram.getUniformHandle("projectionMatrix");

//        GLES20.glUniformMatrix4fv(projHandle, 1, false, projection.toFloatBuffer());
        GLES20.glUniformMatrix4fv(projHandle, 1, false, Matrix.identity().toFloatBuffer());
        float []dbg = new float[16];
        GLES20.glGetUniformfv(projHandle, 0, dbg, 0);
        System.out.println("MATRIX DUMP: " + projHandle);
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                System.out.print(dbg[i*4 + j]);
            }
            System.out.println(",");
        }
    }

    @Override
    public void cleanupRenderLoop()
    {
    }

    public void setViewDimensions(int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);
    }

    public void setPerspectiveFrustum(Frustum frustum)
    {
        perspectiveFrustum = frustum;
        buildMatrices();
    }

    private void buildMatrices()
    {
        modelview = Matrix.identity();

        Frustum f = perspectiveFrustum;
        projection = new Matrix(new float[]{
                (2*f.getNear())/(f.getRight() - f.getLeft()),
                    0,
                    -(f.getRight() + f.getLeft())/(f.getRight() - f.getLeft()),
                    0,
                0,
                    (2*f.getNear())/(f.getTop() - f.getBottom()),
                    -(f.getTop() + f.getBottom())/(f.getTop() - f.getBottom()),
                    0,
                0,
                    0,
                    (f.getFar() + f.getNear())/(f.getFar() - f.getNear()),
                    -(2*f.getNear()*f.getFar())/(f.getFar() - f.getNear()),
                0,
                    0,
                    1,
                    0});
    }
}
