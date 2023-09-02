package arpin.mobile.opengles.math;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Float
{
    public static final int SIZE = 4;

    public static FloatBuffer arrayToFloatBuffer(float[] arr)
    {
        ByteBuffer bb = ByteBuffer.allocateDirect(SIZE * arr.length);
        bb.order(ByteOrder.nativeOrder());

        FloatBuffer floatBuffer = bb.asFloatBuffer();
        floatBuffer.put(arr);
        floatBuffer.position(0);

        return floatBuffer;
    }
}
