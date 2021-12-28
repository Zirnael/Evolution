package agh.ics.oop;

public class TeleportMap extends AnyMap{
    public TeleportMap() {
        super();
    }

    @Override
    public Vector2d moveAttempt(Vector2d new_position) {
        int x = new_position.x;
        int y = new_position.y;

        x += Constants.width;
        y += Constants.height;
        x %= Constants.width;
        y %= Constants.height;

        return new Vector2d(x,y);
    }
}
