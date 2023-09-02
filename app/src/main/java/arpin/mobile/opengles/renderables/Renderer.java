package arpin.mobile.opengles.renderables;

import arpin.mobile.opengles.math.Frustum;

public interface Renderer
{
    public void render(Renderable obj);

    public void initializeRenderLoop();

    public void cleanupRenderLoop();

    public void setViewDimensions(int width, int height);

    public void setPerspectiveFrustum(Frustum frustum);
}
