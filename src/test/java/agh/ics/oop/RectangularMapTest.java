package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    void creation_test(){
        RectangularMap xx = new RectangularMap(1,2);
        Vector2d[] border = xx.border();
        Vector2d start = border[0];
        Vector2d end = border[1];
        Assertions.assertEquals(start,new Vector2d(0,0));
        Assertions.assertEquals(end, new Vector2d(1,2));
        Assertions.assertTrue(xx.canMoveTo(new Vector2d(1,1)));
    }

    @Test
    void main_test()
    {
        RectangularMap xx = new RectangularMap(5,4);
        Animal cat = new Animal(xx,new Vector2d(2,2));
        xx.place(cat);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        Vector2d expected = new Vector2d(2,4);
        Assertions.assertEquals(cat.getPosition(), expected);
        Assertions.assertTrue(xx.isOccupied(expected));
        Assertions.assertEquals(cat,xx.objectAt(expected));
        cat.move(MoveDirection.FORWARD);
        Assertions.assertEquals(cat.getPosition(), expected);
        Assertions.assertTrue(xx.isOccupied(expected));
        Assertions.assertEquals(cat,xx.objectAt(expected));
        cat.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        expected = new Vector2d(5,4);
        Assertions.assertEquals(cat.getPosition(), expected);
        Assertions.assertTrue(xx.isOccupied(expected));
        Assertions.assertEquals(cat,xx.objectAt(expected));
    }

}
