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

    @Test
    public void testGregg()
    {
        int[] testInts = {7, 8, 9};
        db(testInts, "Start");

        testArr(testInts);
        db(foo, "First copy");

        testInts[1] = 99;
        db(foo, "Second copy");
        db(testInts, "local");

    }
    private void db(int[] vals, String msg)
    {
        System.out.print(msg + "{");
        for (int v:vals) {
            System.out.print(v);
            System.out.print(";");
        }
        System.out.println("}");
    }
    private int[] foo = {1, 2, 3};
    private void testArr(int[] vals)
    {
        foo = vals.clone();
    }
}
