package arpin.mobile.opengles.math;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class VertexList
{
    private final List<Vertex> vertices = new ArrayList<>();
    private FloatBuffer floatBuffer;
    private final int floatSize = 4;
    private final int floatsPerElement = 4;

    public VertexList add(final Vertex v)
    {
        vertices.add(v);
        floatBuffer = null;

        return this;
    }

    public int[] getOrder()
    {
        // TODO:: This could be modified to reuse vertices and dynamically generate the order array
        int numVertices = vertices.size();
        int[] out = new int[numVertices];

        for (int i = 0; i < numVertices; ++i) {
            out[i] = i;
        }

        return out;
    }

    public int size()
    {
        return vertices.size();
    }

    public int serializedVertexSize()
    {
        return floatSize * floatsPerElement;
    }

    private float[] toArray()
    {
        float[] out = new float[size() * floatsPerElement];
        int i = 0;

        for (Vertex v:vertices) {
            out[i++] = v.getX();
            out[i++] = v.getY();
            out[i++] = v.getZ();
            out[i++] = v.getW();
        }

        return out;
    }

    public FloatBuffer toFloatBuffer()
    {
        if (floatBuffer == null) {
            float[] vertexArray = toArray();

            ByteBuffer bb = ByteBuffer.allocateDirect(floatSize * vertexArray.length * size());
            bb.order(ByteOrder.nativeOrder());

            floatBuffer = bb.asFloatBuffer();
            floatBuffer.put(vertexArray);
            floatBuffer.position(0);
        }

        return floatBuffer;
    }
}
