package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {


    protected VBox vbox;



    public GuiElementBox(IMapElement element){
        String elementURL = element.getImageURL();
        String elementLabel = element.getElementLabel();

        Image elementImageSource = new Image(elementURL);
        ImageView elementImage = new ImageView(elementImageSource);
        elementImage.setFitWidth(20);
        elementImage.setFitHeight(20);
        Label elementPosition = new Label(elementLabel);
        this.vbox = new VBox(2);
        this.vbox.getChildren().addAll(elementImage, elementPosition);
        this.vbox.setAlignment(Pos.CENTER);

    }

}
