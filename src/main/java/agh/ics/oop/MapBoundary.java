package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

//    Comparator<Object> compareX = new Comparator<Object>() {
//        @Override
//        public int compare(Object o1, Object o2) {
//            Vector2d position1;
//            int type1;
//            Vector2d position2;
//            int type2;
//
//            if (o1 instanceof Animal) {
//                position1 = ((Animal) o1).getPosition();
//                type1 = 1;
//            }
//            else {
//                type1 = 0;
//                position1 = ((Grass) o1).getPosition();
//            }
//            if (o2 instanceof Animal) {
//                type2 = 4;
//                position2 = ((Animal) o2).getPosition();
//            }
//            else {
//                type2 = 3;
//                position2 = ((Grass) o2).getPosition();
//            }
//            if (position1.x != position2.x){
//                return position2.x - position1.x;
//            }
//            if (position1.y != position2.y){
//                return position2.y - position1.y;
//            }
//            return type2 - type1;
//        }
//    };
//    Comparator<Object> compareY = new Comparator<Object>() {
//        @Override
//        public int compare(Object o1, Object o2) {
//            Vector2d position1;
//            int type1;
//            Vector2d position2;
//            int type2;
//
//            if (o1 instanceof Animal) {
//                position1 = ((Animal) o1).getPosition();
//                type1 = 1;
//            }
//            else {
//                type1 = 0;
//                position1 = ((Grass) o1).getPosition();
//            }
//
//            if (o2 instanceof Animal) {
//                type2 = 4;
//                position2 = ((Animal) o2).getPosition();
//            }
//            else {
//                type2 = 3;
//                position2 = ((Grass) o2).getPosition();
//            }
//            if (position1.y != position2.y){
//                return position2.y - position1.y;
//            }
//            if (position1.x != position2.x){
//                return position2.x - position1.x;
//            }
//            return type2 - type1;
//        }
//    };

    Comparator<Vector2d> compareX = (o1, o2) -> {
        if (o1.x != o2.x) {
            return o1.x - o2.x;
        }
        return o1.y - o2.y;
    };
    Comparator<Vector2d> compareY = (o1, o2) -> {
        if (o1.y != o2.y) {
            return o1.y - o2.y;
        }
        return o1.x - o2.x;
    };
    public SortedSet<Vector2d> xSortedGrass = new TreeSet<>(compareX);
    public SortedSet<Vector2d> ySortedGrass = new TreeSet<>(compareY);

    public SortedSet<Vector2d> xSortedAnimal = new TreeSet<>(compareX);
    public SortedSet<Vector2d> ySortedAnimal = new TreeSet<>(compareY);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xSortedAnimal.remove(oldPosition);
        xSortedAnimal.add(newPosition);

        ySortedAnimal.remove(oldPosition);
        ySortedAnimal.add(newPosition);
    }

    public Vector2d[] border()
    {
        Vector2d low;
        Vector2d high;
        low = xSortedAnimal.first();
        low = low.lowerLeft(ySortedAnimal.first());
        low = low.lowerLeft(xSortedGrass.first());
        low = low.lowerLeft(ySortedGrass.first());

        high = xSortedAnimal.last();
        high = high.upperRight(ySortedAnimal.last());
        high = high.upperRight(xSortedGrass.last());
        high = high.upperRight(ySortedGrass.last());

        return new Vector2d[] {low, high};
    }

}
