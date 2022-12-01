package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{

    public AbstractWorldMap field;

    public int windowWidth = 600;
    public int windowHeight = 800;
    public int gridDimension = 40;

    private GridPane gridPane;
    private Vector2d leftCorner;
    private Vector2d rightCorner;

    @Override
    public void start(Stage primaryStage) {




        try {

            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = new OptionsParser().parse(args);
            Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(3, 4)};

            this.field = new GrassField(10);

            IEngine engine = new SimulationEngine(directions, field, positions);

            engine.run();


            this.leftCorner = field.leftCorner;
            this.rightCorner = field.rightCorner;
            Scene scene = new Scene(
                    draw(
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

    public GridPane draw() {
        this.gridPane = new GridPane();

        gridPane.setGridLinesVisible(true);

        setupGrid();

        generateGridOnce();

        return gridPane;
    }

    public void setupGrid() {
        int maxWidth = this.rightCorner.getX() - this.leftCorner.getX() + 1;
        int maxHeight = this.rightCorner.getY() - this.leftCorner.getY() + 1;


        // SIZING THE GRID
        for (int i = 0; i <= maxWidth; i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(this.gridDimension));
        }
        for (int i = 0; i <= maxHeight; i++) {
            this.gridPane.getRowConstraints().add(new RowConstraints(this.gridDimension));
        }

        this.windowHeight = gridDimension * (maxHeight + 1) * 2;
        this.windowWidth = gridDimension * (maxWidth + 1) * 2;
        // END OF SIZING


        // HEADER PRINTING
        Label xy = new Label("x\\y");
        this.gridPane.add(xy, 0, 0);
        GridPane.setHalignment(xy, HPos.CENTER);
        GridPane.setValignment(xy, VPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 1; i <= maxWidth; i++) {
            Label label = new Label(String.valueOf(this.leftCorner.x + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            this.gridPane.add(label, i, 0);
        }

        for (int i = 1; i <= maxHeight; i++) {
            Label label = new Label(String.valueOf(maxHeight - this.leftCorner.y - i));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            this.gridPane.add(label, 0, i);
        }
        // END OF HEADER SECTION
    }


    public void generateGridOnce() {
        int maxWidth = this.rightCorner.getX() - this.leftCorner.getX() + 1;
        int maxHeight = this.rightCorner.getY() - this.leftCorner.getY() + 1;


        for (int i = 1; i <= maxHeight; i++) {
            int y = this.rightCorner.y - i + 1;
            for (int j = 1; j <= maxWidth; j++) {
                int x = this.leftCorner.x + j - 1;

                Vector2d currentPosition = new Vector2d(x, y);
                if (field.isOccupied(currentPosition)) {
                    Object object = this.field.objectAt(currentPosition);

                    GuiElementBox box = new GuiElementBox((IMapElement) object);
                    gridPane.add(box.vbox, j, i,1,1);

                }

            }
        }

    }

}