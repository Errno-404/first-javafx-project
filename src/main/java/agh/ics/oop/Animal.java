package agh.ics.oop;

import javax.xml.stream.FactoryConfigurationError;

public class Animal{
    private MapDirection animalDirection;

    private Vector2d position;

    private IWorldMap map;

   Animal(){
       this.position = new Vector2d(2, 2);
       this.animalDirection = MapDirection.NORTH;
   }

    Animal(IWorldMap map) {
        this.map = map;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    // gettery

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
                Vector2d currentPosition = this.position.add(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(currentPosition)) {
                    this.position = currentPosition;
                }
            }
            case BACKWARD -> {
                Vector2d currentPosition = this.position.subtract(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(currentPosition)) {
                    this.position = currentPosition;

                }
            }
        }
    }
}