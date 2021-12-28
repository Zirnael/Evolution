package agh.ics.oop;

public enum Orientations {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;


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
    public Orientations next(){
        switch (this)
        {
            case NORTH:
                return Orientations.NORTHEAST;
            case NORTHEAST:
                return Orientations.EAST;
            case EAST:
                return Orientations.SOUTHEAST;
            case SOUTHEAST:
                return Orientations.SOUTH;
            case SOUTH:
                return Orientations.SOUTHWEST;
            case SOUTHWEST:
                return Orientations.WEST;
            case WEST:
                return Orientations.NORTHWEST;
            case NORTHWEST:
                return Orientations.NORTH;
        }
        return null;
    }
    public Vector2d toUnitVector(){
        switch (this)
        {
            case NORTH:
                return new Vector2d(0,1);
            case NORTHEAST:
                return new Vector2d(1,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTHEAST:
                return new Vector2d(1,-1);
            case SOUTH:
                return new Vector2d(0,-1);
            case SOUTHWEST:
                return new Vector2d(-1,-1);
            case WEST:
                return new Vector2d(-1,0);
            case NORTHWEST:
                return new Vector2d(-1,1);
        }
        return new Vector2d(0,0);
    }
}