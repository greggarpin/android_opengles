package arpin.mobile.opengles.renderables;

public interface Renderer
{
    public void render(Renderable obj);

    public void initializeRenderLoop();

    public void cleanupRenderLoop();

    public void setViewDimensions(int width, int height);
}
