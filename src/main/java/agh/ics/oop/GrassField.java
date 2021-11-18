package agh.ics.oop;

import java.util.ArrayList;

public class GrassField extends AbstractWorldMap implements IWorldMap  {
    private ArrayList<Grass> grasses = new ArrayList<>();
    private MapVisualizer visualizer = new MapVisualizer(this);

    public GrassField(int n) {
        int limit = (int) Math.sqrt(n * 10);
        for (int i = 0; i < n; i++) {
            boolean already_there = false;
            int x = (int) (Math.random() * limit);
            int y = (int) (Math.random() * limit);
            Vector2d shot = new Vector2d(x, y);
            for (Grass grass : this.grasses) {
                if (grass.getPosition().equals(shot)) {
                    already_there = true;
                    break;
                }
            }
            if (already_there) {
                i--;
            } else {
                this.grasses.add(new Grass(shot));
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
        for (Animal animal : this.animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    public String toString() {
        Vector2d low = grasses.get(0).getPosition();
        Vector2d high = grasses.get(0).getPosition();
        for (Animal animal : this.animals) {
            Vector2d considered = animal.getPosition();
            if (considered.precedes(low))
                low = considered;
            if (considered.follows(high))
                high = considered;
        }
        for (Grass grass : this.grasses) {
            Vector2d considered = grass.getPosition();
            if (considered.precedes(low))
                low = considered;
            if (considered.follows(high))
                high = considered;
        }
        return this.visualizer.draw(low, high);
    }
}
