package agh.ics.oop;

import javax.lang.model.type.NullType;
import java.util.Objects;

public class World {
    public static void run(Directions[] tablica) {
        System.out.println("Start");
        for (Directions c:tablica) {
            switch (c){
                case f:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case b:
                    System.out.println("Zwierzak idzie do tylu");
                    break;
                case l:
                    System.out.println("Zwierzak idzie w lewo");
                    break;
                case r:
                    System.out.println("Zwierzak idzie w prawo");

            }
        }
        System.out.println("Stop");
    }
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        System.out.println("System wystartował");

        int count = 0;
        for (String argument:args){
            if (Objects.equals(argument, "f") || Objects.equals(argument, "b") || Objects.equals(argument, "l") || Objects.equals(argument, "r"))
                count++;
        }

        Directions[] to_give = new Directions[count];
        count = 0;
        for (String x:args) {
            Directions to_add;
            switch (x){
                case "f":
                    to_add = Directions.f;
                    break;
                case "b":
                    to_add = Directions.b;
                    break;
                case "l":
                    to_add = Directions.l;
                    break;
                case "r":
                    to_add = Directions.r;
                    break;
                default:
                    to_add = null;
            }
            if (to_add != null)
                to_give[count++] = to_add;
        }

        run(to_give);
        System.out.println("system zakończył działanie");
    }
}
