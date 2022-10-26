package agh.ics.oop;

public class Animal {
    private MapDirection animalDirection = MapDirection.NORTH;

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getAnimalDirection() {
        return animalDirection;
    }

    private Vector2d position = new Vector2d(2, 2);

    public String toString() {
        return "(" + this.position + ", " + this.animalDirection + ")";
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

                if ((currentPosition.follows(new Vector2d(0, 0)))
                        && (currentPosition.precedes(new Vector2d(4, 4))))
                    this.position = currentPosition;
            }
            case BACKWARD -> {
                Vector2d currentPosition = this.position.subtract(this.animalDirection.toUnitVector());

                if ((currentPosition.follows(new Vector2d(0, 0)))
                        && (currentPosition.precedes(new Vector2d(4, 4))))
                    this.position = currentPosition;
            }
        }
    }
}