package agh.ics.oop;

import javax.xml.stream.FactoryConfigurationError;

public class Animal{
    private MapDirection animalDirection = MapDirection.NORTH;

    private Vector2d position;

    private IWorldMap map;

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
                Vector2d newPosition = this.position.add(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(newPosition)) {
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.animalDirection.toUnitVector());

                if (this.map.canMoveTo(newPosition)) {
                    this.position = newPosition;

                }
            }
        }
    }
}