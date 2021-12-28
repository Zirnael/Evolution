package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import javax.lang.model.type.NullType;
import java.util.Objects;

public class World {
    public static void main(String[] args) {

        try
        {
            Application.launch(App.class, args);
            MoveDirection[] directions = new OptionsParser().parse(args);
            GrassField map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

            IEngine engine = new SimulationEngine(directions, map, positions);
            System.out.println(map);
            engine.run();
            System.out.println(map);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
