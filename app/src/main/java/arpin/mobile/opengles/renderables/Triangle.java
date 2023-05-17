package arpin.mobile.opengles.renderables;

import arpin.mobile.opengles.math.Vertex;
import arpin.mobile.opengles.math.VertexList;

public class Triangle implements Renderable
{
    private VertexList coordinates;

    public Triangle()
    {
// TODO:: shouldn't be hardcoded
        coordinates = (new VertexList())
                .add(new Vertex(-0.5f, -0.25f, 0f))
                .add(new Vertex(0.5f, -0.25f, 0f))
                .add(new Vertex(0f, 0.5f, 0f));
    }

    @Override
    public VertexList getCoordinates()
    {
        return coordinates;
    }
}
