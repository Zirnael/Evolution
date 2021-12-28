package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    @Override
public void start(Stage primaryStage) {
        /*

    primaryStage.setTitle("GridPane Experiment");

    GridPane gridPane = new GridPane();
    String[] args = getParameters().getRaw().toArray(new String[0]);

    MoveDirection[] directions = new OptionsParser().parse(args);
    AbstractWorldMap map = new GrassField(10);
    Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

    IEngine engine = new SimulationEngine(directions, map, positions);

    Vector2d[] border = map.border();
    Vector2d low = border[0];
    Vector2d high = border[1];

    int width = high.x - low.x + 1;
    int height = high.y - low.y + 1;

    for(int i = 0; i <= width; i++){
        for (int j = 0; j <= height; j++) {
            Label x;
            if (i == 0){
                if (j==height){
                    x = new Label("y\\x");
                }
                else{

                    x = new Label(j+"");

                }
            }
            else if(j==height){
                x = new Label((i-1)+"");

            }
            else{
                Vector2d position = new Vector2d(i-1, j);
                if (map.isOccupied(position))
                {
                    x = new Label(map.objectAt(position).toString());
                }
                else
                {
                    x = new Label("");
                }
            }
            GridPane.setHalignment(x,HPos.CENTER);
            gridPane.add(x,i,height-j);
        }
    }

    System.out.println(map);
    engine.run();
    System.out.println(map);
    for(int i = 0; i <= width; i++){
        gridPane.getColumnConstraints().add(new ColumnConstraints(20));
    }
    for (int j = 0; j <= height; j++) {
        gridPane.getRowConstraints().add(new RowConstraints(20));
    }

    gridPane.setGridLinesVisible(true);
    Scene scene = new Scene(gridPane, 300, 300);
    primaryStage.setScene(scene);
    primaryStage.show();

     */
}

    public static void main(String[] args) {

        Application.launch(App.class, args);
    }
}
