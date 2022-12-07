package agh.ics.oop;

import java.util.HashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected HashMap<Vector2d, Animal> animals = new HashMap<>();

    protected MapBoundary mapBoundary = new MapBoundary();

    public Vector2d leftCorner;
    public Vector2d rightCorner;

    @Override
    public abstract boolean canMoveTo(Vector2d position);

    // TODO: pytanie - czy zmienić typ na void? Nieładny jest ten return tylko w przypadku powodzenia,
    //  a brak false w.p.p

    @Override
    public boolean place(Animal animal){

        Vector2d animalPosition = animal.getPosition();

        if(canMoveTo(animalPosition)){


            this.animals.put(animalPosition, animal);
            this.mapBoundary.addCordsXY(animalPosition);

            animal.addObserver(this);
            animal.addObserver(this.mapBoundary);

            return true;
        }
        throw new IllegalArgumentException("position " + animalPosition + " is already occupied!");
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return objectAt(position) != null;
    }


    @Override
    public Object objectAt(Vector2d position){
        return animals.getOrDefault(position, null);
    }

    public abstract void dynamicSizing();

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

    public Vector2d getLeftCorner() {
        return leftCorner;
    }

    public Vector2d getRightCorner(){
        return rightCorner;
    }
}


