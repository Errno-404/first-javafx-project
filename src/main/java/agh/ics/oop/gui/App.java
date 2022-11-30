package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    public AbstractWorldMap field;

    public int windowWidth = 600;
    public int windowHeight = 800;
    public int gridDimension = 30;

    @Override
    public void start(Stage primaryStage){

        try {

            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(3, 4)};

            this.field = new GrassField(10);

            IEngine engine = new SimulationEngine(directions, field, positions);

            engine.run();

            Scene scene = new Scene(
                    draw(
                            field.leftCorner,
                            field.rightCorner
                    ),
                    windowWidth,
                    windowHeight
            );

            primaryStage.setTitle("Zwierzogród");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            primaryStage.close();
            Platform.exit();
        }


    }

    public GridPane draw(Vector2d leftCorner, Vector2d rightCorner){
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        int maxWidth = rightCorner.getX() - leftCorner.getX() + 1;
        int maxHeight = rightCorner.getY() - leftCorner.getY() + 1;

        System.out.print(maxWidth);
        System.out.print(maxHeight);

        // Size of the grid

        for(int i = 0; i<=maxWidth;i++){
            gridPane.getColumnConstraints().add(new ColumnConstraints(gridDimension));
        }
        for(int i = 0; i <= maxHeight;i++){
            gridPane.getRowConstraints().add(new RowConstraints(gridDimension));
        }

        // End size of the grid


        // Header section

        Label xy = new Label("x\\y");
        gridPane.add(xy, 0, 0);

        for(int i = 1; i <= maxWidth; i++){
            Label label = new Label(String.valueOf(leftCorner.x + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, i, 0);
        }

        for(int i = 1; i<= maxHeight; i++){
            Label label = new Label(String.valueOf(maxHeight - leftCorner.y - i));
            GridPane.setHalignment(label, HPos.CENTER);
            gridPane.add(label, 0, i);
        }

        // END OF HEADER SECTION


        for(int i = 1; i <= maxHeight; i ++){
            int y = rightCorner.y - i + 1;
            for(int j = 1; j <= maxWidth; j++){
                int x = leftCorner.x + j - 1;

                String content;
                Vector2d currentPosition = new Vector2d(x, y);
                if(field.isOccupied(currentPosition)){
                    Object object = this.field.objectAt(currentPosition);

                    if(object != null){
                        content = object.toString();
                    }
                    else{
                        content = " ";
                    }
                    }else{
                    content = " ";
                }

                Label label = new Label(content);
                GridPane.setHalignment(label, HPos.CENTER);

                gridPane.add(label, j, i, 1,1);


            }
        }
        this.windowHeight = gridDimension * (maxHeight + 1);
        this.windowWidth = gridDimension * (maxWidth + 1);

        return gridPane;
    }

}