package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    // final check later
    private final int width;
    private final int height;

    public List<Animal> animals = new ArrayList<>();

    RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }


    @Override
    public boolean canMoveTo(Vector2d position){
        // obiekt może się przemieścić na pozycję position, kiedy nie jest ona zajęta
        // oraz kiedy pozycja ta mieści się w zakresie mapy

        if(isOccupied(position)){
            return false;
        }
        return position.follows(new Vector2d(0, 0))
                && position.precedes(new Vector2d(this.width, this.height));
    }



    @Override
    public boolean place(Animal animal){
        // zwierze może zostać dodane do mapy, gdy może się na tę pozycję przemieścić
        // w tym przypadku przemieszczenie to jest z danej pozycji na samą siebie.

        Vector2d checkPosition = animal.getPosition();
        if(canMoveTo(checkPosition)){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        // jeśli obiekt na position nie jest nullem, tzn, że jest okupowany
        return objectAt(position) != null;
    }


    @Override
    public Object objectAt(Vector2d position){
        for(Animal animal: this.animals){
            if(animal.getPosition().equals(position)){
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        MapVisualizer board = new MapVisualizer(this);
        return board.draw(new Vector2d(0, 0), new Vector2d(this.width, this.height));
    }
}
