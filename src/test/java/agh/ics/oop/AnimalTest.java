
/*

package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    void smallTest()
    {
        RectangularMap mapa = new RectangularMap(4,4);
        Vector2d start = new Vector2d(2,2);
        Animal cat = new Animal(mapa,start);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);

        Assertions.assertTrue(cat.isAt(new Vector2d(4,3)));
    }

    @Test
    void bigTest()
    {
        IWorldMap mapa = new RectangularMap(4,4);
        Vector2d start = new Vector2d(2,2);
        OptionsParser help = new OptionsParser();
        Animal cat = new Animal(mapa,start);
        Animal dog = new Animal(mapa,start);
        MoveDirection[] tab = help.parse(new String[]{"f", "forward", "r", "right", "right", "left", "backward", "b"});
        MoveDirection[] expected_result = {MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.BACKWARD};

        mapa.place(cat);
        int i = 0;
        for (MoveDirection d: tab)
        {
            cat.move(d);
            Assertions.assertEquals(expected_result[i],tab[i]);
        }

        Vector2d expected_position = new Vector2d(2,4);
        Assertions.assertTrue(cat.isAt(expected_position));

    }

}
 */