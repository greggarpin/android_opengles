package arpin.mobile.opengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import arpin.mobile.opengles.math.Vertex;
import arpin.mobile.opengles.math.VertexList;
import arpin.mobile.opengles.ogl.ShaderProgram;

public class OpenGLESActivity extends AppCompatActivity {

    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int OPENGL_ES_VERSION = 2;
        glView = new GLSurfaceView(this);
        glView.setEGLContextClientVersion(OPENGL_ES_VERSION);
        glView.setRenderer(new ProgramRenderer());
        setContentView(glView);
    }

    private class ProgramRenderer implements GLSurfaceView.Renderer {
        private VertexList coords = null;

        private final String vertexShaderCode =
                "attribute vec4 vPosition; \n" +
                        "void main(){              \n" +
                        " gl_Position = vPosition; \n" +
                        "}                         \n";

        private final String fragmentShaderCode =
                "precision mediump float;  \n" +
                        "void main(){              \n" +
                        " gl_FragColor = vec4 (0.0, 1.0, 0.0, 1.0); \n" +
                        "}                         \n";
        private ShaderProgram shaderProgram;

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);

            coords = (new VertexList())
                    .add(new Vertex(-0.5f, -0.25f, 0f))
                    .add(new Vertex(0.5f, -0.25f, 0f))
                    .add(new Vertex(0f, 0.5f, 0f));

            shaderProgram = new ShaderProgram(vertexShaderCode, fragmentShaderCode);
        }

        public void onDrawFrame(GL10 unused) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

            shaderProgram.use();

            int positionHandle = shaderProgram.getHandle("vPosition");
            GLES20.glVertexAttribPointer(positionHandle, coords.size(), GLES20.GL_FLOAT, false, coords.serializedVertexSize(), coords.toFloatBuffer());
            GLES20.glEnableVertexAttribArray(positionHandle);

            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);
        }

        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }
    }
}