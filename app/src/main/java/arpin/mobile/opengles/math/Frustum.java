package arpin.mobile.opengles.math;

public class Frustum
{
    private float leftX = -1;
    private float rightX = 1;
    private float topY = 1;
    private float bottomY = -1;
    private float nearZ = -1;
    private float farZ = 1;

    public float getLeft() { return leftX; }
    public float getRight() { return rightX; }
    public float getTop() { return topY; }
    public float getBottom() { return bottomY; }
    public float getNear() { return nearZ; }
    public float getFar() { return farZ; }

    public Frustum setLeft(float left) { leftX = left; return this; }
    public Frustum setRight(float right) { rightX = right; return this; }
    public Frustum setTop(float top) { topY = top; return this; }
    public Frustum setBottom(float bottom) { bottomY = bottom; return this; }
    public Frustum setNear(float near) { nearZ = near; return this; }
    public Frustum setFar(float far) { farZ = far; return this; }
}
