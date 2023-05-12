package arpin.mobile.opengles.math;

public class Vertex
{
    float x = 0, y = 0, z = 0, w = 1;

    public Vertex()
    {
    }

    public Vertex(float xp, float yp, float zp)
    {
        setX(xp);
        setY(yp);
        setZ(zp);
    }

    public float getX()
    {
        return x;
    }

    public Vertex setX(float xp)
    {
        x = xp;
        return this;
    }

    public float getY()
    {
        return y;
    }

    public Vertex setY(float yp)
    {
        y = yp;
        return this;
    }

    public float getZ()
    {
        return z;
    }

    public Vertex setZ(float zp)
    {
        z = zp;
        return this;
    }

    public float getW()
    {
        return w;
    }
}
