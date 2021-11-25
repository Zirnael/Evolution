package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap implements IWorldMap  {
    public Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int n) {
        int limit = (int) Math.sqrt(n * 10);
        for (int i = 0; i < n; i++) {
            boolean already_there = false;
            int x = (int) (Math.random() * limit);
            int y = (int) (Math.random() * limit);
            Vector2d shot = new Vector2d(x, y);
            if (this.grasses.containsKey(shot))
                already_there = true;

            if (already_there) {
                i--;
            } else {
                this.grasses.put(shot,new Grass(shot));
            }
        }
    }

    public boolean canMoveTo(Vector2d position) {
        if(!this.isOccupied(position))
            return true;
        Object x = this.objectAt(position);
        return x instanceof Grass;
    }


    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position))
            return true;

        return this.grasses.containsKey(position);
    }

    @Override
    public Vector2d[] border() {
        Vector2d x = (Vector2d) this.grasses.keySet().toArray()[0];
        Vector2d low = x;
        Vector2d high = x;

        for (Vector2d considered : this.animals.keySet()) {
            low = low.lowerLeft(considered);
            high = high.upperRight(considered);
        }
        for (Vector2d considered : this.grasses.keySet()) {
            low = low.lowerLeft(considered);
            high = high.upperRight(considered);
        }
        return new Vector2d[] {low, high};
    }

    public Object objectAt(Vector2d position) {
        Object x = super.objectAt(position);
        if (x != null)
            return x;

        if (this.grasses.containsKey(position))
            return this.grasses.get(position);

        return null;
    }
}
