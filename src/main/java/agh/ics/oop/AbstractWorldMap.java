package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap{
    public ArrayList<Animal> animals = new ArrayList<>();
    public MapVisualizer visualizer = new MapVisualizer(this);

    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        this.animals.add(animal);
        return true;

    }
}
