package agh.ics.oop;

public class BorderMap extends AnyMap{

    @Override
    public Vector2d moveAttempt(Vector2d new_position) {
        int x = new_position.x;
        int y = new_position.y;
        x = Math.max(0,x);
        x = Math.min(Constants.width-1,x);
        y = Math.max(0,y);
        y = Math.min(Constants.height-1,y);
        return new Vector2d(x,y);

    }
}

