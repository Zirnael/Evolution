package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        switch (this)
        {
            case NORTH:
                return "Polnoc";
            case SOUTH:
                return "Poludnie";
            case EAST:
                return "Wschod";
            case WEST:
                return "Zachod";
        }
        return "";
    }
    public MapDirection next(){
        switch (this)
        {
            case NORTH:
                return MapDirection.EAST;
            case EAST:
                return MapDirection.SOUTH;
            case SOUTH:
                return MapDirection.WEST;
            case WEST:
                return MapDirection.NORTH;
        }
        return null;
    }
    public MapDirection previous(){
        switch (this)
        {
            case NORTH:
                return MapDirection.WEST;
            case WEST:
                return MapDirection.SOUTH;
            case SOUTH:
                return MapDirection.EAST;
            case EAST:
                return MapDirection.NORTH;
        }
        return null;
    }
    public Vector2d toUnitVector(){
        switch (this)
        {
            case NORTH:
                return new Vector2d(0,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTH:
                return new Vector2d(0,-1);
            case WEST:
                return new Vector2d(-1,0);
        }
        return new Vector2d(0,0);
    }
}