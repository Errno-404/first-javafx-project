package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    public void addCordsXY(Vector2d objPosition){
        cordsX.add(objPosition);
        cordsY.add(objPosition);
    }

    // zakładam że już są wstawiane jako posortowane
    private TreeSet<Vector2d> cordsX = new TreeSet<>(Comparator.comparing(Vector2d::getX));
    private TreeSet<Vector2d> cordsY = new TreeSet<>(Comparator.comparing(Vector2d::getY));



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        //TODO
    }
}
