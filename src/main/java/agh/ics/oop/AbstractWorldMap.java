package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    public ArrayList<Animal> animals = new ArrayList<>();
    public MapVisualizer visualizer = new MapVisualizer(this);

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        this.animals.add(animal);
        return true;
    }

    public Object objectAt(Vector2d position) {
        for (Animal x : this.animals) {
            if (x.getPosition().equals(position))
                return x;
        }
        return null;
    }

    public boolean isOccupied(Vector2d position) {
        for (Animal x : this.animals) {
            if (x.getPosition().equals(position))
                return true;
        }
        return false;
    }
    public abstract Vector2d[] border();

    public String toString() {
        Vector2d[] border = this.border();
        Vector2d start = border[0];
        Vector2d end = border[1];
        return this.visualizer.draw(start, end);
    }
}