package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    public List<Animal> animals = new ArrayList<>();

    protected Vector2d leftCorner = new Vector2d(0, 0);
    protected Vector2d rightCorner;


    @Override
    public abstract boolean canMoveTo(Vector2d position);



    @Override
    public boolean place(Animal animal){
        if(canMoveTo(animal.getPosition())){
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }


    @Override
    public abstract Object objectAt(Vector2d position);

    protected abstract void dynamicSizing();

    @Override
    public String toString(){
        MapVisualizer board = new MapVisualizer(this);
        this.dynamicSizing();
        return board.draw(this.leftCorner, this.rightCorner);
    }
}


