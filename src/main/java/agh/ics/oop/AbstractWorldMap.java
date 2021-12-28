package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IPositionChangeObserver {
    Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();

    public void place(Animal animal) {
        Vector2d position = animal.getPosition();

        ArrayList<Animal> list = this.animals.get(position);
        list.add(animal);
        animal.addObserver(this);
    }
    public ArrayList<Animal> animalsAt(Vector2d position) {
        return this.animals.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return !this.animals.get(position).isEmpty();
    }

    public abstract Vector2d[] border();


}