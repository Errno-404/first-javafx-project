package agh.ics.oop;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{

    private MapDirection animalDirection = MapDirection.NORTH;

    private Vector2d position;

    private IWorldMap map;

    public List<IPositionChangeObserver> observers = new ArrayList<>();


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }


    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getAnimalDirection() {
        return animalDirection;
    }



    public String toString() {
        return switch (this.animalDirection) {
            case EAST -> ">";
            case WEST -> "<";
            case NORTH -> "^";
            case SOUTH -> "v";
        };
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.animalDirection = this.animalDirection.next();
            case LEFT -> this.animalDirection = this.animalDirection.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(newPosition)) {
                    this.positionChanged(this.position, newPosition);
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(newPosition)) {
                    this.positionChanged(this.position, newPosition);
                    this.position = newPosition;

                }
            }
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.observers.forEach((obs) -> obs.positionChanged(oldPosition, newPosition));
    }


    // IMapElement

    @Override
    public String getImageURL(){
        return switch (this.animalDirection){
            case NORTH -> "up.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
            case EAST -> "right.png";
        };
    }

    @Override
    public String getElementLabel(){
        return this.position.toString();
    }
}