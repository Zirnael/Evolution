package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    Comparator<Object> compareX = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            Vector2d position1;
            int type1;
            Vector2d position2;
            int type2;

            if (o1 instanceof Animal) {
                position1 = ((Animal) o1).getPosition();
                type1 = 1;
            }
            else {
                type1 = 0;
                position1 = ((Grass) o1).getPosition();
            }
            if (o2 instanceof Animal) {
                type2 = 4;
                position2 = ((Animal) o2).getPosition();
            }
            else {
                type2 = 3;
                position2 = ((Grass) o2).getPosition();
            }
            if (position1.x != position2.x){
                return position2.x - position1.x;
            }
            if (position1.y != position2.y){
                return position2.y - position1.y;
            }
            return type2 - type1;
        }
    };
    Comparator<Object> compareY = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            Vector2d position1;
            int type1;
            Vector2d position2;
            int type2;

            if (o1 instanceof Animal) {
                position1 = ((Animal) o1).getPosition();
                type1 = 1;
            }
            else {
                type1 = 0;
                position1 = ((Grass) o1).getPosition();
            }

            if (o2 instanceof Animal) {
                type2 = 4;
                position2 = ((Animal) o2).getPosition();
            }
            else {
                type2 = 3;
                position2 = ((Grass) o2).getPosition();
            }
            if (position1.y != position2.y){
                return position2.y - position1.y;
            }
            if (position1.x != position2.x){
                return position2.x - position1.x;
            }
            return type2 - type1;
        }
    };

    SortedSet<Object> xSorted = new TreeSet<>(compareX);
    SortedSet<Object> ySorted = new TreeSet<>(compareY);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
