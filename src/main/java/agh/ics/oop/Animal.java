package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animal {
    private Orientations orientation = Orientations.NORTH;
    Vector2d position;
    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
    private AnyMap mapa;
    public int[] genes;
    public int energy;

    public Animal(AnyMap map, Vector2d initialPosition, int[] genes,int energy) {
        this.mapa = map;
        this.position = initialPosition;
        this.genes = genes;
        this.energy = energy;
    }

    public boolean isAt(Vector2d pos) {
        return this.position.equals(pos);
    }

    public String toString() {
        switch (this.orientation){
            case NORTH:
                return "^";
            case WEST:
                return "<";
            case SOUTH:
                return "v";
            case EAST:
                return ">";
        }
        return "";
    }

    public void move() {
        int rnd = Constants.random.nextInt(32);
        int choice = this.genes[rnd];

        Vector2d new_position = null;
        switch (choice) {
            case 0: {
                new_position = this.position.add(this.orientation.toUnitVector());
                break;
            }
            case 4:{
                new_position = this.position.subtract(this.orientation.toUnitVector());
            }
            default: {
                for(int i= 0 ; i < choice; i++){
                    assert this.orientation != null;
                    this.orientation = this.orientation.next();
                }
            }

        }
        if (new_position != null)
        {
            new_position = this.mapa.moveAttempt(new_position);
            this.positionChanged(this.position,new_position);
            this.position = new_position;

        }
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for (IPositionChangeObserver obs : this.observers)
            obs.positionChanged(this, oldPosition,newPosition);
    }
    public Vector2d getPosition()
    {
        return this.position;
    }
    void addObserver(IPositionChangeObserver observer)
    {
        this.observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer)
    {
        this.observers.remove(observer);
    }

    public Animal copy() {
        Animal result = new Animal(this.mapa,this.getPosition(),this.genes,Constants.startEnergy);
        return result;
    }
}
