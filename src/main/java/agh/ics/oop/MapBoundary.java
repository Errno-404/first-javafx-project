package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    public void addCordsXY(Vector2d objPosition){
        cordsX.add(objPosition);
        cordsY.add(objPosition);
    }


    public TreeSet<Vector2d> cordsX = new TreeSet<>(Comparator.comparing(Vector2d::getX).thenComparing(Vector2d::getY));
    public TreeSet<Vector2d> cordsY = new TreeSet<>(Comparator.comparing(Vector2d::getY).thenComparing(Vector2d::getX));



    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        cordsX.remove(oldPosition);
        cordsY.remove(oldPosition);
        cordsX.add(newPosition);
        cordsY.add(newPosition);
    }
}
