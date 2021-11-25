package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private List<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();
    private IWorldMap mapa;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.mapa = map;
        this.position = initialPosition;
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

    public void move(MoveDirection direction) {
        Vector2d new_position = null;
        switch (direction) {
            case FORWARD: {
                new_position = this.orientation.toUnitVector().add(this.position);
                break;
            }
            case BACKWARD: {
                new_position = this.orientation.toUnitVector().opposite().add(this.position);
                break;
            }
            case RIGHT: {
                this.orientation = this.orientation.next();
                break;
            }
            case LEFT: {
                this.orientation = this.orientation.previous();
                break;
            }

        }
        if (new_position != null && this.mapa.canMoveTo(new_position))
        {
            positionChanged(this.position,new_position);
            this.position = new_position;

        }
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {
        for (IPositionChangeObserver obs : this.observers)
            obs.positionChanged(oldPosition,newPosition);
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
}
