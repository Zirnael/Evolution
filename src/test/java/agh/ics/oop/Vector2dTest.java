package agh.ics.oop;

import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    @Test
    void test_equals(){

        Vector2d x = new Vector2d(4, 10);
        Vector2d y = new Vector2d(1, 10);
        Vector2d z = new Vector2d(4 , 10);
        Assertions.assertNotEquals(x, y);
        Assertions.assertEquals(x,z);
        Assertions.assertNotEquals(y,z);

    }

    @Test
    void test_toString()
    {
        Vector2d x = new Vector2d(40,10);
        Assertions.assertEquals("(40,10)",x.toString());
    }

    @Test
    void test_precedes()
    {
        Vector2d x = new Vector2d(1,1);
        Vector2d y = new Vector2d(2 ,4);
        Vector2d z = new Vector2d(2,3);

        Assertions.assertTrue(x.precedes(x));
        Assertions.assertTrue(x.precedes(y));
        Assertions.assertTrue(x.precedes(z));
        Assertions.assertFalse(y.precedes(x));
        Assertions.assertTrue(z.precedes(y));
    }

    @Test
    void test_follows()
    {
        Vector2d x = new Vector2d(1,20);
        Vector2d y = new Vector2d(1,10);
        Vector2d z = new Vector2d(3,15);

        Assertions.assertTrue(x.follows(x));
        Assertions.assertTrue(x.follows(y));
        Assertions.assertFalse(x.follows(z));
        Assertions.assertFalse(y.follows(z));
        Assertions.assertFalse(z.follows(x));
        Assertions.assertTrue(z.follows(y));
    }

    @Test
    void test_upperRight()
    {
        Vector2d x = new Vector2d(10,20);
        Vector2d y = new Vector2d(0,5);
        Vector2d z = new Vector2d(-5,10);

        Assertions.assertEquals(z.upperRight(x), new Vector2d(10,20));
        Assertions.assertEquals(x.upperRight(z), new Vector2d(10,20));
        Assertions.assertEquals(y.upperRight(y), new Vector2d(0,5));

    }

    @Test
    void test_lowerLeft()
    {
        Vector2d x = new Vector2d(10,20);
        Vector2d y = new Vector2d(0,5);

        Assertions.assertEquals(x.lowerLeft(x),new Vector2d(10,20));
        Assertions.assertEquals(x.lowerLeft(y),new Vector2d(0,5));

    }

    @Test
    void test_add()
    {
        Vector2d x = new Vector2d(10,20);
        Vector2d y = new Vector2d(-5,10);

        Assertions.assertEquals(x.add(x),new Vector2d(20,40));
        Assertions.assertEquals(x.add(y),new Vector2d(5,30));

    }

    @Test
    void test_subtract()
    {
        Vector2d x = new Vector2d(10,20);
        Vector2d y = new Vector2d(-5,10);

        Assertions.assertEquals(x.subtract(y),new Vector2d(15,10));
        Assertions.assertEquals(x.subtract(x),new Vector2d(0,0));
        Assertions.assertEquals(y.subtract(x),new Vector2d(-15,-10));

        Assertions.assertEquals(y.subtract(x), x.subtract(y).opposite());
    }

    @Test
    void test_opposite()
    {
        Vector2d x = new Vector2d(5,-10);
        Vector2d y = new Vector2d(0,8);

        Assertions.assertEquals(x.opposite(),new Vector2d(-5,10));
        Assertions.assertEquals(y.opposite(),new Vector2d(0,-8));
    }
}
