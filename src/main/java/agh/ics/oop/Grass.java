package agh.ics.oop;

public class Grass {
    private Vector2d pozycja;

    public Grass(Vector2d position) {
        this.pozycja = position;
    }
    public Vector2d getPosition(){
        return this.pozycja;
    }

    @Override
    public String toString() {
        return "*";
    }
}
