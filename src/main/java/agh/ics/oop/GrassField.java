package agh.ics.oop;

import java.util.ArrayList;

public class GrassField extends AbstractWorldMap implements IWorldMap  {
    public ArrayList<Grass> grasses = new ArrayList<>();

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
        if (super.isOccupied(position))
            return true;

        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vector2d[] border() {

        Vector2d low = grasses.get(0).getPosition();
        Vector2d high = grasses.get(0).getPosition();
        for (Animal animal : this.animals) {
            Vector2d considered = animal.getPosition();
            low = low.lowerLeft(considered);
            high = high.upperRight(considered);
        }
        for (Grass grass : this.grasses) {
            Vector2d considered = grass.getPosition();
            low = low.lowerLeft(considered);
            high = high.upperRight(considered);
        }
        return new Vector2d[] {low, high};
    }

    public Object objectAt(Vector2d position) {
        Object x = super.objectAt(position);
        if (x != null)
            return x;

        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }
}
