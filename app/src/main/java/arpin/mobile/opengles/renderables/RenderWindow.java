package arpin.mobile.opengles.renderables;

import arpin.mobile.opengles.math.Color;

public class RenderWindow
{
    // TODO:: Does RenderWindow own Renderer or vice versa or neither?
    // TODO:: This can hold backgroundColor, window dimensions, camera position and matrices
    // TODO:: Replace the configurations in OpenGLActivity with this thing
    private Color backgroundColor = new Color();

    public void setBackgroundColor(Color col)
    {
        backgroundColor = col;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }
}
