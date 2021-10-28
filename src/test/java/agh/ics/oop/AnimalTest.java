package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    void smallTest()
    {
        Animal cat = new Animal();
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
        OptionsParser help = new OptionsParser();
        Animal cat = new Animal();
        Animal dog = new Animal();
        MoveDirection[] tab = help.parse(new String[]{"f", "forward", "r", "right", "right", "left", "backward", "b", "small", "kitty"});
        MoveDirection[] expected_result = {MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.BACKWARD};

        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        int i = 0;
        for (MoveDirection d: tab)
        {
            cat.move(d);
            dog.move(d);
            Assertions.assertEquals(expected_result[i],tab[i]);
        }

        Vector2d expected_position = new Vector2d(2,4);
        Assertions.assertTrue(cat.isAt(expected_position));
        Assertions.assertTrue(dog.isAt(expected_position));

    }

}
