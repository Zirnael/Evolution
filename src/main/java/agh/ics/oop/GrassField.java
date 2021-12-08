package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap implements IWorldMap  {
    public Map<Vector2d, Grass> grasses = new HashMap<>();
    private MapBoundary mapBoundary = new MapBoundary();

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
                Grass newGrass = new Grass(shot);
                this.grasses.put(shot,newGrass);
                mapBoundary.ySortedGrass.add(shot);
                mapBoundary.xSortedGrass.add(shot);
            }
        }
    }
    public boolean place(Animal animal) {
        super.place(animal);
        Vector2d position = animal.getPosition();
        animal.addObserver(mapBoundary);
        mapBoundary.xSortedAnimal.add(position);
        mapBoundary.ySortedAnimal.add(position);
        return true;
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
        return mapBoundary.border();
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
