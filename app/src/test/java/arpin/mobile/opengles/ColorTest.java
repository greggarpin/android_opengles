package arpin.mobile.opengles;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import arpin.mobile.opengles.math.Color;

public class ColorTest
{
    private final float acceptableDelta = 0f;

    @Test
    public void testEmptyConstructor()
    {
        Color c = new Color();
        Assert.assertEquals(0, c.getRed(), acceptableDelta);
        Assert.assertEquals(0, c.getGreen(), acceptableDelta);
        Assert.assertEquals(0, c.getBlue(), acceptableDelta);
        Assert.assertEquals(1, c.getAlpha(), acceptableDelta);
    }

    @Test
    public void testTriadicConstructor()
    {
        float testR = 0.5f, testG = 0.3f, testB = 1.0f;

        Color c = new Color(testR, testG, testB);
        Assert.assertEquals(testR, c.getRed(), acceptableDelta);
        Assert.assertEquals(testG, c.getGreen(), acceptableDelta);
        Assert.assertEquals(testB, c.getBlue(), acceptableDelta);
        Assert.assertEquals(1, c.getAlpha(), acceptableDelta);
    }

    @Test
    public void testFullConstructor()
    {
        float testR = 0.5f, testG = 0.3f, testB = 1.0f, testAlpha = 0.4f;

        Color c = new Color(testR, testG, testB, testAlpha);
        Assert.assertEquals(testR, c.getRed(), acceptableDelta);
        Assert.assertEquals(testG, c.getGreen(), acceptableDelta);
        Assert.assertEquals(testB, c.getBlue(), acceptableDelta);
        Assert.assertEquals(testAlpha, c.getAlpha(), acceptableDelta);
    }
}
