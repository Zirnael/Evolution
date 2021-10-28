package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);


    public boolean isAt(Vector2d pos)
    {
        return this.position.equals(pos);
    }

    public String toString()
    {
        return "position: " + this.position.toString() + " orientation: " + this.orientation.toString();
    }
    public void  move(MoveDirection direction)
    {
        Vector2d new_position = null;
        switch (direction){
            case FORWARD:
            {
                new_position = this.orientation.toUnitVector().add(this.position);
                break;
            }
            case BACKWARD:
            {
                new_position = this.orientation.toUnitVector().opposite().add(this.position);
                break;
            }
            case RIGHT:
            {
                this.orientation = this.orientation.next();
                break;
            }
            case LEFT:
            {
                this.orientation = this.orientation.previous();
                break;
            }

        }
        if (new_position != null && new_position.precedes(new Vector2d(4,4)) && new_position.follows(new Vector2d(0,0)))
            this.position = new_position;
    }
}
