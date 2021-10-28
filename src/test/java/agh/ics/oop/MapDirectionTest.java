package agh.ics.oop;

import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {
    @Test
    void next_tester() {
        Assertions.assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        Assertions.assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        Assertions.assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        Assertions.assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
    }

    @Test
    void prev_tester() {
        Assertions.assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        Assertions.assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        Assertions.assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        Assertions.assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
    }

}
