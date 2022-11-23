package agh.ics.oop;

import java.util.HashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
//    public List<Animal> animals = new ArrayList<>();

    protected HashMap<Vector2d, Animal> animals = new HashMap<>();


    //TODO: fix corner values
    protected Vector2d leftCorner = new Vector2d(0, 0);
    protected Vector2d rightCorner;


    @Override
    public abstract boolean canMoveTo(Vector2d position);



    @Override
    public boolean place(Animal animal){
        if(canMoveTo(animal.getPosition())){
//            this.animals.add(animal);
            this.animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }


    @Override
    public Object objectAt(Vector2d position){
        return animals.getOrDefault(position, null);
    }

    protected abstract void dynamicSizing();

    @Override
    public String toString(){
        MapVisualizer board = new MapVisualizer(this);
        this.dynamicSizing();
        return board.draw(this.leftCorner, this.rightCorner);
    }

    @Override
    public void  positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal change = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, change);
    }
}


