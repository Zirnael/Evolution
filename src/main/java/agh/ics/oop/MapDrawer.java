package agh.ics.oop;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Map;

public class MapDrawer {
    AnyMap map;
    GridPane gridPane;

    public MapDrawer(AnyMap map, GridPane gridPane){
        this.map = map;
        this.gridPane = gridPane;
    }
    public void draw()
    {
        int boxHeight = 400/Constants.height;
        int boxWidth = 400/Constants.width;

        for(int i = 0; i < Constants.width;i++) {
            for (int j = 0; j < Constants.height; j++) {
                Vector2d position = new Vector2d(i, j);
                Label x = new Label("");
                Background grass = new Background(new BackgroundFill(Color.color(0,1,0),null,null));
                Background jungle = new Background(new BackgroundFill(Color.DARKGREEN,null,null));
                Background empty = new Background(new BackgroundFill(Color.color(1,1,1),null,null));

                if (map.isOccupiedAnimal(position)) {
                    Animal animal = map.animalsAt(position).get(0);
                    BackgroundFill bb = new BackgroundFill(Color.color(Math.min((double)animal.energy*0.8/(double)Constants.startEnergy+0.2,1),0,0), null, null);
                    Background b = new Background(bb);
                    x.backgroundProperty().setValue(b);
                }else if(map.isOccupiedGrass(position)){
                    x.backgroundProperty().setValue(grass);
                }else if(map.isJungle(position)){
                    x.backgroundProperty().setValue(jungle);
                }else{
                    x.backgroundProperty().setValue(empty);
                }

                x.setMinWidth(boxWidth-2);
                x.setMinHeight(boxHeight-2);
                GridPane.setHalignment(x, HPos.CENTER);
                gridPane.add(x, i, j);
            }
        }
    }
}
