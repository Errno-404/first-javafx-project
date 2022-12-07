package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application implements IPositionChangeObserver{

    public AbstractWorldMap map;

    public int windowWidth = 600;
    public int windowHeight = 800;
    public int gridDimension = 40;

    private GridPane gridPane;

    private long moveDelay;
    private Thread engineThread;

    public SimulationEngine engine;

    @Override
    public void init(){

        // Initializing map and engine
        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(3, 4)};
        this.map = new GrassField(10);
        this.engine = new SimulationEngine(directions, map, positions);
        this.engine.addObserver(this);

        // moveDelay must be initialized before engine Thread starts?
        this.moveDelay = 300L;

        // Thread initialization and start
        this.engineThread = new Thread(engine);
        engineThread.start();

        // Gui initialization
        this.gridPane = new GridPane();
    }

    @Override
    public void start(Stage primaryStage) {

        Button startButton = new Button("Start");
        TextField textField = new TextField();
        TextField delayField = new TextField();

        delayField.setText(String.valueOf(moveDelay));
        textField.setText(
                String.join(" ", getParameters().getRaw())
        );

        //// SETUP FORM ////

        HBox hBox = new HBox();
        hBox.getChildren().add(textField);
        hBox.getChildren().add(delayField);
        hBox.getChildren().add(startButton);
        hBox.setAlignment(Pos.CENTER);

        //// SETUP ERROR MESSAGE BOX ////

        Label errorMessage = new Label("");

        errorMessage.setPadding(new Insets(10));
        errorMessage.setTextFill(Color.color(1,0,0));

        HBox errorMessageBox = new HBox();

        errorMessageBox.getChildren().add(errorMessage);
        errorMessageBox.setAlignment(Pos.CENTER);

        //// SETUP GRID-BOARD ////

        HBox gridContainer = new HBox();

        this.gridPane = new GridPane();
        this.gridPane.setGridLinesVisible(true);

        gridContainer.getChildren().add(this.gridPane);
        gridContainer.setAlignment(Pos.CENTER);

        //// SETUP SCENE STRUCTURE ////

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(errorMessageBox);
        vBox.getChildren().add(gridContainer);

        Scene scene = new Scene(
                vBox,
                windowWidth,
                windowHeight
        );
        try {

            Platform.runLater(this::draw);


            primaryStage.setTitle("Zwierzogród");
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setMaximized(true);

            startButton.setOnAction( (e) -> {
                try {
                    this.moveDelay = Integer.parseInt( delayField.getText() );

                    this.engineThread = new Thread(this.engine);
                    this.engine.setDirections(
                            new OptionsParser().parse(textField.getText().trim().split("\\s+"))
                    );

                    this.engineThread.start();

                } catch (NumberFormatException ex){
                    errorMessage.setText("Error: \""+ delayField.getText() + "\" is not an integer");
                } catch (IllegalArgumentException ex) {
                    errorMessage.setText("Error: "+ ex.getMessage());
                }

            });


        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            primaryStage.close();
            Platform.exit();
        }





    }



    public void draw() {

        Vector2d rightCorner = this.map.getRightCorner();
        Vector2d leftCorner = this.map.getLeftCorner();

        // clear whole grid

        this.gridPane.setGridLinesVisible(false);
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getChildren().clear();

        // show lines back
        this.gridPane.setGridLinesVisible(true);


        int maxWidth = rightCorner.getX() - leftCorner.getX() + 1;
        int maxHeight = rightCorner.getY() - leftCorner.getY() + 1;


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
            Label label = new Label(String.valueOf(leftCorner.x + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            this.gridPane.add(label, i, 0);
        }

        for (int i = 1; i <= maxHeight; i++) {
            Label label = new Label(String.valueOf(rightCorner.y - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
            this.gridPane.add(label, 0, i);
        }


        for (int i = 1; i <= maxHeight; i++) {
            int y = rightCorner.y - i + 1;
            for (int j = 1; j <= maxWidth; j++) {
                int x = leftCorner.x + j - 1;

                Vector2d currentPosition = new Vector2d(x, y);
                if (this.map.isOccupied(currentPosition)) {
                    Object object = this.map.objectAt(currentPosition);

                    GuiElementBox box = new GuiElementBox((IMapElement) object);
                    gridPane.add(box.vbox, j, i,1,1);

                }

            }
        }

    }



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        try{
            Thread.sleep(moveDelay);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        Platform.runLater(this::draw);

    }
}