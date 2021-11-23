package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {

    private Vector2d start = new Vector2d(0, 0);
    private Vector2d end;

    public RectangularMap(int width, int height) {
        this.end = new Vector2d(width, height);
    }


    public boolean canMoveTo(Vector2d position) {
        if (this.isOccupied(position))
            return false;

        return position.precedes(this.end) && position.follows(this.start);
    }


    @Override
    public Vector2d[] border() {
        return new Vector2d[]{this.start, this.end};
    }
}
