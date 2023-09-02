package arpin.mobile.opengles.math;

import java.nio.FloatBuffer;

public class Matrix
{
    private FloatBuffer floatBuffer = null;

    private float elements[] = {
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f};

    private Matrix() {}

    public Matrix(float[] els)
    {
        int ct = Math.min(els.length, 16);

        for (int i = 0; i < ct; i++) {
            elements[i] = els[i];
        }
    }

    public static Matrix identity()
    {
        return new Matrix();
    }

    public Matrix set(int row, int col, float val)
    {
        elements[row*4 + col] = val;
        return this;
    }

    public float get(int row, int col)
    {
        return elements[row*4 + col];
    }

    public FloatBuffer toFloatBuffer()
    {
        if (floatBuffer == null) {
            floatBuffer = Float.arrayToFloatBuffer(elements);
        }

        return floatBuffer;
    }
}
