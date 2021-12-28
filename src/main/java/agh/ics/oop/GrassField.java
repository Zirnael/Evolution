package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
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
                Grass newGrass = new Grass(shot);
                this.grasses.put(shot,newGrass);
            }
        }
    }
    public void place(Animal animal) {
        super.place(animal);
        Vector2d position = animal.getPosition();
    }


    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position))
            return true;
        return this.grasses.containsKey(position);
    }

    @Override
    public Vector2d[] border() {
        return new Vector2d[] {new Vector2d(0,0),new Vector2d(Constants.width,Constants.height)};
    }

    @Override
    public void positionChanged(Animal moveAnimal, Vector2d oldPosition, Vector2d newPosition) {
        ArrayList<Animal> list = this.animalsAt(oldPosition);
        list.remove(moveAnimal);
        list = this.animalsAt(newPosition);
        list.add(moveAnimal);
    }
}
