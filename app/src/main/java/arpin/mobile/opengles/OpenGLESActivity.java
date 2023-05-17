package arpin.mobile.opengles;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import arpin.mobile.opengles.ogl.OpenGLRenderer;
import arpin.mobile.opengles.ogl.ShaderProgram;
import arpin.mobile.opengles.renderables.Renderable;
import arpin.mobile.opengles.renderables.Renderer;
import arpin.mobile.opengles.renderables.Triangle;

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
        Renderer renderer;
        private Renderable triangle = new Triangle();

        public void onSurfaceCreated(GL10 unused, EGLConfig config) {
            GLES20.glClearColor(1.0f, 1.0f, 0.0f, 1.0f);

            ShaderProgram shaderProgram = ShaderProgram.createFromAssetFiles(getAssets(), "defaultVertexShader.vert", "defaultFragmentShader.frag");
            renderer = new OpenGLRenderer(shaderProgram);
        }

        public void onDrawFrame(GL10 unused)
        {
            renderer.initializeRenderLoop();
            renderer.render(triangle);
            renderer.cleanupRenderLoop();
        }

        public void onSurfaceChanged(GL10 unused, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }
    }
}