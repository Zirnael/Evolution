package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class App extends Application {

    public void start(Stage primaryStage) {

        Label lb1 = new Label("Width");
        Label lb2 = new Label("Height");
        Label lb3 = new Label("Start Energy");
        Label lb4 = new Label("Move Energy");
        Label lb5 = new Label("Plant Energy");
        Label lb6 = new Label("Jungle (0-100)%");
        Label lb7 = new Label("Starting animals");
        Label lb8 = new Label("Day delay");
        RadioButton lrb9 = new RadioButton("Normal");
        lrb9.setSelected(true);

        TextField tf1=new TextField("20");
        TextField tf2=new TextField("20");
        TextField tf3=new TextField("30");
        TextField tf4=new TextField("1");
        TextField tf5=new TextField("6");
        TextField tf6=new TextField("20");
        TextField tf7=new TextField("10");
        TextField tf8=new TextField("1000");
        RadioButton rrb9 = new RadioButton("Magic");

        ToggleGroup group = new ToggleGroup();
        lrb9.setToggleGroup(group);
        rrb9.setToggleGroup(group);
        Button sbmt = new Button("Submit");
        sbmt.setOnAction(e-> {
            try {
                int val1 = Integer.parseInt(tf1.getText());
                int val2 = Integer.parseInt(tf2.getText());
                int val3 = Integer.parseInt(tf3.getText());
                int val4 = Integer.parseInt(tf4.getText());
                int val5 = Integer.parseInt(tf5.getText());
                int val6 = Integer.parseInt(tf6.getText());
                int val7 = Integer.parseInt(tf7.getText());
                int val8 = Integer.parseInt(tf8.getText());
                boolean val9 = rrb9.isSelected();

                Constants.width = val1;
                Constants.height = val2;
                Constants.startEnergy = val3;
                Constants.moveEnergy = val4;
                Constants.plantEnergy = val5;
                Constants.jungleRatio = val6;
                Constants.noAnimals = val7;
                Constants.dayDelay = val8;
                Constants.magic = val9;

                this.app(primaryStage);
            }catch (NumberFormatException exception){
                Alert a = new Alert(Alert.AlertType.ERROR,"Enter correct values");
                a.show();
            }


        });
        sbmt.setDefaultButton(true);
        GridPane root = new GridPane();
        root.addRow(0, lb1, tf1);
        root.addRow(1, lb2, tf2);
        root.addRow(2, lb3, tf3);
        root.addRow(3, lb4, tf4);
        root.addRow(4, lb5, tf5);
        root.addRow(5, lb6, tf6);
        root.addRow(6, lb7, tf7);
        root.addRow(7, lb8, tf8);
        root.addRow(8,lrb9,rrb9);
        root.addRow(9, sbmt);
        root.setHgap(20);
        Scene scene=new Scene(root,300,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Input simulation values");
        primaryStage.show();
    }

    private void app(Stage primaryStage) {
        GridPane leftMapGrid = new GridPane();
        GridPane rightMapGrid = new GridPane();

        BorderMap leftMap = new BorderMap();
        TeleportMap rightMap = new TeleportMap();

        MapDrawer leftDrawer = new MapDrawer(leftMap, leftMapGrid);
        MapDrawer rightDrawer = new MapDrawer(rightMap,rightMapGrid);

        Simulation bordered = new Simulation(leftMap,leftDrawer);
        Simulation noBorder = new Simulation(rightMap,rightDrawer);

        leftDrawer.draw();
        rightDrawer.draw();

        Button leftButton = new Button("Start simulation");
        Button rightButton = new Button("Start simulation");

        ThreadManager leftManager = new ThreadManager(bordered,leftButton);
        ThreadManager rightManager = new ThreadManager(noBorder,rightButton);


        leftButton.setOnAction(e->{
            leftManager.click();
        });
        rightButton.setOnAction(e->{
            rightManager.click();
        });

        GridPane.setHalignment(leftButton, HPos.CENTER);
        GridPane.setHalignment(rightButton, HPos.CENTER);

        int boxHeight = 400/Constants.height;
        int boxWidth = 400/Constants.width;

        for(int i = 0 ; i < Constants.width; i++){
            leftMapGrid.getColumnConstraints().add(new ColumnConstraints(boxWidth));
            rightMapGrid.getColumnConstraints().add(new ColumnConstraints(boxWidth));
        }
        for(int j = 0; j < Constants.height; j++){
            leftMapGrid.getRowConstraints().add(new RowConstraints(boxHeight));
            rightMapGrid.getRowConstraints().add(new RowConstraints(boxHeight));
        }
        leftMapGrid.setGridLinesVisible(true);
        rightMapGrid.setGridLinesVisible(true);

        GridPane fullGrid = new GridPane();
        fullGrid.addRow(0,leftMapGrid,rightMapGrid);
        fullGrid.addRow(1, leftButton, rightButton);
        fullGrid.setHgap(100);
        fullGrid.setVgap(50);


        Scene scene = new Scene(fullGrid,900,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation: Aleksander Pytel");
        primaryStage.show();

    }

//    @Override
//public void start(Stage primaryStage) {
//
//    primaryStage.setTitle("GridPane Experiment");
//
//    GridPane gridPane = new GridPane();
///*
//    for(int i = 0; i <= width; i++){
//        gridPane.getColumnConstraints().add(new ColumnConstraints(20));
//    }
//    for (int j = 0; j <= height; j++) {
//        gridPane.getRowConstraints().add(new RowConstraints(20));
//    }
//
//    gridPane.setGridLinesVisible(true);
//
//    */
//    Scene scene = new Scene(gridPane, 300, 300);
//    primaryStage.setScene(scene);
//    primaryStage.show();
//
//}

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
