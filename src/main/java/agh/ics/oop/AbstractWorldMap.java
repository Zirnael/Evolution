package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    Map<Vector2d, Animal> animals = new HashMap<>();
    public MapVisualizer visualizer = new MapVisualizer(this);

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            throw new IllegalArgumentException("Position " + animal.getPosition() + " is already occupied");

        this.animals.put(animal.getPosition(),animal);
        animal.addObserver(this);
        return true;
    }
    public Object objectAt(Vector2d position) {
        if (!this.isOccupied(position))
            return null;
        return this.animals.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    public abstract Vector2d[] border();

    public String toString() {
        Vector2d[] border = this.border();
        Vector2d start = border[0];
        Vector2d end = border[1];
        return this.visualizer.draw(start, end);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition,animal);
    }
}