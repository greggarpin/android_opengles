package arpin.mobile.opengles.math;

public class Color
{
    protected float red = 0;
    private float green = 0;
    private float blue = 0;
    private float alpha = 1;

    public Color()
    {
        this(0, 0, 0, 1);
    }

    public Color(float red, float green, float blue)
    {
        this(red, green, blue, 1);
    }

    public Color(float redParam, float greenParam, float blueParam, float alphaParam)
    {
        red = redParam;
        green = greenParam;
        blue = blueParam;
        alpha = alphaParam;
    }

    public float getRed()
    {
        return red;
    }

    public float getGreen()
    {
        return green;
    }

    public float getBlue()
    {
        return blue;
    }

    public float getAlpha()
    {
        return alpha;
    }
}
