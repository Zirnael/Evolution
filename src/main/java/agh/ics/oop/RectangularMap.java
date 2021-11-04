package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private int width;
    private int height;
    private ArrayList<Animal> animals = new ArrayList();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;

    }


    public boolean canMoveTo(Vector2d position) {
        if (this.isOccupied(position))
            return false;

        return position.precedes(new Vector2d(this.width, this.height)) && position.follows(new Vector2d(0, 0));
    }

    public boolean place(Animal animal) {
        if(isOccupied(animal.getPosition()))
            return false;

        this.animals.add(animal);
        return true;

    }

    public boolean isOccupied(Vector2d position) {
        for (Animal x :this.animals){
            if(x.getPosition() == position)
                return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for(Animal x : this.animals)
        {
            if (x.getPosition() == position)
                return x;
        }
        return null;
    }
}
