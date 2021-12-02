package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EngineTest {
    @Test
    public void ConstructorTest() {
        OptionsParser parser = new OptionsParser();
        MoveDirection[] tab = parser.parse(new String[]{"f", "forward", "r", "right", "right", "left", "backward", "b"});
        IWorldMap mapa = new RectangularMap(4, 4);
        Vector2d[] start_positions = {new Vector2d(2, 2), new Vector2d(2, 3), new Vector2d(2, 4)};
        SimulationEngine engine = new SimulationEngine(tab, mapa, start_positions);
        Assertions.assertFalse(mapa.canMoveTo(start_positions[0]));
        Assertions.assertFalse(mapa.canMoveTo(start_positions[1]));

        Assertions.assertTrue(mapa.canMoveTo(new Vector2d(3, 3)));
    }

    @Test
    public void RunTest() {
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "forward", "r", "right", "right", "left", "backward", "b"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        Animal[] animals = {(Animal) map.objectAt(positions[0]), (Animal) map.objectAt(positions[1])};
        Assertions.assertTrue(animals[0].isAt(positions[0]));
        Assertions.assertTrue(animals[1].isAt(positions[1]));

        engine.run();
        Assertions.assertTrue(true);
        Assertions.assertEquals(animals[0],map.objectAt(new Vector2d(2,4)));
        Assertions.assertEquals(animals[1],map.objectAt(new Vector2d(3,4)));
    }
}



