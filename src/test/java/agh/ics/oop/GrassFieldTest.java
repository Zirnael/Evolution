package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    void generation() {
        GrassField x = new GrassField(1000);
        Assertions.assertEquals(x.grasses.size(), 1000);
        GrassField y = new GrassField(10);
        Assertions.assertEquals(y.grasses.size(), 10);
        GrassField z = new GrassField(10000);
        Assertions.assertEquals(z.grasses.size(), 10000);
    }
    @Test
    void main_test() {
        GrassField x = new GrassField(10);
        Animal cat = new Animal(x, new Vector2d(2, 2));
        for(int i= 0; i < 10; i++)
            cat.move(MoveDirection.FORWARD);
        x.place(cat);
        Vector2d expected_end = new Vector2d(2,12);
        Assertions.assertTrue(x.isOccupied(expected_end));
        Assertions.assertEquals(cat,x.objectAt(expected_end));
        Assertions.assertEquals(cat.getPosition(),expected_end);
    }
}
